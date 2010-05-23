package org.bworks.bworksdb

class ConfigSetting {

    String configKey
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

    static public final String DEFAULT_AREA_CODE = 'DEFAULT_AREA_CODE'
    static public final String DEFAULT_CITY = 'DEFAULT_CITY'
    static public final String DEFAULT_STATE = 'DEFAULT_STATE'
    static public final String MASCOT_ICON = 'MASCOT_ICON'
}
