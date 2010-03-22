package org.bworks.bworksdb

class Interest {

    Boolean active
    Note note
    Student student
    Program course
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Student, Program]

    static constraints = {
        note(nullable:true, blank:true)
        active(nullable:false)
        lastUpdated(nullable:true, blank:true)
    }
}
