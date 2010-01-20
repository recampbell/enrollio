import org.bworks.bworksdb.util.TestKeys
class MiscFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def testDataService

    void loginAs(userName, pass) {
        get('/login')
        form('loginForm') {
            username = userName
            password = pass
            click "login"
        }
    }

    void testPageNotFoundAdmin() {
        loginAs('admin', 'admin0')
        assertStatus 200
        get('/snoogins/someotheraction')
        assertStatus 404

        assertTitleContains 'Page Not Found'
        assertContentContains 'nathan.neff@gmail.com'
        assertNotNull byName('helpLink')
    }

    void testPageNotFoundBob() {
        loginAs('bob', 'bobbobbob0')
        assertStatus 200
        get('/somepage')
        assertStatus 404
        assertTitleContains 'Page Not Found'
        assertContentContains 'nathan.neff@gmail.com'
        assertNotNull byName('helpLink')
    }

}
