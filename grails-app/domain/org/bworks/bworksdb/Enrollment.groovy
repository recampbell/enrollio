package org.bworks.bworksdb

class Enrollment {
    static belongsTo = [Student,ClassSession]
    Student student
    ClassSession classSession

    String status

    static constraints = {
        status(nullable:true, blank:true)
    }
}
