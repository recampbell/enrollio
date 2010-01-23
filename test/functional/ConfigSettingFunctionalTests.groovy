import org.bworks.bworksdb.util.TestKeys
class ConfigSettingFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def testDataService

    void loginAdmin() {
        get('/login')
        form('loginForm') {
            username = 'admin'
            password = 'admin0'
            click "login"
        }
    }
    

    void testShowSettingPage() {
        loginAdmin()
        click('Admin')
        click('Settings')
        def settingLink = byName('settingLink_1')
        assertNotNull settingLink
        settingLink.click()
        assertStatus 200
        assertTitleContains "Setting: "

        }
}
