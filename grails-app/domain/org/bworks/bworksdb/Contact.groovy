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

    // Declared to assist w/adding phoneNumbers to class easily
    // http://lxisoft.com/web/guest/grails
    List phoneNumbers = new ArrayList()
    List students

    static hasMany = [students:Student, phoneNumbers:PhoneNumber]
    
    static constraints = {
        address1(nullable:true)
        address2(nullable:true)
        state(nullable:true)
        city(nullable:true)
        zipCode(nullable:true)
        emailAddress(nullable:true)
    }

    String toString(){
        return lastName + ', ' + firstName
    }

    String fullAddress() {
        def addr = address1
        if (address2) {
            addr += ", " + address2
        }

        if (city) {
            addr += ", " + city
        }
        if (state) {
            addr += ", " + state
        }
        if (zipCode) {
            addr += "  " + zipCode
        }
    }

    String allPhoneNumbers() {
        phoneNumbers?.join(", ")
    }


}
