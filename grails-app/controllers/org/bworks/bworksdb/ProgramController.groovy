package org.bworks.bworksdb

class ProgramController {
    
    def index = { redirect(action:list,params:params) }

    def courseService

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def pdfCallList = {
        def contactInstanceList = courseService.getCallList(1)

        [ contactInstanceList: contactInstanceList, contactInstanceTotal: Contact.count() ]

    }
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ courseInstanceList: Program.list( params ), courseInstanceTotal: Program.count() ]
    }

    def saveLessonSort = {
        def courseInstance = Program.get( params.id )

        if(!courseInstance) {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
        else {

            courseService.sortLessons(courseInstance, params)

            flash.message = "Lessons successfully sorted."

            redirect(action:lessons,id:courseInstance.id)
        }
    }

    def sortLessons = {
        def courseInstance = Program.get( params.id )
        [ courseInstance : courseInstance ]
    }

    def show = {
        def courseInstance = Program.get( params.id )

        if(!courseInstance) {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ courseInstance : courseInstance ] }
    }

    def delete = {
        def courseInstance = Program.get( params.id )
        if(courseInstance) {
            try {
                courseInstance.delete(flush:true)
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
        def courseInstance = Program.get( params.id )

        if(!courseInstance) {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ courseInstance : courseInstance ]
        }
    }

    def update = {
        def courseInstance = Program.get( params.id )
        if(courseInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(courseInstance.version > version) {
                    
                    courseInstance.errors.rejectValue("version", "course.optimistic.locking.failure", "Another user has updated this Program while you were editing.")
                    render(view:'edit',model:[courseInstance:courseInstance])
                    return
                }
            }
            courseInstance.properties = params
            if(!courseInstance.hasErrors() && courseInstance.save()) {
                flash.message = "Program ${params.id} updated"
                redirect(action:show,id:courseInstance.id)
            }
            else {
                render(view:'edit',model:[courseInstance:courseInstance])
            }
        }
        else {
            flash.message = "Program not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def courseInstance = new Program()
        courseInstance.properties = params
        return ['courseInstance':courseInstance]
    }

    def save = {
        def courseInstance = new Program(params)
        if(!courseInstance.hasErrors() && courseInstance.save()) {
            flash.message = "Program ${courseInstance.id} created"
            redirect(action:show,id:courseInstance.id)
        }
        else {
            render(view:'create',model:[courseInstance:courseInstance])
        }
    }
    
    def callList = {
        def reportData = callListReportData()
        
        chain(controller:'jasper',
              action:'index',
              model:[data:reportData],params:params)
    }

    def lessons = {
        def courseInstance = Program.get(params.id)
        [ 'courseInstance' : courseInstance ]
    }

    // Fetches new lesson dates from course specified in params.id
    // starting at params.startDate or today
    def nextAvailableLessonDates = {
        def p = Program.get(params.id)
        def startDate = new Date()
        if (params.startDate) {
            startDate = Date.parse('MM/dd/yyyy', params.startDate)
        }
        def lessonDates = courseService.nextAvailableLessonDates(p, startDate)
        render(template:'/classSession/editLessonDates',
                  model:[lessonDates:lessonDates])
    }
    
    // ---------------------------------
    
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
        def courseInstance = Program.get(params.id)
        params['PROGRAM_NAME'] = courseInstance.name

        // Default Graduation Date to date of last class
        // TODO: Refactor to a Service, or else give classSession a graduationDate
        def interests = courseService.activeInterests(courseInstance)

        return interests.collect(buildReportData)
    }

}
