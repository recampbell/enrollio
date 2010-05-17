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
        def starStudent = new Student(firstName:'Star', lastName:'Star', starred:true);
        
        def contact = new Contact(firstName:'Bob',
                            lastName:'IhaveAStarredStudent',
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
        contact.addToStudents(starStudent).save()
        
        def notStarStudent1 = new Student(firstName:'not', lastName:'nostar', starred:false);
        def notStarStudent1Brother = new Student(firstName:'brother', lastName:'nostar', starred:false);
        
        def notStarContact1 = new Contact(firstName:'Not',
                            lastName:TestKeys.CONTACT1_LAST_NAME,
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
        notStarContact1.addToStudents(notStarStudent1).save()
        notStarContact1.addToStudents(notStarStudent1Brother).save()
        
        def notStarStudent2 = new Student(firstName:'not', lastName:'Star', starred:true);
        
        def notStarContact2 = new Contact(firstName:'Bob',
                            lastName:TestKeys.CONTACT1_LAST_NAME,
                            address1:'add1',
                            address2:'add2',
                            city:'Saint Louis',
                            state:'MO',
                            zipCode:'63043',
                            emailAddress:TestKeys.CONTACT_EMAIL).save()
        notStarContact2.addToStudents(notStarStudent2).save()
                                
        def course = new Course(name:"first course", description:"foo desc.").save()
        
        def firstInterest = new Interest(course:course, active:true)
        starStudent.addToInterests(firstInterest)
        course.addToInterests(firstInterest).save()

        def secondInterest = new Interest(course:course, active:true)
        notStarStudent2.addToInterests(secondInterest)
        course.addToInterests(secondInterest).save()

        def thirdInterest = new Interest(course:course, active:true)
        notStarStudent1.addToInterests(thirdInterest)
        course.addToInterests(thirdInterest).save()

        def thirdInterestBrother = new Interest(course:course, active:true)
        notStarStudent1Brother.addToInterests(thirdInterestBrother)
        course.addToInterests(thirdInterestBrother).save()




        def contacts = courseService.callList(course.id)
        
        assertEquals 'wrong number of contacts returned', 3, contacts.size()
        
        assertEquals 1, contacts[0].students.findAll { it.starred }.size()
        assertEquals 1, contacts[1].students.findAll { it.starred }.size()
        assertEquals 0, contacts[2].students.findAll { it.starred }.size()
    }
}