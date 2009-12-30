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

        // configSetting controller
		'/settings'(controller: 'configSetting', action: 'list')

        // contact controller
        '/contact'(controller: 'contact', action: 'index')
        '/contacts'(controller: 'contact', action: 'list')
        "/contact/$id"(controller: 'contact', action: 'show')

        // help controller
        "/"(controller:"help", action:'about')
        '/help'(controller: 'help', action: 'index')

        // interest controller
		"/interest/$id"(controller: 'interest', action: 'show')

        // program controller
		'/programs'(controller: 'program', action: 'list')
		"/program/$id"(controller: 'program', action: 'show')

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
