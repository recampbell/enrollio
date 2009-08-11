package org.bworks.bworksdb

class Program {

    String name
    String description
    // Tells program that lessons need to be sorted, meow.
    SortedSet lessons
    static hasMany = [ lessons:Lesson, classSessions:ClassSession, interests:Interest ]
    
    static constraints = {
    }
}
