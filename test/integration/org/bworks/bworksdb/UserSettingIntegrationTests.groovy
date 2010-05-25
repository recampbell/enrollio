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

        assertEquals 'MO', configSettingService.getSetting(ConfigSetting.DEFAULT_STATE).toString()

        def user = ShiroUser.findByUsername('bob')
        user.addToUserSettings(new UserSetting(configKey:ConfigSetting.DEFAULT_STATE, value:'IL'))
        user.save()

        assertEquals 'IL', configSettingService.getSetting(ConfigSetting.DEFAULT_STATE).toString()

    }
}
