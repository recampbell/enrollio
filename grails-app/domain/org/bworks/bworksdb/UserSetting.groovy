package org.bworks.bworksdb
import org.bworks.bworksdb.auth.ShiroUser

class UserSetting {

    String configKey
    String value

    static belongsTo = ShiroUser
    static constraints = {
        value(blank:true)
    }

    String toString() {
        value
    }
}
