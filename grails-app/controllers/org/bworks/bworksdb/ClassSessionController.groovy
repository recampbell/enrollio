package org.bworks.bworksdb

class ClassSessionController {

    def courseService
    def classSessionService

    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', 
                             saveEnrollments:'POST', update:'POST',
                             gradCerts: 'POST' ]

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ classSessionInstanceList: ClassSession.list( params ), classSessionInstanceTotal: ClassSession.count() ]
    }

    def editEnrollments = {
        def classSession = ClassSession.get(params.id)
        // TODO if courseId not provided, flash message and redirect back
        def interests = Interest.findAllByCourse(classSession.course).findAll {
            it.active == true
        }
        [ interests : interests , classSession:classSession ]
    }

    def saveEnrollments = {
        def enrollees = params.interestedStudents
        def classSession = ClassSession.get(params.classSessionId)
        enrollees.each {
            def stud = Student.get(it)
            def e = new Enrollment(student: stud, classSession:classSession ).save()
        }
        redirect(action:show,id:params.classSessionId)
    }

    def show = {
        def classSessionInstance = ClassSession.get( params.id )

        if(!classSessionInstance) {
            flash.message = "ClassSession not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ classSessionInstance : classSessionInstance ] }
    }

    def delete = {
        def classSessionInstance = ClassSession.get( params.id )
        if(classSessionInstance) {
            try {
                classSessionInstance.delete(flush:true)
                flash.message = "ClassSession ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ClassSession ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ClassSession not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def classSessionInstance = ClassSession.get( params.id )

        if(!classSessionInstance) {
            flash.message = "ClassSession not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ classSessionInstance : classSessionInstance ]
        }
    }

    def update = {
        def classSessionInstance = ClassSession.get( params.id )
        if(classSessionInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(classSessionInstance.version > version) {

                    classSessionInstance.errors.rejectValue("version", "classSession.optimistic.locking.failure", "Another user has updated this ClassSession while you were editing.")
                    render(view:'edit',model:[classSessionInstance:classSessionInstance])
                    return
                }
            }
            classSessionInstance.properties = params
            if(!classSessionInstance.hasErrors() && classSessionInstance.save()) {
                flash.message = "ClassSession ${params.id} updated"
                redirect(action:show,id:classSessionInstance.id)
            }
            else {
                render(view:'edit',model:[classSessionInstance:classSessionInstance])
            }
        }
        else {
            flash.message = "ClassSession not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def classSessionInstance = new ClassSession()
        classSessionInstance.properties = params
        if (!classSessionInstance.course) {
            classSessionInstance.course = Course.list([sort:'id', max:1, order:'asc'])[0]
        }
        
        def nac = courseService.nextAvailableLessonDates(classSessionInstance.course, new Date())
        nac.each {
            classSessionInstance.addToLessonDates(it)
        }
            
        return ['classSessionInstance':classSessionInstance]
    }

    def save = {
        def lessonDates = [:]
        // lesson Dates come in like this:
        // lesson_{lessonId} = '11/12/2009'
        params.findAll { it.key.startsWith('lesson_') }.each {
            def lessonId = it.key.split('_')[1]
            def date = it.value
            lessonDates[lessonId] = date
        }

        params.findAll { it.key.startsWith('lesson_') }.each {
            params.remove(it.key)
        }
       
        def dateFormat = 'MM/dd/yyyy'
        def startDate = params.remove('startDate')
        def classSessionInstance = new ClassSession(params)
        try {
            classSessionInstance.startDate = Date.parse(dateFormat, startDate)
        } catch (Exception e) {
        }
 
        if(!classSessionInstance.hasErrors() && classSessionInstance.save()) {
            lessonDates.each {
                classSessionInstance.addToLessonDates(new LessonDate(lesson : Lesson.get(it.key),
                                                      lessonDate : Date.parse(dateFormat, it.value)));
            }
            classSessionInstance.save()
            flash.message = "ClassSession ${classSessionInstance.id} created"
            redirect(action:show,id:classSessionInstance.id)
        }
        else {
            render(view:'create',model:[classSessionInstance:classSessionInstance])
        }
    }


    // Renders a PDF report of students/lesson dates for the
    // Class Session ID == params.id
    def attendanceSheet = {
        def reportWriter = classSessionService.getBlankAttendanceSheet(params.id)

        response.addHeader('content-disposition', "attachment; filename=attendanceSheet")
        reportWriter.writeTo(response)
    }

    def gradCerts = {
        def classSessionInstance = ClassSession.get( params.id )
        def lessonDates = classSessionInstance?.lessonDates

        def graduationDate = classSessionInstance.startDate
        if (lessonDates && lessonDates.size() > 0) {
            graduationDate = lessonDates.last().lessonDate
        }

        params['graduationDateParam'] = enrollio.formatDate(date:graduationDate)

        // Default Graduation Date to date of last class
        // TODO: Refactor to a Service, or else give classSession a graduationDate
        def students = classSessionInstance.enrollments.collect { 
            [STUDENT_NAME:it.student.fullName()]
        }

        // Set the background picture for the report using absolute URL
        // Haven't tried using a relative URL
        params['backgroundImageUrlParam'] =
            resource(dir:'images', file:'blank_certificate_background.jpg',
                     absolute:true)

        chain(controller:'jasper',
              action:'index',
              model:[data:students],params:params)
    }
    
}

