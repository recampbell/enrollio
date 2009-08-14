package org.bworks.bworksdb

class Interest {

    Boolean active
    Note note
    Student student
    Program program

    static belongsTo = [Student, Program]

    static constraints = {
        note(nullable:true, blank:true)
        active(nullable:false)
    }
}
