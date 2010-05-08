package org.bworks.bworksdb

class Course {

    String name
    String description
    // Tells course that lessons need to be sorted, meow.
    SortedSet lessons
    static hasMany = [ lessons:Lesson, classSessions:ClassSession, interests:Interest ]
    
    static mapping = {
        classSessions sort: "startDate", order: 'desc'
    }
    
    static constraints = {
        name(nullable:false, blank:false)
        description(nullable:false, blank:false)
    }
    
    String toString() {
        name
    }
}
