package org.bworks.bworksdb

class PhoneNumber {

    String phoneNumber
    String label
    static belongsTo = [Contact]
    static constraints = {
        //phone(phoneNumber:true)
    }

    String toString() {
        label + " : " + phoneNumber
    }
}
