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
        lastName(nullable:false, blank:false)
        emailAddress(nullable:true, blank:false)
    }
    
    String toString(){
        return lastName + ', ' + firstName + ' ' + middleName
    }
    
}
