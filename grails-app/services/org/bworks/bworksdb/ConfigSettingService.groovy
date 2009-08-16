package org.bworks.bworksdb

class ConfigSettingService {

    boolean transactional = true

    def getSetting(key) {
        def setting = ConfigSetting.findByKeyAndIsDefault(key, false)
        if (!setting) {
            setting = ConfigSetting.findByKeyAndIsDefault(key, true)
        }
        return setting
    }

    def setSetting(key, value, description = '') {
        def setting = ConfigSetting.findByKey(key)
        if (!setting) {
            setting = new ConfigSetting()
        }

        setting.key = key
        setting.value = value
        setting.description = description
        setting.isDefault = false

        if (!setting.validate()) {
            return null
        }
        setting.save
        return setting
    }

}
