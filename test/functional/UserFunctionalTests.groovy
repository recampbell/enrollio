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
    void testSettingsPage() {
        loginAdmin()
        click('Admin')
        click('Settings')
        assertStatus 200

        assertTitleContains 'Settings'
        assertContentContains 'Settings'
    }

    // The settings list test could be refactored
    // later to a SettingsFunctionalTests.groovy file
    void testUsersLink() {
        loginAdmin()
        click('Admin')
        click('List Users')
        assertStatus 200

        assertTitleContains 'User List'
        assertContentContains 'Username'
    }
    
    void testCreateUserLink() {
        loginAdmin()
        click('Admin')
        click('Create User')
        assertStatus 200
        assertContentContains "Create User"
        assertContentContains "Username"
        assertContentContains "Password"
    }
}
