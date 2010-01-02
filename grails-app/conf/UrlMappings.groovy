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
		'/createClassLesson'(controller: 'classSession', action: 'create')

		// configSetting controller
		'/settings'(controller: 'configSetting', action: 'list')

		// contact controller
		'/contact'(controller: 'contact', action: 'index')
		'/contacts'(controller: 'contact', action: 'list')
		"/contact/$id"(controller: 'contact', action: 'show')
		"/createContact"(controller: 'contact', action: 'create')

		// help controller
		"/"(controller:"help", action:'about')
		'/help'(controller: 'help', action: 'index')

		// home controller
		'/home'(controller: 'home', action: 'index')

		// interest controller
		"/interest/$id"(controller: 'interest', action: 'show')

		// lesson controller
		"/lesson/$id"(controller: 'lesson', action: 'show')
		'/createLesson'(controller: 'lesson', action: 'create')
		'/saveLesson'(controller: 'lesson', action: 'save')
		'/lessons'(controller: 'lesson', action: 'list')

		// lessonDate controller
		"/lessionDate/$id"(controller: 'lessonDate', action: 'show')

		// program controller
		'/programs'(controller: 'program', action: 'list')
		"/program/$id"(controller: 'program', action: 'show')
		'/createProgram'(controller: 'program', action: 'create')
		'/saveProgram'(controller: 'program', action: 'save')

		// shiroUser controller
		'/createUser'(controller: 'shiroUser', action: 'create')
		'/users'(controller: 'shiroUser', action: 'list')
		"/editUser/$id"(controller: 'shiroUser', action: 'edit')
		"/deleteUser/$id"(controller: 'shiroUser', action: 'delete')

		// student controller
		'/students'(controller: 'student', action: 'list')
		"/student/$id"(controller: 'student', action: 'show')

		// response codes
		"500"(view:'/error')
	}
}
