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
    
    void testStarredStudentsFirst() {
        def signup1 = new Student(firstName:'Star', lastName:'Star', starred:true);
        
        def contact = new Contact(firstName:'Bob',
                            lastName:'signup1',
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
        contact.addToStudents(signup1).save()
        
        def signup2 = new Student(firstName:'not', lastName:'nostar', starred:false);
        def signup2brother = new Student(firstName:'brother', lastName:'nostar', starred:false);
        
        def notStarContact1 = new Contact(firstName:'Not',
                            lastName:'signup2',
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
        notStarContact1.addToStudents(signup2).save()
        notStarContact1.addToStudents(signup2brother).save()
        
        def signup3 = new Student(firstName:'not', lastName:'Star', starred:true);
        
        def notStarContact2 = new Contact(firstName:'Bob',
                            lastName:'signup3',
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
        notStarContact2.addToStudents(signup3).save()

        def signup4 = new Student(firstName:'signup4', lastName:'Star', starred:true);
        
        def signup4Contact = new Contact(firstName:'Bob',
                            lastName:'signup4',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
        signup4Contact.addToStudents(signup4).save()
                                
        def course = new Course(name:"first course", description:"foo desc.").save()
        
        def firstInterest = new Interest(signupDate:new Date() - 365, course:course, active:true)
        signup1.addToInterests(firstInterest)
        course.addToInterests(firstInterest).save()

        def secondInterest = new Interest(signupDate:new Date() - 300, course:course, active:true)
        signup2.addToInterests(secondInterest)
        course.addToInterests(secondInterest).save()

        def thirdInterestBrother = new Interest(signupDate:new Date() - 200,course:course, active:true)
        signup2brother.addToInterests(thirdInterestBrother)
        course.addToInterests(thirdInterestBrother).save()

        def thirdInterest = new Interest(signupDate:new Date() - 200, course:course, active:true)
        signup3.addToInterests(thirdInterest)
        course.addToInterests(thirdInterest).save()

        def fourthInterest = new Interest(signupDate:new Date() - 100,course:course, active:true)
        signup4.addToInterests(fourthInterest)
        course.addToInterests(fourthInterest).save()

        def contacts = courseService.callList(course.id)
        
        assertEquals 'wrong number of contacts returned', 4, contacts.size()
        
        assertEquals 1, contacts[0].students.findAll { it.starred }.size()
        assertEquals 1, contacts[1].students.findAll { it.starred }.size()
        assertEquals 1, contacts[2].students.findAll { it.starred }.size()
        assertEquals 0, contacts[3].students.findAll { it.starred }.size()
        
        assertEquals 'signup1',contacts[0].lastName
        assertEquals 'signup3',contacts[1].lastName
        assertEquals 'signup4',contacts[2].lastName
        assertEquals 'signup2',contacts[3].lastName
        
        
    }
}