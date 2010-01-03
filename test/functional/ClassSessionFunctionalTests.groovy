class ClassSessionFunctionalTests extends functionaltestplugin.FunctionalTestCase {
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

    // Make sure we can get to the add/edit enrollments page
    // for a Class Session
    void testAddEnrollments() {
        loginAs('bob', 'bobbobbob0')
        click("Class Sessions")
        assertStatus 200
        def csLink = byXPath("//a[starts-with(@name,'classSessionLink')]")
        assertNotNull csLink
        // if we got multiple links, then just get 1st one
        csLink = csLink instanceof ArrayList ? csLink[0] : csLink
        csLink.click()
        assertStatus 200
        def addEnrollmentsLink = byName('editEnrollmentsLink')
        assertNotNull addEnrollmentsLink
        addEnrollmentsLink.click()
        assertStatus 200

    }
}
