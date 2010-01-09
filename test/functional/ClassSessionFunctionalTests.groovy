import org.bworks.bworksdb.util.TestKeys
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

    // gotoClassSessionList is a utility method
    // that goes to the classSession/list page
    void gotoClassSessionList() {
        loginAs('bob', 'bobbobbob0')
        click("Class Sessions")
        // Make sure we see our newly formatted beautiful dates
        assertContentContains TestKeys.SESSION_KIDS_DATE_FORMATTED
        assertContentContains TestKeys.SESSION_ADULT_DATE_FORMATTED
        assertContentContains TestKeys.SESSION_MENTORSHIP_DATE_FORMATTED

    }

    // gotoClassSessionPage is a utility method
    // that, um, goes to a class session page
    void gotoClassSessionPage() {
        loginAs('bob', 'bobbobbob0')
        click("Class Sessions")
        
        def csLink = byXPath("//a[starts-with(@name,'classSessionLink')]")
        // if we got multiple links, then just get 1st one
        csLink = csLink instanceof ArrayList ? csLink[0] : csLink
        csLink.click()
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
    }

    // Test that Grad. Certs come out OK
    void testGradCerts() {

        gotoClassSessionPage()
        assertStatus 200
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
