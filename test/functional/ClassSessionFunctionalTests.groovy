import org.bworks.bworksdb.util.TestKeys

// Note: BootStrap adds methods to FunctionalTestCase

class ClassSessionFunctionalTests extends functionaltestplugin.FunctionalTestCase {
    // gotoClassSessionList is a utility method
    // that goes to the classSession/list page
    void gotoClassSessionList() {
        System.err.println "TRACER cp 1"
        loginAs('bob', 'bobbobbob0')
        System.err.println "TRACER cp 2"
        click("Class Sessions")
        // Make sure we see our newly formatted beautiful dates
        assertContentContains TestKeys.SESSION_KIDS_DATE_FORMATTED
        assertContentContains TestKeys.SESSION_ADULT_DATE_FORMATTED
        assertContentContains TestKeys.SESSION_MENTORSHIP_DATE_FORMATTED

    }

    // gotoClassSessionPage is a utility method
    // that, um, goes to a class session page
    void gotoClassSessionPage(sessionName) {
        loginAs('bob', 'bobbobbob0')
        click("Class Sessions")

        if (sessionName) {
            // select explicit session
            click(sessionName)
        }
        else {
            def xpathExpr = "//a[starts-with(@name,'classSessionLink')]"
            def csLink = byXPath(xpathExpr)
            // if we got multiple links, then just get 1st one
            csLink = csLink instanceof ArrayList ? csLink[0] : csLink
            csLink.click()
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

    void testClassSessionShow() {
        loginAs('bob', 'bobbobbob0')
        click("Class Sessions")

        assertStatus 200
        click(TestKeys.SESSION_ADULT_NAME)
        // Check awesome date format
        assertContentContains TestKeys.SESSION_ADULT_DATE_FORMATTED
        // Ensure that STUDENT is enrolled in this program.
        assertContentContains TestKeys.STUDENT
    }

    void testLessonDatesShown() {
        loginAs('bob', 'bobbobbob0')
        click("Class Sessions")

        assertStatus 200
        click(TestKeys.SESSION_KIDS_NAME)
        assertContentContains TestKeys.LESSON_KIDS_AEC_INTRO
        assertContentContains 'Scratch Programming' 
        assertContentContains 'Word Processing'
        assertContentContains 'Presentations'
        assertContentContains 'Email and WWW'
        assertContentContains 'Graduation'
        // Ensure that STUDENT2 is enrolled in this program
        assertContentContains TestKeys.STUDENT2
    }


    // Test that Grad. Certs come out OK
    void testGradCerts() {

        gotoClassSessionPage(TestKeys.SESSION_KIDS_NAME)
        assertStatus 200
        assertContentContains TestKeys.LESSON_KIDS_AEC_INTRO
        assertContentContains 'Scratch Programming' 
        // Click on grad list, and expect a PDF
        // NOTE: For some reason (probably javascript), the tests
        // will *not* follow the redirect, so you have to manually call followRedirect()
        redirectEnabled = false
        def gradCertsLink = byName('gradCertsLink')
        assertNotNull gradCertsLink
        gradCertsLink.click()
        assertStatus 302
        followRedirect()
        assertStatus 200
        assertContentType "application/pdf"
    }

    // Test grad certs for a class session w/no lesson dates
    void testGradCertsNoLessons() {
        gotoClassSessionPage(TestKeys.SESSION_ADULT_NAME)
        assertStatus(200)
        redirectEnabled = false
        def gradCertsLink = byName('gradCertsLink')
        assertNotNull gradCertsLink
        gradCertsLink.click()
        assertStatus 302
        followRedirect()
        assertStatus 200
        assertContentType "application/pdf"
    }

    // Test Attendance Sheet comes out OK
    void testAttendanceSheet() {
        gotoClassSessionPage()
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
        gotoClassSessionList()
        def newLink = byName('newClassSessionLink')
        assertNotNull newLink
        newLink.click()
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
