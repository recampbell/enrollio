package org.bworks.bworksdb

class Student {
    
    String firstName
    String lastName
    String middleName
    Date birthDate
    String gender
    Integer grade
    String emailAddress
    Contact contact
    static belongsTo = [Contact]
    static hasMany = [ interests:Interest ]

    static constraints = {
        birthDate(nullable:true, blank:true)
        emailAddress(nullable:true, blank:true)
        firstName(nullable:true, blank:true)
        gender(nullable:true, blank:true)
        grade(nullable:true, blank:true)
        lastName(nullable:false, blank:false)
        middleName(nullable:true, blank:true)
    }
    
    String toString(){
        def fullName
        // Is this really the best we can do?
        fullName = lastName
        if (firstName) {
            fullName += ', ' + firstName
        }
        if (middleName) {
            fullName += ' ' + middleName
        }
        return fullName
    }

    // Return a printable string of the active interests the student
    // has
    String activeInterestsSummary() {
        // return "Basket weaving, stupidity, sensationalness"
        def progs = []
        interests.each { 
            if (it.active) {
                progs << it.program.name 
            }
        }
        return progs ? progs.join(", ") : ''
    }

}
