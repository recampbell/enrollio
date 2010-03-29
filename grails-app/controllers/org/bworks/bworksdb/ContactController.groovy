package org.bworks.bworksdb

class ContactController {
    
    def studentService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', saveStudent:'POST', save:'POST', update:'POST']

    def list = {
        def contactList
        def contactCount 
        
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        if(params.q){
            contactList = Contact.search( params.q + "*", offset:params.offset ).results
            contactCount = Contact.countHits( params.q + "*" )
        } else { 
            contactList = Contact.list( params )
            contactCount = Contact.count()
        }
        [ contactInstanceList: contactList, contactInstanceTotal: contactCount, previousQuery: params.q ]
    }

    def show = {
        def contactInstance = Contact.get( params.id )
        // Create a stub new student, in case user wants to create one.
        def newStudentInstance = new Student(lastName:contactInstance.lastName,
                                          contact:contactInstance)

        if(!contactInstance) {
            flash.message = "Contact not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ contactInstance : contactInstance, newStudentInstance : newStudentInstance ] }
    }

    def delete = {
        def contactInstance = Contact.get( params.id )
        if(contactInstance) {
            try {
                contactInstance.delete(flush:true)
                flash.message = "Contact ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Contact ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Contact not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def contactInstance = Contact.get( params.id )

        if(!contactInstance) {
            flash.message = "Contact not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ contactInstance : contactInstance ]
        }
    }

    def update = {
        def contactInstance = Contact.get( params.id )
        if(contactInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(contactInstance.version > version) {
                    
                    contactInstance.errors.rejectValue("version", "contact.optimistic.locking.failure", "Another user has updated this Contact while you were editing.")
                    render(view:'edit',model:[contactInstance:contactInstance])
                    return
                }
            }
            contactInstance.properties = params
            if(!contactInstance.hasErrors() && contactInstance.save()) {
                flash.message = "Contact ${params.id} updated"
                redirect(action:show,id:contactInstance.id)
            }
            else {
                render(view:'edit',model:[contactInstance:contactInstance])
            }
        }
        else {
            flash.message = "Contact not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def contactInstance = new Contact()
        // Create two new phone numbers
        contactInstance.addToPhoneNumbers(new PhoneNumber(label:'Home'))
        contactInstance.addToPhoneNumbers(new PhoneNumber(label:'Work'))
        contactInstance.properties = params
        return ['contactInstance':contactInstance]
    }


    def saveAndAddStudents = {
        def contactInstance = new Contact(params)
        if(!contactInstance.hasErrors() && contactInstance.save()) {
            flash.message = "Contact ${contactInstance.id} created"
            redirect(action:create,controller:"student",params:["contact.id":contactInstance.id])
        }
        else {
            render(view:'create',model:[contactInstance:contactInstance])
        }
    }
    // Only difference between this and saveAndAddStudents is that
    // saveAndAddStudents redirects to student/create action
    def save = {
        def contactInstance = new Contact(params)
        if(!contactInstance.hasErrors() && contactInstance.save()) {
            flash.message = "Created contact \"${contactInstance}\".  You can enter students below."
            redirect(action:show,id:contactInstance.id)
        }
        else {
            render(view:'create',model:[contactInstance:contactInstance])
        }
    }

    def saveStudent = {
        def studentInstance = new Student(params)

        // Find contact that student belongs to.
        def contactInstance = Contact.get(params.contact.id)

        if(!studentInstance.hasErrors() && studentInstance.validate()) {
            contactInstance.addToStudents(studentInstance)
            studentService.saveInterests(studentInstance, params['interestInCourse'])
            flash.studentMessage = "Successfully added '${studentInstance.fullName()}'"
            // render(template:'studentList', model:[contactInstance:contactInstance])
            redirect(action:'show', id:contactInstance.id)
        }
        else {
            render(view:'show',
                  model:[studentInstance:studentInstance,
                         contactInstance:contactInstance])
        }
    }
    //Replace inline div with "New student" button
    def cancelInlinestudent = {
        render(template: 'newInlinestudentButton', model: ['studentidx': params.studentidx])
    }

    /**
     * Delete an individual student associated with the given id from the DB
     */
    def deleteInLineStudent = {
        def contactInstance
        // student id passed from GSP
        if (params.id) {
            def studentInstance = student.get(params.id)
                contactInstance = studentInstance.contact
                // Remove student from list on contact DO
                contactInstance.removeFromStudents(studentInstance)
                studentInstance.delete() // Delete student from DB
                contactInstance.save(flush: true)
                render(template: "studentList",
                        model: [contactInstance:contactInstance])
        }
        // contact id passed from GSP
        else if (params.contact.id) {
            // Return a refreshed contact instance
            contactInstance = contact.get(params.contact.id)
                render(template: "studentList",
                        model: [contactInstance: contactInstance])
        }
    }
}
