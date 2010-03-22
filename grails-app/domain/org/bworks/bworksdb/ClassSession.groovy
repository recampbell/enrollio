package org.bworks.bworksdb

class ClassSession {
    Date startDate
    String name
    Course course
    static belongsTo = [ Course ] 
    static hasMany = [ lessonDates:LessonDate, enrollments:Enrollment ]

    SortedSet lessonDates
    SortedSet enrollments
    static constraints = {
    }

    String toString() {
        course.name + ", " + name
    }
}
