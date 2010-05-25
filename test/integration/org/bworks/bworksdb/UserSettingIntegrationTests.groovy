package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.auth.ShiroUser
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken

class UserSettingIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    def userService
    def configSettingService
    // basic test to make sure that Bob gets his own settings
    // versus system-wide settings
    void testConfigSettingServiceIntegration() {

        configSettingService.setSetting(ConfigSetting.DEFAULT_STATE, 'MO')
        // pretend like 'bob' is logged in
        userService.metaClass.loggedInUser = {
            return ShiroUser.findByUsername('bob')
        }

        userService = new UserService()

        assertEquals 'MO', configSettingService.getSetting(ConfigSetting.DEFAULT_STATE).toString()

        def user = ShiroUser.findByUsername('bob')
        configSettingService.setUserSetting(ConfigSetting.DEFAULT_STATE, 'IL')
        assert user.userSettings.find { 
            it.configKey == ConfigSetting.DEFAULT_STATE
        }

        assertEquals 'IL', configSettingService.getSetting(ConfigSetting.DEFAULT_STATE).toString()
        configSettingService.setUserSetting(ConfigSetting.DEFAULT_STATE, 'WA')
        assertEquals 'WA', configSettingService.getSetting(ConfigSetting.DEFAULT_STATE).toString()

    }

}
