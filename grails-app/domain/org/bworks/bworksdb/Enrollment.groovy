package org.bworks.bworksdb

class Enrollment {
    static belongsTo = [Student,ClassSession]
    Student student
    ClassSession classSession

    static constraints = {
    }
}
