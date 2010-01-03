class ProgramFunctionalTests extends functionaltestplugin.FunctionalTestCase {
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

    // method: gotoProgramShow
    //         utility method to do all the clicky stuff
    //         to get to a program/show page
    void gotoProgramShow() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        def progLink = byXPath("//a[starts-with(@name,'programLink')]")
        // if we get > 1 link back, we have to pick 1st one.
        progLink = progLink instanceof ArrayList ? progLink[0] : progLink
        progLink.click()
    }

    void testProgramList() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        assertStatus 200
        assertTitleContains("Programs")
        assertContentContains("Children's EAC")
    }

    // Ensure that we can create a new Program
    void testProgramMenu() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        assertStatus 200
        assertNotNull byName('programMenu')

    }

    void testNewProgram() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        assertStatus 200
        click("newProgramLink")
        assertStatus 200
        assertTitleContains("Create Program")
    }

    void testCallList() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        assertStatus 200
        // find first a>href that starts with programLink
        // -- it's a link to a Program.
        def progLink = byXPath("//a[starts-with(@name,'programLink')]")
        assertNotNull progLink
        // if we get > 1 link back, we have to pick 1st one.
        progLink = progLink instanceof ArrayList ? progLink[0] : progLink
        // click the link to go to program/show
        progLink.click()
        assertStatus 200
        def callListLink = byName('callListLink')
        assertNotNull callListLink
        // Click on call list, and expect a PDF
        // NOTE: For some reason (probably javascript), the tests
        // will *not* follow the redirect, so you have to manually call followRedirect()
        redirectEnabled = false
        callListLink.click()
        assertStatus 302
        followRedirect()
        assertStatus 200
        assertContentType "application/pdf"

    }

    void testProgramEdit() {
        gotoProgramShow()
        def editLink = byXPath("//a[starts-with(@name,'editProgramLink')]")
        assertNotNull editLink
        editLink.click()
        assertStatus 200

        // Test an invalid URL
        get("/editProgram")
        assertStatus 404
        
    }
}
