package org.bworks.bworksdb

class Program {

    String name
    String description
    // Tells program that lessons need to be sorted, meow.
    SortedSet lessons
    static hasMany = [ lessons:Lesson, classSessions:ClassSession, interests:Interest ]
    
    static constraints = {
        name(nullable:false, blank:false)
        description(nullable:false, blank:false)
    }
    
    String toString() {
        name
    }
}
