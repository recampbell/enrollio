package org.bworks.bworksdb

import grails.test.*
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

    // basic test to make sure that Bob gets his own settings
    // versus system-wide settings
    void testConfigSettingServiceIntegration() {
        // def authToken = new UsernamePasswordToken('bob', 'bobbobbob0')

        /*
        SecurityUtils.subject.login(authToken)

        assertEquals 'MO', ConfigSettingService.getSetting(ConfigSetting.DEFAULT_STATE)

        def user = ShiroUser.findByUsername('bob')
        user.addToUserSettings(new UserSetting(name:ConfigSetting.DEFAULT_STATE), value:'IL')
        user.save()

        assertEquals 'IL', ConfigSettingService.getSetting(ConfigSetting.DEFAULT_STATE)
        */


    }
}
