package org.bworks.bworksdb

class UserSetting {

    String configKey
    String value

    static belongsTo = ShiroUser
    static constraints = {
        value(blank:true)
        useSystemDefault(nullable:true)
    }

    String toString() {
        value
    }
}
