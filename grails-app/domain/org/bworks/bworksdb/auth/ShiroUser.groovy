package org.bworks.bworksdb.auth

class ShiroUser {
    String username
    String firstName
    String lastName
    String passwordHash
    String passwordConfirm
    String password

    static transients = ['passwordConfirm', 'password']

    static constraints = {
        username(nullable: false, blank: false)
        firstName(nullable:false, blank:false)
        lastName(nullable:false, blank:false)
        password(nullable:false, blank:false, validator: 
            { val, obj ->
                def result = 'user.password.strength.confirmation'

                if (val != obj.passwordConfirm) {
                    result = 'user.password.mustmatch.confirmation'       
                } else if (val.length() >= 5 && (val =~ /.*\d.*/)) {
                    result = true
                }

                result
            }
        )
    }
    
}
