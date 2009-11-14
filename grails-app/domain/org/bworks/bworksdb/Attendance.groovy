package org.bworks.bworksdb

class Attendance implements Comparable {
    
    static belongsTo = [Student, LessonDate]

    static mapping = {
        sort "student.lastName"
    }

    Student student
    LessonDate lessonDate
    String status
    
    static constraints = {
    }

    int compareTo(obj) {
        this.student.toString() <=> obj.student.toString()
    }
}




