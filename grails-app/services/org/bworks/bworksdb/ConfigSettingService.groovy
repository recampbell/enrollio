package org.bworks.bworksdb

class ConfigSettingService {

    boolean transactional = true

    def userService

    def getSetting(key) {
        def curUser = userService.loggedInUser()
        def setting
        if (curUser) {
            setting = curUser.userSettings.find {
                it.configKey == key
            }
            if (setting) { return setting }
        }

        setting = ConfigSetting.findByConfigKeyAndIsDefault(key, false)
        if (!setting) {
            setting = ConfigSetting.findByConfigKeyAndIsDefault(key, true)
        }
        return setting
    }

    def setSetting(key, value, description = '') {
        def setting = ConfigSetting.findByConfigKey(key)
        if (!setting) {
            setting = new ConfigSetting()
        }

        setting.configKey = key
        setting.value = value
        setting.description = description
        setting.isDefault = false

        if (!setting.validate()) {
            return null
        }
        setting.save()
        return setting
    }

    // create new UserSetting, unless useSystemSetting is true.
    // zap User Setting if useSystemSetting = true
    def setUserSetting(key, value) {
        def curUser = userService.loggedInUser()
        def setting
        if (curUser) {
            setting = curUser.userSettings.find {
                it.configKey == key
            }
        } else {
            // TODO Throw error
            return null
        }
        if (setting) {
            setting.value = value
            setting.save(flush:true)
        }
        else {
            // create a new UserSetting
            setting = new UserSetting(configKey:key, value:value).save(flush:true)
            curUser.addToUserSettings(setting);
        }
        curUser.save(flush:true)

        return setting
    }

    // returns a list of settings. Each setting is either specifically user-defined, or
    // defined by the system-wide setting.  User settings get precedence
    def userSettingsList() {
        def userSettings = []

        def crit = ConfigSetting.createCriteria()
        def results = crit.list() {
            projections {
                distinct('configKey')
            }
        }

        crit = UserSetting.createCriteria()
        crit.list() {
            projections {
                distinct('configKey')
            }
        }.each {
            results.add it
        }

        results.unique().each { configKey ->
            userSettings.add getSetting(configKey)
        }

        return userSettings
    }

    def systemSettingsList() {
    }

    // Use the system-wide configuration setting for configKey
    def useSystemSetting(configKey) {
        def curUser = userService.loggedInUser()
        if (curUser) {
            def setting = curUser.userSettings.find { it.configKey == configKey }
            if (setting) {
                curUser.removeFromUserSettings(setting)
                setting.delete()
                curUser.save()
            }
        }

    }

}
