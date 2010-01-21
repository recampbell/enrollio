import org.bworks.bworksdb.util.TestKeys
class GradCertsFunctionalTests extends functionaltestplugin.FunctionalTestCase {
    // gotoClassSessionPage is a utility method
    // that, um, goes to a class session page
    void loginAs(userName, pass) {
        get('/login')
        form('loginForm') {
            username = userName
            password = pass
            click "login"
        }
    }

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
}

