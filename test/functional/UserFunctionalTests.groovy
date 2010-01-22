import org.bworks.bworksdb.util.TestKeys
class UserFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    // AdminFunctionalTests are primarily used to test admin's capabilities on
    // the admin page
    // For security tests, use SecurityFiltersFunctionalTests.
    def testDataService

    void loginAdmin() {
        get('/login')
        form('loginForm') {
            username = 'admin'
            password = 'admin0'
            click "login"
        }
    }

    // The settings list test could be refactored
    // later to a SettingsFunctionalTests.groovy file
    void testListUsersPage() {
        loginAdmin()
        click('Admin')
        click('List Users')
        assertStatus 200

        assertTitleContains 'Users'
        assertContentContains 'bob'
        assertContentContains 'admin'
    }

    void testShowUserPage() {
        loginAdmin()
        click('Admin')
        click('List Users')

        def adminLink = byName('userLink_admin')
        assertNotNull adminLink
        adminLink.click()
        assertStatus 200

        assertTitleContains "Show User: admin"
        assertContentContains "Bert"
        assertContentContains "Adminbadboy"

    }
}
