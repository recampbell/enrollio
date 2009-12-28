class SecurityFiltersFunctionalTests extends functionaltestplugin.FunctionalTestCase {
    void loginAs(userName, pass) {
        get('/auth/login')
        form('loginForm') {
            username = userName
            password = pass
            click "login"
        }
    }

    void testHelpNoLogin() {
        // Here call get(uri) or post(uri) to start the session
        // and then use the custom assertXXXX calls etc to check the response
        //
        get('/help')
        assertTitleContains "Help"
        // assertContentContains 'the expected text'
    }

    void testNoAdminForRegularUser() {
        redirectEnabled = false
        loginAs("bob", "bobbobbob0")
            
        
        // assertRedirectUrl('/')
        // User is logged in.

        // Make sure that user cannot go to admin page
        get('/admin')
        assertRedirectUrlContains "auth/unauthorized"
    }

    void testNonLoggedInUser() {
        redirectEnabled = false
        get('/contact')
        
        assertRedirectUrlContains "/auth/login"
    }

    void testAdminCanGoToAdmin() {
        loginAs("admin", "admin0")

    
        assertStatus 200
        assertContentContains "Welcome"
        assertContentContains "admin"
    
        get('/admin')
        assertStatus 200
       


    }

    void testUserCannotEditUser() {
        loginAs("bob", "bobbobbob0")
        redirectEnabled = false
        assertStatus 200
        get('/shiroUser/edit/1')
        
        assertRedirectUrlContains "auth/unauthorized"
    }
    void testUserCannotCreateUser() {
        loginAs("bob", "bobbobbob0")
        redirectEnabled = false
        assertStatus 200
        get('/shiroUser/create')

        assertRedirectUrlContains "auth/unauthorized"
    }

}
