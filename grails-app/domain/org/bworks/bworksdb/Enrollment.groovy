package org.bworks.bworksdb

class Enrollment implements Comparable {
    static belongsTo = [Student,ClassSession]
    Student student
    ClassSession classSession

    EnrollmentStatus status = EnrollmentStatus.IN_PROGRESS

    static constraints = {
        status(nullable:true, blank:true)
    }

    int compareTo(obj) {
        this.student.toString() + this.student.id <=> 
        obj.student.toString() + obj.student.id
    }
}
