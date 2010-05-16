package org.bworks.bworksdb
import org.bworks.bworksdb.auth.ShiroUser

class CallListContact {

    Contact contact
    ClassSession classSession

    ShiroUser user
    int calls = 0

    static constraints = {
    }
}
