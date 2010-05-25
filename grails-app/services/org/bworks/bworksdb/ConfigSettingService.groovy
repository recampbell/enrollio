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

}
