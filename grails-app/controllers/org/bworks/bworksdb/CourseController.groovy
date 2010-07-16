package org.bworks.bworksdb
import grails.orm.PagedResultList
import org.bworks.bworksdb.auth.ShiroUser
import org.apache.shiro.SecurityUtils

class CourseController {
    
    def index = { redirect(action:list,params:params) }
    def userService

    static navigation = [
        group:'mainMenu',
        action:'list',
        title:'Courses',
		isVisible: { SecurityUtils.subject?.isAuthenticated() },
        order:10
    ]

    def courseService

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ courseInstanceList: Course.list( params ), courseInstanceTotal: Course.count() ]
    }

    def saveLessonSort = {
        def courseInstance = Course.get( params.id )

        if(!courseInstance) {
            flash.message = "Course not found with id ${params.id}"
            redirect(action:list)
        }
        else {

            courseService.sortLessons(courseInstance, params)

            flash.message = "Lessons successfully sorted."

            redirect(action:lessons,id:courseInstance.id)
        }
    }

    def sortLessons = {
        def courseInstance = Course.get( params.id )
        [ courseInstance : courseInstance ]
    }

    def show = {
        def courseInstance = Course.get( params.id )

        if(!courseInstance) {
            flash.message = "Course not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ courseInstance : courseInstance, 
            activeInterestCount : courseService.activeInterests(courseInstance).size() ] }
    }

    def delete = {
        def courseInstance = Course.get( params.id )
        if(courseInstance) {
            try {
                courseInstance.delete(flush:true)
                flash.message = "Course ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Course ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Course not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def courseInstance = Course.get( params.id )

        if(!courseInstance) {
            flash.message = "Course not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ courseInstance : courseInstance ]
        }
    }

    def update = {
        def courseInstance = Course.get( params.id )
        if(courseInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(courseInstance.version > version) {
                    
                    courseInstance.errors.rejectValue("version", "course.optimistic.locking.failure", "Another user has updated this Course while you were editing.")
                    render(view:'edit',model:[courseInstance:courseInstance])
                    return
                }
            }
            courseInstance.properties = params
            if(!courseInstance.hasErrors() && courseInstance.save()) {
                flash.message = "Course ${params.id} updated"
                redirect(action:show,id:courseInstance.id)
            }
            else {
                render(view:'edit',model:[courseInstance:courseInstance])
            }
        }
        else {
            flash.message = "Course not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def courseInstance = new Course()
        courseInstance.properties = params
        return ['courseInstance':courseInstance]
    }

    def save = {
        def courseInstance = new Course(params)
        if(!courseInstance.hasErrors() && courseInstance.save()) {
            flash.message = "Course ${courseInstance.id} created"
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
        def courseInstance = Course.get(params.id)
        [ 'courseInstance' : courseInstance ]
    }

    // Fetches new lesson dates from course specified in params.id
    // starting at params.startDate or today
    def nextAvailableLessonDates = {
        def p = Course.get(params.id)
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
    

    def interestedStudents = {

        // default to showing 10 records, and at most 100 records
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        println params
        def courseInstance = Course.get(params.id)
        def classSessionInstance = ClassSession.get(params.classSessionId)
        def callListContacts = courseService.callListContacts(courseInstance)

        def options = [ 
            max:params.max?.toLong(), 
            offset:params.offset?.toLong()
        ] 

        if(params.reservedForUser) {
            options['reservedForUser'] = ShiroUser.get(params.reservedForUser)
        }
            
        PagedResultList contactInstanceList = 
            courseService.callList(params.id.toLong(), options) 

        [ contactInstanceList : contactInstanceList,
          contactInstanceTotal : contactInstanceList.totalCount,
          reservedForUserId : params.reservedForUser,
            courseInstance : courseInstance,
            callListContacts : callListContacts,
            currentUser : userService.loggedInUser(),
            users : ShiroUser.list(), 
            classSessionInstance : classSessionInstance ]

    }

    def callListReportData = {
        def courseInstance = Course.get(params.id)
        params['PROGRAM_NAME'] = courseInstance.name

        // Default Graduation Date to date of last class
        // TODO: Refactor to a Service, or else give classSession a graduationDate
        def interests = courseService.activeInterests(courseInstance)

        return interests.collect(buildReportData)
    }

}
