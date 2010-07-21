package org.bworks.bworksdb

class MiscService {

    boolean transactional = true

    def pmFormat = 'MM/dd/yyyy h:mm a'

    def parseDate(date, hour_min, ampm) {
        def d = Date.parse(pmFormat, date + ' ' + hour_min + ' ' + ampm)
        return d
    }
}

