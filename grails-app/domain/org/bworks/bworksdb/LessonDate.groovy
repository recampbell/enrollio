package org.bworks.bworksdb

class LessonDate {

    static belongsTo = [ClassSession,Lesson]
    static hasMany = [attendees:Attendance]
    Lesson lesson
    Date lessonDate
    
    static constraints = {
    }
}
