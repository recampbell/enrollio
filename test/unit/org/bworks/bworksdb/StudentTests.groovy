
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

    void testFullName() {
        def student = new Student()

        assertEquals '', student.fullName()

        student.lastName = 'Lahey'

        assertEquals 'Lahey', student.fullName()

        student.firstName = 'Mr'
        assertEquals 'Mr Lahey', student.fullName()

        student.middleName = 'Jim'
        assertEquals 'Mr Jim Lahey', student.fullName()

        assertEquals 'Ricky', new Student(firstName:"Ricky").fullName()

        assertEquals 'Lester', new Student(middleName:"Lester").fullName()

    }


}
