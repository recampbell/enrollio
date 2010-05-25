package org.bworks.bworksdb

class UserSetting {

    String configKey
    String value

    // Users can choose to use the system default, yet
    // save their settings for later use/toggling later
    Boolean useSystemDefault = false

    static belongsTo = ShiroUser
    static constraints = {
        value(blank:true)
        useSystemDefault(nullable:true)
    }

    String toString() {
        value
    }
}
