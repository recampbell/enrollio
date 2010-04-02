import org.bworks.bworksdb.util.TestKeys

// Note: BootStrap adds methods to FunctionalTestCase

class StudentFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def testDataService

    // utility method for going to student/show page
    void gotoStudentShow() {
        loginAs('bob', 'bobbobbob0')
        click("Students")

        def studentLink = byXPath("//a[starts-with(@name,'studentLink')]")
        studentLink = studentLink instanceof ArrayList ? studentLink[0] : studentLink
        studentLink.click()

    }

    // utility method for going to student/edit page
    void gotoStudentEdit() {
        gotoStudentShow()
        def studentLink = byXPath("//a[starts-with(@name,'editStudentLink')]")
        studentLink = studentLink instanceof ArrayList ? studentLink[0] : studentLink
        studentLink.click()
    }

    void testSearchBarOnStudentList() {
        // Contact search bar should be on the /contacts and the /contact/(some id) pages
        loginAs('bob', 'bobbobbob0')
        click("Students")
        assertNotNull byId('studentSearchButton')
        assertContentContains('Search')
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
        gotoStudentEdit()
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

    void testCancelEdit() {
        gotoStudentEdit()
        assertStatus 200
        
        form('editStudentForm') {
            lastName = "Baggins"
            birthDate = '4/20/1914'
        }
        // clicking 'cancel' must be outside the form,
        // if it's an href?
        click "Cancel"

        assertStatus 200
        assertTitleContains('Student:')

        shouldFail() {
            assertContentContains('Baggins')
        }

        shouldFail() {
            assertContentContains('Updated')
        }

        shouldFail() {
            assertContentContains('April 20, 1914')
        }
    }

    void testEditStudentBirthDate() {
        gotoStudentEdit()
        assertStatus 200
        
        form('editStudentForm') {
            birthDate = '1/1/2000'
            click "Save"
        }

        assertStatus 200
        assertTitleContains('Student:')
        assertContentContains('Updated')
        assertContentContains('January 1, 2000')
    }

    void testEditStudentInterests() {
        gotoStudentEdit()
        assertStatus 200
        
        def interestCheckboxen = byName('interestInCourse')

        // Make sure we see at least three possible Courses to be interested in
        assertTrue interestCheckboxen.size() >= 3

        // Should be interested in the adult prog.
        assertNotNull interestCheckboxen.find { 
            it.getParentNode().getTextContent() =~ /${ TestKeys.PROGRAM_ADULT_AEC }/ &&
            it.checked == true
        }

        // Should not be interested in mentorship course
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


        // We're interested in Mentorship course, so check it
        // and Save!
        mentorshipCheckbox.click()
        form('editStudentForm') {
            click "Save"
        }

        assertStatus 200

        // Should now be interested in the adult prog. and mentorship.
        // TODO: since combining students/show onto one page, this isn't 
        // reliable'
        // Need to find a way to inspect the DIV that contains each student
        // and see if it contains the TestKeys.PROGRAM_KIDS_AEC, blah blah
        // assertContentContains TestKeys.PROGRAM_ADULT_AEC
        // assertContentContains TestKeys.PROGRAM_MENTORSHIP

        // Still shouldn't be interested in kids prog.
        // shouldFail() {
            // assertContentContains TestKeys.PROGRAM_KIDS_AEC
        // }

    }

}
