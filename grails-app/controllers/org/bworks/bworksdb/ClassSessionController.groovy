package org.bworks.bworksdb

class ClassSessionController {

    def courseService
    def classSessionService
    def attendanceService

    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', 
                             saveEnrollments:'POST', update:'POST',
                             printGradCerts: 'POST',
                             printWelcomeLetter: 'POST' ]

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ classSessionInstanceList: ClassSession.list( params ), classSessionInstanceTotal: ClassSession.count() ]
    }


    // ajax method to enroll studs on the fly
    def enrollStudent = {
        def studentInstance = Student.get(params.studentId)
        def classSessionInstance = ClassSession.get(params.classSessionId)
        def msg
        if (params.enroll == 'true') {
            msg = classSessionService.enrollStudent(studentInstance, classSessionInstance)
        }
        else {
            msg = classSessionService.disrollStudent(studentInstance, classSessionInstance)
        }

        render '<b>' + msg['enrolledStudents'] + '</b> students enrolled'
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

    def graduation = {
        def classSessionInstance = ClassSession.get( params.id )
        if(!classSessionInstance) {
            flash.message = "Class Session not found with id ${params.id}"
            redirect(action:list)
        }
        else { 
            
           def attendancesForSession =  classSessionService.attendancesForSession(classSessionInstance)
            return [ attendancesForSession : attendancesForSession,
                         classSessionInstance : classSessionInstance ] }

    }

    def certificates = {
        def classSessionInstance = ClassSession.get( params.id )
        if(!classSessionInstance) {
            flash.message = "Class Session not found with id ${params.id}"
            redirect(action:list)
        }
        else { 
            
           def attendancesForSession =  classSessionService.attendancesForSession(classSessionInstance)
            return [ attendancesForSession : attendancesForSession,
                         classSessionInstance : classSessionInstance ] }

    }

    def printGradCerts = {
        def classSessionInstance = ClassSession.get( params.id )
        def lessonDates = classSessionInstance?.lessonDates

        def graduationDate = classSessionInstance.startDate
        if (lessonDates && lessonDates.size() > 0) {
            graduationDate = lessonDates.last().lessonDate
        }

        params['graduationDateParam'] = enrollio.formatDate(date:graduationDate)

        // Default Graduation Date to date of last class
        // TODO: Refactor to a Service, or else give classSession a graduationDate

        // Set the background picture for the report using absolute URL
        // Haven't tried using a relative URL
        params['backgroundImageUrlParam'] =
            resource(dir:'images', file:'blank_certificate_background.jpg',
                     absolute:true)

        // have to convert to long, otherwise getAll barfs
        def studentIdList = request.getParameterValues('studentIds').collect {
            it.toLong()
        }

        if (studentIdList.size() > 0) {

            def students = Student.getAll(studentIdList)?.collect {
                [STUDENT_NAME:it.fullName()]
            }

            if (students) {

                chain(controller:'jasper',
                      action:'index',
                      model:[data:students],params:params)
            }
            else {
                render ''
            }
        }
        else {
            flash.message = "You must select at least one student."
            if (classSessionInstance) {
                redirect(action:'certificates', controller:'classSession', params:[id:classSessionInstance.id])
            }
            else {
                redirect(action:"oops", controller:"error")
            }

        }

    }

    def attendance = {
        def classSessionInstance = ClassSession.get( params.id )
        if(!classSessionInstance) {
            flash.message = "ClassSession not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            // Find closest class to highlight/show in attendance sheet.
            def closestLessonDate = classSessionService.closestLessonDate(classSessionInstance) 
            if (closestLessonDate) {
                // Init. attendances if need be.
                attendanceService.initializeAttendees(closestLessonDate)
                
                [ classSessionInstance : classSessionInstance,
                  closestLessonDate : closestLessonDate ]
            }
            else {
                flash.message = "You don't have any lesson dates scheduled for this session"
                redirect(action:show, id:classSessionInstance.id)
            }
        }
    }

    def printWelcomeLetter = {
        def classSessionInstance = ClassSession.get(params.id)
        if (classSessionInstance) {
            def reportData = classSessionService.welcomeLetterReportData(classSessionInstance)
            // Take the hash map found in 'forwardParams', and move it to the
            // params hash, and pass params to our report
            // forwardParams has single parameters like TITLE of report
            // params.add reportData.remove('forwardParams')
            
            params['PROGRAM_NAME'] = 'foo'
            reportData = reportData['contacts']
            println "report data is: " + reportData

            chain(controller:'jasper',
                  action:'index',
                  model:[data:reportData],params:params)
        }
    }

}

