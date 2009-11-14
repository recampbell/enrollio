package org.bworks.bworksdb

class Attendance {
    
    static belongsTo = [Student, LessonDate]

    Student student
    LessonDate lessonDate
    String attendanceStatus
    
    static constraints = {
    }
}
