package org.bworks.bworksdb

class LessonDate {

    static belongsTo = [ ClassSession, Lesson ]
    static hasMany = [ attendees : Attendance ]

    SortedSet attendees
    ClassSession classSession
    Lesson lesson
    Date lessonDate
    
    static constraints = {
    }
}
