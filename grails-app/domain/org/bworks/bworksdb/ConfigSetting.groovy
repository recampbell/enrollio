package org.bworks.bworksdb

class ConfigSetting {

    String key
    String value
    String description
    Boolean isDefault

    static constraints = {
        value(blank:true)
        description(nullable:true, blank:true)
    }

    String toString() {
        value
    }
}
