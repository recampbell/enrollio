package org.bworks.bworksdb.auth 

class ShiroUserTests extends grails.test.GrailsUnitTestCase {
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testPassword_Mismatch() {
        mockDomain(ShiroUser)

        def user = new ShiroUser(firstName:'nate',
                                  lastName:'neff',
                                  passwordHash:'baz',
                                  username:'tookelso',
                                  password:'foo',
                                  passwordConfirm:'bar')
                                  
        // test
        assertFalse 'password and passwordConfirm must be equal', user.validate()
    }
    
    void testPassword_TooShort() {
        mockDomain(ShiroUser)

        def user = new ShiroUser(firstName:'nate',
                                  lastName:'neff',
                                  username:'tookelso',
                                  password:'foo')
                                  
        // test
        assertFalse user.validate()
    }

    void testPassword_TooShort2() {
        mockDomain(ShiroUser)

        def user = new ShiroUser(firstName:'nate',
                                  lastName:'neff',
                                  username:'tookelso',
                                  password:'123')
                                  
        // test
        assertFalse user.validate()
    }

    void testPassword_NoDigit() {
        mockDomain(ShiroUser)

        def user = new ShiroUser(firstName:'nate',
                                  lastName:'neff',
                                  username:'tookelso',
                                  password:'meow!')
                                  
        // test
        assertFalse user.validate()
    }

    void testPassword_Green() {
        mockDomain(ShiroUser)

        def user = new ShiroUser(firstName:'nate',
                                  lastName:'neff',
                                  passwordHash:'baz',
                                  username:'tookelso',
                                  password:'meow69',
                                  passwordConfirm:'meow69')
                                  
        // test
        assertTrue user.validate()
    }
    
}
