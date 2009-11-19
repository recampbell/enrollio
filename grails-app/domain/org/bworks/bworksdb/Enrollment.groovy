package org.bworks.bworksdb

class Enrollment implements Comparable {
    static belongsTo = [Student,ClassSession]
    Student student
    ClassSession classSession

    String status

    static constraints = {
        status(nullable:true, blank:true)
    }

    int compareTo(obj) {
        this.student.toString() <=> obj.student.toString()
    }
}
