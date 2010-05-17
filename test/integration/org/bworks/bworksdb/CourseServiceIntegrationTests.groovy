package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.util.TestKeys


class CourseServiceIntegrationTests extends GrailsUnitTestCase {
    def courseService
    
    void testFindInterestedContacts() {
        def student = Student.findByLastName(TestKeys.STUDENT)
        def contact = new Contact(firstName:'Bob',
                            lastName:TestKeys.CONTACT1_LAST_NAME,
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
                                
        
        def firstCourse = new Course(name:"first course", description:"foo desc.")
        def secondCourse = new Course(name:"second course", description:"foo desc.")
        def thirdCourse = new Course(name:"third course", description:"foo desc.")
        
        def firstInterest = new Interest(course:firstCourse, active:true)
        def secondInterest = new Interest(course:secondCourse, active:true)

        student.addToInterests(firstInterest).addToInterests(secondInterest).save()
        
        firstCourse.addToInterests(firstInterest).save()
        secondCourse.addToInterests(secondInterest).save()

        def contacts = courseService.callList(firstCourse.id)
        
        assertEquals 'One contact found', 1, contacts.size()
    }
}