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
}
