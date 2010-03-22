class UrlMappings {
	static mappings = {

		// admin controller
		'/admin'(controller: 'admin', action: 'index')

		// auth controller
		'/login'(controller: 'auth', action: 'login')
		'/logout'(controller: 'auth', action: 'signOut')
		'/unauthorized' (controller: 'auth', action: 'unauthorized')
		'/signin'(controller: 'auth', action: 'signIn')

		// classSession controller
		'/classSessions'(controller: 'classSession', action: 'list')
		"/classSession/$id"(controller: 'classSession', action: 'show')
		'/createClassSession'(controller: 'classSession', action: 'create')
        '/saveClassSession'(controller: 'classSession', action: 'save')
		"/editEnrollments/$id"(controller: 'classSession', action: 'editEnrollments')
        "/gradCerts"(controller: 'classSession', action: 'gradCerts')
        "/attendanceSheet"(controller: 'classSession', action: 'attendanceSheet')

		// configSetting controller
        '/settings'(controller: 'configSetting', action: 'list')
        "/showSetting/$id"(controller: 'configSetting', action: 'show')
        "/editSetting/$id"(controller: 'configSetting', action: 'edit')
        "/updateSetting"(controller: 'configSetting', action: 'update')

		// contact controller
		'/contact'(controller: 'contact', action: 'index')
		'/contacts'(controller: 'contact', action: 'list')
		"/contact/$id"(controller: 'contact', action: 'show')
		"/createContact"(controller: 'contact', action: 'create')
		"/createStudent"(controller: 'contact', action: 'createStudent')
		"/saveStudent"(controller: 'contact', action: 'saveStudent')
		"/editContact/$id"(controller: 'contact', action: 'edit')

		// help controller
		"/"(controller:"help", action:'about')
        '/help'(controller: 'help', action: 'index')
        '/thanks'(controller: 'help', action: 'thanks')

		// home controller
		'/home'(controller: 'home', action: 'index')

		// interest controller
		"/interest/$id"(controller: 'interest', action: 'show')

		// lesson controller
		"/lesson/$id"(controller: 'lesson', action: 'show')
		'/createLesson'(controller: 'lesson', action: 'create')
		'/saveLesson'(controller: 'lesson', action: 'save')
		"/editLesson/$id"(controller: 'lesson', action: 'edit')
		"/updateLesson"(controller: 'lesson', action: 'update')
		"/lessons"(controller: 'lesson', action: 'list')

		// lessonDate controller
		"/lessonDate/$id"(controller: 'lessonDate', action: 'show')

        // jasper controller
        '/jasper/index'(controller:'jasper', action:'index')

		// course controller
		'/courses'(controller: 'course', action: 'list')
        '/course/callList'(controller: 'course', action: 'callList')
		"/course/$id"(controller: 'course', action: 'show')
		'/createCourse'(controller: 'course', action: 'create')
		'/saveCourse'(controller: 'course', action: 'save')
		"/editCourse/$id"(controller: 'course', action: 'edit')
		"/updateCourse"(controller: 'course', action: 'update')
		"/nextAvailableLessonDates"(controller: 'course', action: 'nextAvailableLessonDates')
		"/courseLessons/$id"(controller: 'course', action: 'lessons')
        "/sortLessons/$id"(controller: 'course', action: 'sortLessons')
        "/saveLessonSort"(controller: 'course', action: 'saveLessonSort')

		// shiroUser controller
		'/createUser'(controller: 'shiroUser', action: 'create')
		'/users'(controller: 'shiroUser', action: 'list')
		"/editUser/$username"(controller: 'shiroUser', action: 'edit')
		"/deleteUser/$id"(controller: 'shiroUser', action: 'delete')
		"/showUser/$username"(controller: 'shiroUser', action: 'show')
        "/updateUser"(controller: 'shiroUser', action: 'update')
        "/saveUser"(controller: 'shiroUser', action: 'save')

		// student controller
		'/students'(controller: 'student', action: 'list')
		"/student/$id"(controller: 'student', action: 'show')
		"/editStudent/$id"(controller: 'student', action: 'edit')
		"/updateStudent"(controller: 'student', action: 'update')

		// response codes
		"500"(view:'/error')
		"404"(controller:'error', action:'pageNotFound')
	}
}
