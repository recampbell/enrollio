
package org.bworks.bworksdb
import grails.test.*

class StudentTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testConstraints_Green() {
        mockDomain(Student)

        def contact = new Contact(firstName:'f',
                            lastName:'lastName',
                            address1:'add1',
                            address2:'address2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'zip',
                            emailAddress:'emailAddress')
        
        // test
        def student = new Student(lastName:"Lahey", contact : contact)
        
        assertTrue student.validate()
    }    
}