package org.bworks.bworksdb

class Contact {

    String firstName
    String lastName
    String address1
    String address2
    String city
    String state
    String zipCode
    String emailAddress

    static hasMany = [students:Student, phoneNumbers:PhoneNumber]
    // Declared to assist w/adding phoneNumbers to class easily
    // http://lxisoft.com/web/guest/grails
    List phoneNumbers = new ArrayList()
    
    static constraints = {
        address1(nullable:true)
        address2(nullable:true)
        state(nullable:true)
        city(nullable:true)
        zipCode(nullable:true)
        emailAddress(nullable:true)
    }

    String toString(){
        return lastName + ',' + firstName
    }
}
