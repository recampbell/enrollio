class StudentFunctionalTests extends functionaltestplugin.FunctionalTestCase {
    // TODO loginAs should be refactored into a
    // common method -- it's also used in SecurityFiltersFunctionalTests
    void loginAs(userName, pass) {
        get('/login')
        form('loginForm') {
            username = userName
            password = pass
            click "login"
        }
    }

    // utility method for going to student/show page
    void gotoStudentShow() {
        loginAs('bob', 'bobbobbob0')
        click("Students")

        def studentLink = byXPath("//a[starts-with(@name,'studentLink')]")
        studentLink = studentLink instanceof ArrayList ? studentLink[0] : studentLink
        studentLink.click()
    }

    void testRegularUserGoesToStudentShow() {
        
        loginAs('bob', 'bobbobbob0')
        click("Students")
        assertStatus 200

        def studentLink = byXPath("//a[starts-with(@name,'studentLink')]")
        assertNotNull studentLink
        studentLink = studentLink instanceof ArrayList ? studentLink[0] : studentLink
        // Click on the link -- using its ID <evil laugh>
        studentLink.click()
        assertStatus 200
        assertTitleContains('Student:')
        assertContentContains('Interests:')
    }


}
