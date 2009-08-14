

package org.bworks.bworksdb

class ContactController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ contactInstanceList: Contact.list( params ), contactInstanceTotal: Contact.count() ]
    }

    def show = {
        def contactInstance = Contact.get( params.id )

        if(!contactInstance) {
            flash.message = "Contact not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ contactInstance : contactInstance ] }
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
            flash.message = "Contact ${contactInstance.id} created"
            redirect(action:show,id:contactInstance.id)
        }
        else {
            render(view:'create',model:[contactInstance:contactInstance])
        }
    }

    /**
     * Save the given Contact and their associated students
     */
    def saveContactAndStudents = {
      def contactInstance
      println params
      if (params.id) {
        contactInstance = Contact.get(params.id)

        contactInstance.properties = params

        contactInstance.validate()

        //Loop through students, validate and save
        contactInstance.students.each {student ->
          if (student.validate()) {
            student.save()
          }
          else {
              student.errors.allErrors.each {
                  println it
              }
          }
        }

        contactInstance.save()
        
        redirect action:'show', id:contactInstance.id
        return
      }
      redirect action:'show', id:contactInstance.id
    }

    //Show editable student row for data entry
    def newInlineStudent = {
      render(template: 'newInlineStudent', model: ['idx': params.idx])
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
