package org.bworks.bworksdb

class ProgramController {
    
    def index = { redirect(action:list,params:params) }
    def programService

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def pdfCallList = {
        def contactInstanceList = programService.getCallList(1)
        println "Contact instances are: " + contactInstanceList

        [ contactInstanceList: contactInstanceList, contactInstanceTotal: Contact.count() ]

    }
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ programInstanceList: Program.list( params ), programInstanceTotal: Program.count() ]
    }

    def show = {
        def programInstance = Program.get( params.id )

        if(!programInstance) {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ programInstance : programInstance ] }
    }

    def delete = {
        def programInstance = Program.get( params.id )
        if(programInstance) {
            try {
                programInstance.delete(flush:true)
                flash.message = "Program ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Program ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def programInstance = Program.get( params.id )

        if(!programInstance) {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ programInstance : programInstance ]
        }
    }

    def update = {
        def programInstance = Program.get( params.id )
        if(programInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(programInstance.version > version) {
                    
                    programInstance.errors.rejectValue("version", "program.optimistic.locking.failure", "Another user has updated this Program while you were editing.")
                    render(view:'edit',model:[programInstance:programInstance])
                    return
                }
            }
            programInstance.properties = params
            if(!programInstance.hasErrors() && programInstance.save()) {
                flash.message = "Program ${params.id} updated"
                redirect(action:show,id:programInstance.id)
            }
            else {
                render(view:'edit',model:[programInstance:programInstance])
            }
        }
        else {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def programInstance = new Program()
        programInstance.properties = params
        return ['programInstance':programInstance]
    }

    def save = {
        def programInstance = new Program(params)
        if(!programInstance.hasErrors() && programInstance.save()) {
            flash.message = "Program ${programInstance.id} created"
            redirect(action:show,id:programInstance.id)
        }
        else {
            render(view:'create',model:[programInstance:programInstance])
        }
    }

    def buildReportData = { interest ->
        def student = interest.student
        def contact = student.contact 
        
        def addr = [ contact.address1 , contact.address2 ?: '' ]
        def csz = [ contact.city ?: '' , contact.state ?: '', contact.zipCode ?: '']
        
        [
            CONTACT_NAME:contact,
            CONTACT_ADDRESS:addr.join('<BR />'),
            // TODO Contact Notes, mmmk?
            CONTACT_NOTES:'',
            CONTACT_CITY_STATE_ZIP:csz.join(', '),
            CONTACT_EMAIL:contact.emailAddress,
            CONTACT_PHONE:contact.phoneNumbers.join('<br />'),
            CONTACT_ID:contact.id,
            STUDENT_NAME:student.fullName(),
            STUDENT_BIRTHDATE:student.birthDate ?: '?',
            STUDENT_GENDER:student.gender ?: '?',
            STUDENT_GRADE:student.grade ?: '?'
        ]        
    }
    
    def callListReportData = {
        def programInstance = Program.get(params.id)
        params['PROGRAM_NAME'] = programInstance.name

        // Default Graduation Date to date of last class
        // TODO: Refactor to a Service, or else give classSession a graduationDate
        def interests = programService.activeInterests(programInstance)

        def reportData = interests.collect {
            buildReportData(it)
        }
        
        return reportData
    }
    
    def callList = {
        def reportData = callListReportData()
        
        chain(controller:'jasper',
              action:'index',
              model:[data:reportData],params:params)
    }

    // Fetches new lesson dates from program specified in params.id
    // starting at params.startDate or today
    def nextAvailableLessonDates = {
        def p = Program.get(params.id)
        def startDate = new Date()
        if (params.startDate) {
            startDate = Date.parse('MM/dd/yyyy', params.startDate)
        }
        def lessonDates = programService.nextAvailableLessonDates(p, startDate)
        render(template:'/classSession/editLessonDates',
                  model:[lessonDates:lessonDates])
    }

}
