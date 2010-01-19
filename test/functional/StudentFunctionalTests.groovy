import org.bworks.bworksdb.util.TestKeys
class StudentFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    // TODO loginAs should be refactored into a
    // common method -- it's also used in SecurityFiltersFunctionalTests
    def testDataService

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
        // click on the link -- using its id <evil laugh>
        studentLink.click()
        assertStatus 200
        assertTitleContains('student:')
        assertContentContains('interests:')
    }

    void testEditStudentName() {
        gotoStudentShow()
        assertStatus 200
        
        click "Edit"
        assertStatus 200

        form('editStudentForm') {
            firstName = "Ralph"
            lastName = "Lauren"
            click "Save"
        }

        assertStatus 200
        assertTitleContains('Student:')
        assertContentContains('Updated')
        assertContentContains('Ralph')
        assertContentContains('Lauren')

    }

    void testEditStudentInterests() {
        testDataService.deleteIntegrationTestData()
        testDataService.loadIntegrationTestData()
        gotoStudentShow()
        assertStatus 200
        
        def editLink = byName("editStudentLink")
        assertNotNull editLink
        click "Edit"
        assertStatus 200

        
        def interestCheckboxen = byName('interestInProgram')

        // Make sure we see three possible Programs to be interested in
        assertEquals 3, interestCheckboxen.size()

        interestCheckboxen.each {
            println it.toString()
        }

        // Should be interested in the adult prog.
        assertNotNull interestCheckboxen.find { 
            it.getParentNode().getTextContent() =~ /${ TestKeys.PROGRAM_ADULT_AEC }/ &&
            it.checked == true
        }

        // Should not be interested in mentorship program
        // Save mentoship checkbox for later use
        def mentorshipCheckbox = interestCheckboxen.find { 
            it.getParentNode().getTextContent() =~ /${ TestKeys.PROGRAM_MENTORSHIP }/ &&
            it.checked == false
        }

        assertNotNull mentorshipCheckbox
        assertFalse mentorshipCheckbox.checked

        // Should not be interested in children's prog
        assertNotNull interestCheckboxen.find { 
            it.getParentNode().getTextContent() =~ /${TestKeys.PROGRAM_KIDS_AEC}/ &&
            it.checked == false
        }


        // We're interested in Mentorship program, so check it
        // and Save!
        mentorshipCheckbox.click()
        form('editStudentForm') {
            click "Save"
        }

        assertStatus 200
        // Make sure interests are g00t
        def newInterestCheckBoxen = byName('interestInProgram')

        // Should now be interested in the adult prog. and mentorship.
        assertContentContains TestKeys.PROGRAM_ADULT_AEC
        assertContentContains TestKeys.PROGRAM_MENTORSHIP
        // Still shouldn't be interested in kids prog.
        shouldFail() {
            assertContentContains TestKeys.PROGRAM_KIDS_AEC
        }

    }

    void testStudentContactLink() {
        gotoStudentShow()
        def studentsContactLink = byName('contactLink')
        assertNotNull studentsContactLink
        studentsContactLink.click()
        assertStatus 200
        assertTitleContains 'Contact'
        assertContentContains TestKeys.CONTACT_EMAIL
        assertContentContains TestKeys.CONTACT1_FIRST_NAME
        assertContentContains TestKeys.CONTACT1_LAST_NAME
    }
}
