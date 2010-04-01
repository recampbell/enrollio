import org.bworks.bworksdb.util.TestKeys

// Note: BootStrap adds methods to FunctionalTestCase

class ClassSessionFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    // gotoClassSessionPage is a utility method
    // that, um, goes to a class session page
    void gotoClassSessionPage(courseName, sessionName) {
        loginAs('bob', 'bobbobbob0')
        click('Courses')
        click(courseName)
        assertStatus 200

        // select explicit session
        click(sessionName)
    }
    
    // Make sure we can get to the add/edit enrollments page
    // for a Class Session
    void testAddEnrollments() {
        gotoClassSessionPage(TestKeys.PROGRAM_KIDS_AEC, TestKeys.SESSION_KIDS_NAME)
        def addEnrollmentsLink = byName('editEnrollmentsLink')
        assertNotNull addEnrollmentsLink
        addEnrollmentsLink.click()
        assertStatus 200

    }

    void testClassSessionShow() {
        gotoClassSessionPage(TestKeys.PROGRAM_ADULT_AEC, TestKeys.SESSION_ADULT_NAME)

        // Check awesome date format
        assertContentContains TestKeys.SESSION_ADULT_DATE_FORMATTED
        // Ensure that STUDENT is enrolled in this course.
        assertContentContains TestKeys.STUDENT
    }

    void testLessonDatesShown() {
        gotoClassSessionPage(TestKeys.PROGRAM_KIDS_AEC, TestKeys.SESSION_KIDS_NAME)

        assertContentContains TestKeys.LESSON_KIDS_AEC_INTRO
        assertContentContains 'Scratch Programming' 
        assertContentContains 'Word Processing'
        assertContentContains 'Presentations'
        assertContentContains 'Email and WWW'
        assertContentContains 'Graduation'
        // Ensure that STUDENT2 is enrolled in this course
        assertContentContains TestKeys.STUDENT2
    }


    // Test that Grad. Certs come out OK
    void testGradCerts() {

        gotoClassSessionPage(TestKeys.PROGRAM_KIDS_AEC, TestKeys.SESSION_KIDS_NAME)
        assertStatus 200
        assertContentContains TestKeys.LESSON_KIDS_AEC_INTRO
        assertContentContains 'Scratch Programming' 
        // Click on grad list, and expect a PDF
        // NOTE: For some reason (probably javascript), the tests
        // will *not* follow the redirect, so you have to manually call followRedirect()
        redirectEnabled = false
        def printGradCertsLink = byName('printGradCertsLink')
        assertNotNull printGradCertsLink
        printGradCertsLink.click()
        assertStatus 302
        followRedirect()
        assertStatus 200
        assertContentType "application/pdf"
    }

    // Test grad certs for a class session w/no lesson dates
    void testGradCertsNoLessons() {
        gotoClassSessionPage(TestKeys.PROGRAM_ADULT_AEC, TestKeys.SESSION_ADULT_NAME)
        assertStatus(200)
        redirectEnabled = false
        def printGradCertsLink = byName('printGradCertsLink')
        assertNotNull printGradCertsLink
        printGradCertsLink.click()
        assertStatus 302
        followRedirect()
        assertStatus 200
        assertContentType "application/pdf"
    }

    // Test Attendance Sheet comes out OK
    void testAttendanceSheet() {
        gotoClassSessionPage(TestKeys.PROGRAM_KIDS_AEC, TestKeys.SESSION_KIDS_NAME)
        assertStatus 200
        // Click on sheet, and expect a PDF
        // NOTE: For some reason (probably javascript), the tests
        // will *not* follow the redirect, so you have to manually call followRedirect()
        def attendanceSheetLink = byName('attendanceSheetLink')
        assertNotNull attendanceSheetLink
        attendanceSheetLink.click()
        assertStatus 200
        assertHeaderContains('Content-Disposition', 'filename=attendanceSheet')
    }

    void testNewClassSession() {
        loginAs('bob', 'bobbobbob0')
        click("Courses")
        // Go to Children's prog, and ensure that we
        // see the start date of the first session in our awesome format
        click(TestKeys.PROGRAM_KIDS_AEC)
        click('New Session')
        assertStatus 200
        assertTitleContains('New Class Session')
        form('newClassSessionForm') {
            name = 'Foo Foo Zip Zap Class Session'
            startDate = '1/1/2010'
            click "Save"
        }
        assertStatus 200
        assertTitleContains('Foo Foo Zip Zap')
        assertContentContains('Foo Foo Zip Zap Class Session')
    }
}
