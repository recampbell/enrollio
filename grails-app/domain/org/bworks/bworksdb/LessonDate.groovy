package org.bworks.bworksdb

class LessonDate {

    static belongsTo = [ClassSession, Lesson]
    static hasMany = [attendees:Attendance]

    ClassSession classSession
    Lesson lesson
    Date lessonDate
    
    static constraints = {
    }
}
