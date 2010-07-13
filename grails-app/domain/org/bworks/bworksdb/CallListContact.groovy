package org.bworks.bworksdb
import org.bworks.bworksdb.auth.ShiroUser

class CallListContact {

    static belongsTo = [ course:Course, contact:Contact ]
    ShiroUser user
    int calls = 0

    static constraints = {
        user(nullable:true)
    }
}
