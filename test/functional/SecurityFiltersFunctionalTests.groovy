class SecurityFiltersFunctionalTests extends functionaltestplugin.FunctionalTestCase {
    void loginAs(userName, pass) {
        get('/login')
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
        assertRedirectUrlContains "/unauthorized"
    }

    void testNonLoggedInUser() {
        redirectEnabled = false
        get('/contact')
        
        assertRedirectUrlContains "/login"
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
        get('/editUser/1')
        
        assertRedirectUrlContains "/unauthorized"
    }
    void testUserCannotCreateUser() {
        loginAs("bob", "bobbobbob0")
        redirectEnabled = false
        assertStatus 200
        get('/createUser')

        assertRedirectUrlContains "/unauthorized"
    }

    void testUserCannotDeleteUser() {
        loginAs("bob", "bobbobbob0")
        redirectEnabled = false
        assertStatus 200
        get('/deleteUser/1')

        assertRedirectUrlContains "/unauthorized"
    }

    void testAdminCanCreateContact() {
        loginAs("admin", "admin0")
        redirectEnabled = false
        assertStatus 200
        get('/createContact')

        assertTitleContains "Create Contact"
    }
   
}
