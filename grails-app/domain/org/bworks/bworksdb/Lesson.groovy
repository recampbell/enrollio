package org.bworks.bworksdb

class Lesson implements Comparable {
    String name 
    String description
    Integer sequence
    Course course
    static belongsTo = [Course]
    static hasMany = [lessonDates:LessonDate]
    static constraints = {
    }

    // We sort by sequence in these here parts.
    int compareTo(obj) {
        sequence.compareTo(obj.sequence)
    }

    String toString() {
        name
    }

}
