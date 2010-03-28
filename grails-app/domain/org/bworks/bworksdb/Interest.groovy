package org.bworks.bworksdb

class Interest {

    Boolean active
    Note note
    Student student
    Course course
    Date dateCreated
    Date lastUpdated
    Date signupDate = new Date()

    static belongsTo = [Student, Course]

    static constraints = {
        note(nullable:true, blank:true)
        active(nullable:false)
        lastUpdated(nullable:true, blank:true)
    }
}
