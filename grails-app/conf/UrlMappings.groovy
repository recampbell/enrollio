class UrlMappings {
    static mappings = {

        // admin controller
        '/admin'(controller: 'admin', action: 'index')  

        // auth controller
        '/login'(controller: 'auth', action: 'login')
        '/unauthorized' (controller: 'auth', action: 'unauthorized')
        '/signin'(controller: 'auth', action: 'signIn')

        // contact controller
        '/contact'(controller: 'contact', action: 'index')

        // help controller
        "/"(controller:"help", action:'about')
        '/help'(controller: 'help', action: 'index')

        // shiroUser controller
        '/createUser'(controller: 'shiroUser', action: 'create')
        "/editUser/$id"(controller: 'shiroUser', action: 'edit')
        "/deleteUser/$id"(controller: 'shiroUser', action: 'delete')

        // response codes
        "500"(view:'/error')
    }
}
