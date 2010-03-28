
// Note: BootStrap adds methods to FunctionalTestCase

class SecurityFiltersFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    void testHelpNoLogin() {
        // Here call get(uri) or post(uri) to start the session
        // and then use the custom assertXXXX calls etc to check the response
        //
        get('/help')
        assertTitleContains "Help"
        // assertContentContains 'the expected text'
    }

    void testNoSettingsListForRegularUser() {
        redirectEnabled = false
        loginAs("bob", "bobbobbob0")
        
        // Make sure that user cannot go to any settings action
        get('/settings')
        assertRedirectUrlContains "/unauthorized"

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

    void testNoLoadDataForRegularUser() {
        loginAs("bob", "bobbobbob0")
        redirectEnabled = false

        get('/loadDataRequest')
        assertRedirectUrlContains "/unauthorized"

        post('/loadDataFromFile')
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

    void testAdminCreateUser() {
        loginAs("admin", "admin0")
        redirectEnabled = false
        get('/createUser')
        assertStatus 200
        assertContentContains "Create User"
        assertContentContains "Username"
        assertContentContains "Password"
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
