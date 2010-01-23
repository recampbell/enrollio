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
        click('Users')
        assertStatus 200

        assertTitleContains 'Users'
        assertContentContains 'bob'
        assertContentContains 'admin'
    }

    void testShowUserPage() {
        loginAdmin()
        click('Admin')
        click('Users')

        def adminLink = byName('userLink_admin')
        assertNotNull adminLink
        adminLink.click()
        assertStatus 200

        assertTitleContains "Show User: admin"
        assertContentContains "Bert"
        assertContentContains "Adminbadboy"

    }


    void testCreateUserPage() {
        loginAdmin()
        click('Admin')
        click('Create User')
        assertStatus 200

        assertTitleContains 'Create User'

        form('newUserForm') {
            username = "bazooka"
            firstName = "Joe"
            lastName = "Banana"

            // password must be 5 char and contain digit!
            password = "superserial0"
            passwordConfirm = "superserial0"

            click('Save')
            
        }

        assertStatus 200
        assertTitleContains "User: bazooka"
        assertContentContains "Banana"
        assertContentContains "Joe"

        // No password should be in the show page.
        shouldFail() {
            assertContentContains "superserial"
        }

    }
}
