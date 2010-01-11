import org.bworks.bworksdb.util.TestKeys
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
        // Go to Children's prog, and ensure that we
        // see the start date of the first session in our awesome format
        click(TestKeys.PROGRAM_KIDS_AEC)

    }

    void testGotoProgramLessonsList() {
        gotoProgramShow()
        assertContentContains(TestKeys.LESSON_KIDS_AEC_INTRO)
        click('Lessons')
        assertStatus 200
        assertContentContains(TestKeys.LESSON_KIDS_AEC_INTRO)
        assertContentContains(TestKeys.LESSON_KIDS_AEC_INTRO_DESCRIPTION)
    }

    void testProgramList() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        assertStatus 200
        assertTitleContains("Programs")
        assertContentContains("Children's EAC")
    }

    void testProgramShow() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        assertStatus 200
        click(TestKeys.PROGRAM_KIDS_AEC)
        assertContentContains TestKeys.SESSION_KIDS_DATE_FORMATTED

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
        form('newProgramForm') {
            name = "Ricky's Program"
            description = 'Only for Ricky (and maybe Bubbles)'
            click 'Save'
        }
        assert 200
        assertTitleContains "Program: Ricky's Program"
        assertContentContains "Only for Ricky (and maybe Bubbles)"
            
    }

    void testNewProgramFailed() {
        loginAs('bob', 'bobbobbob0')
        click("Programs")
        assertStatus 200
        click("newProgramLink")
        assertStatus 200
        assertTitleContains("Create Program")
        form('newProgramForm') {
            name = "Invalid Program"
            description = ''
            click 'Save'
        }
        assert 200
        assertTitleContains "Create Program"
        assertContentContains "description must have a value"

        // Now, fill in description, but not name
        form('newProgramForm') {
            name = ""
            description = "Another Invalid Program, this time there's no name"
            click 'Save'
        }
        assert 200
        assertTitleContains "Create Program"
        assertContentContains "name must have a value"
            
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
        assertTitleContains('Edit Program:')

        // Test an invalid URL
        get("/editProgram")
        assertStatus 404
        
    }

    void testProgramEditCancel() {
        gotoProgramShow()
        def editLink = byXPath("//a[starts-with(@name,'editProgramLink')]")
        assertNotNull editLink
        editLink.click()
        assertStatus 200

        def cancelLink = byName('cancelLink')
        assertNotNull cancelLink
        cancelLink.click()
        assertStatus 200
        assertTitleContains('Program:')

    }
    void testProgramBadEdit() {
        gotoProgramShow()
        def editLink = byXPath("//a[starts-with(@name,'editProgramLink')]")
        assertNotNull editLink
        editLink.click()
        assertStatus 200

        form('editProgramForm') {
            name = ""
            description = ""
        }

        def saveButton = byId('saveButton')
        assertNotNull saveButton
        saveButton.click()

        assertStatus 200
        assertTitleContains('Edit Program:')

    }
}
