class SecurityFiltersFunctionalTests extends functionaltestplugin.FunctionalTestCase {
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
        get('/auth/login')
        form('loginForm') {
            username = "bob"
            password = "bobbobbob0"
            click "login"
            
        }
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
}
