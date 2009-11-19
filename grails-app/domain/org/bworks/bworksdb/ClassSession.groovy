package org.bworks.bworksdb

class ClassSession {
    Date startDate
    String name
    Program program
    static belongsTo = [ Program ] 
    static hasMany = [ lessonDates:LessonDate, enrollments:Enrollment ]

    SortedSet lessonDates
    SortedSet enrollments
    static constraints = {
    }

    String toString() {
        program.name + ", " + name
    }
}
