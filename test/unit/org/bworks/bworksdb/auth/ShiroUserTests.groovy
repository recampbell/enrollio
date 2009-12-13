package org.bworks.bworksdb.auth 

class ShiroUserTests extends grails.test.GrailsUnitTestCase {
    void testPasswordConfirm() {
        mockDomain(ShiroUser)

        // create user w/different password and passwordConfirm
        def user = new ShiroUser(firstName:'nate',
                                  lastName:'neff',
                                  passwordHash:'baz',
                                  username:'tookelso',
                                  password:'foo',
                                  passwordConfirm:'bar')
        assertFalse 'password and passwordConfirm must be equal', user.validate()
    }
}
