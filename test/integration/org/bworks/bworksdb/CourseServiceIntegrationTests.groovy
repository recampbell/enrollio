package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.util.TestKeys


class CourseServiceIntegrationTests extends GrailsUnitTestCase {
    def courseService
    def testDataService
    
    void testFindInterestedContacts() {
        def computerStudent, computerContact, computerCourse
        ( computerContact, computerStudent, computerCourse ) = 
           setupContactAndStudentWithCourse('ComputerContact', 'ComputerStudent', 'Computer Course')

        def bikeStudent, bikeContact, bikeCourse
        ( bikeContact, bikeStudent, bikeCourse ) = 
           setupContactAndStudentWithCourse('BikeContact', 'BikeStudent', 'Bike Course')

        // set up student interested in both bike and computer course
        def bothStudent, bothContact
        ( bothContact, bothStudent ) = 
           setupContactAndStudentWithCourse('BothContact', 
                                            'BothStudent', 
                                            'Bike Course', 'Computer Course')

        // set up student interested in some other course
        def someStudent, someContact
        ( someContact, someStudent ) = 
           setupContactAndStudentWithCourse('SomeContact', 
                                            'SomeStudent', 
                                            'Some Course', 'Some Course')

        // add another course with nobody interested, just to flesh out the test scenario
        def thirdCourse = new Course(name:"third course", description:"foo desc.").save()
        
        def computerCallList = courseService.callList(computerCourse.id)
        
        assertEquals 'Two contacts should be on computer course call list', 2, computerCallList.size()
        assertNotNull computerCallList.find { it.lastName == 'ComputerContact' }
        assertNotNull computerCallList.find { it.lastName == 'BothContact' }

        def bikeCallList = courseService.callList(bikeCourse.id)
        assertEquals 'Two contacts should be on bike course call list', 2, bikeCallList.size()
        assertNotNull bikeCallList.find { it.lastName == 'BikeContact' }
        assertNotNull bikeCallList.find { it.lastName == 'BothContact' }
    }
    
    void testStarredStudentsFirst() {
        def firstContact, firstStudent, computerCourse
        ( firstContact, firstStudent, computerCourse ) = 
           setupContactAndStudentWithCourse('FirstContact', 'FirstStudent', 'Computer Course')

        def firstStarredContact, firstStarredStudent
        ( firstStarredContact, firstStarredStudent) = 
           setupContactAndStudentWithCourse('FirstStarredContact', 'SecondStudent', 'Computer Course')

        // set starred for this student
        firstStarredStudent.starred = true
        firstStarredStudent.save()

        def thirdContact, thirdStudent
        ( thirdContact, thirdStudent) = 
           setupContactAndStudentWithCourse('ThirdContact', 'ThirdStudent', 'Computer Course')
        
        def secondStarredContact, secondStarredStudent
        ( secondStarredContact, secondStarredStudent) = 
           setupContactAndStudentWithCourse('SecondStarredContact', 'FourthStudent', 'Computer Course')

        secondStarredStudent.starred = true
        secondStarredStudent.save()

        def contactList = courseService.callList(computerCourse.id)
        
        assertEquals 'FirstStarredContact',  contactList[0].lastName
        assertEquals 'SecondStarredContact', contactList[1].lastName
        assertEquals 'FirstContact',         contactList[2].lastName
        assertEquals 'ThirdContact',         contactList[3].lastName

    }

    // only active interests should be returned on call list
    void testActiveStudents() {
        def inactiveContact, inactiveStudent, computerCourse
        ( inactiveContact, inactiveStudent, computerCourse ) = 
           setupContactAndStudentWithCourse('inactiveContact', 'inactiveStudent', 'Computer Course')

        inactiveStudent.interests.each {
            it.active = false
            it.save()
        }

        def activeContact, activeStudent
        ( activeContact, activeStudent) = 
           setupContactAndStudentWithCourse('ActiveContact', 'ActiveStudent', 'Computer Course')

        def activeStudent_InactiveBrother
        ( activeContact, activeStudent_InactiveBrother) = 
           setupContactAndStudentWithCourse('ActiveContact', 'ActiveStudentInactiveBrother', 'Computer Course')

        activeStudent_InactiveBrother.interests.each {
            it.active = false
            it.save()
        }

        def anotherActiveContact, anotherActiveStudent
        ( anotherActiveContact, anotherActiveStudent) = 
           setupContactAndStudentWithCourse('AnotherActiveContact', 'AnotherActiveStudent', 'Computer Course')

        def anotherInactiveContact, anotherInactiveStudent
        ( anotherInactiveContact, anotherInactiveStudent) = 
           setupContactAndStudentWithCourse('AnotherInactiveContact', 'AnotherInactiveStudent', 'Computer Course')

        anotherInactiveStudent.interests.each {
            it.active = false
            it.save()
        }

        def contactList = courseService.callList(computerCourse.id)
        assertEquals 2, contactList.size()
        assertNotNull contactList.find {
            it.lastName == 'ActiveContact'
        }
        assertNotNull contactList.find {
            it.lastName == 'AnotherActiveContact'
        }
    }

    void willTest() {
        /*

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
        */

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

    // utility method to simplify student/contact setup
    // returns a list with contact, student
    // You can specify > 1 courseName if you want to add interests to mult. courses.
    // If contact/student with contactLastName, studentFirstName already exist, they are
    // NOT re-created.
    def setupContactAndStudentWithCourse(contactLastName, 
                                         studentFirstName, 
                                         String... courseNames) {
        def retVals = []
        def c = Contact.findByLastName(contactLastName)
        if (!c) {
            c = new Contact(lastName:contactLastName, firstName:'Fuzzball').save()
        }

        retVals.add(c)

        def s = Student.findByLastNameAndFirstName(contactLastName, studentFirstName)
        if (!s) {
            s = new Student(firstName:studentFirstName, lastName:contactLastName)
        } 

        retVals.add(s)

        c.addToStudents(s).save()

        courseNames.each {
            def course = Course.findByName(it)
            if (! course) {
                course = new Course(name:it, description:it).save()
                retVals.add(course)
            }

            def i = new Interest(course:course, student:s, active:true)
            s.addToInterests(i).save()
            course.addToInterests(i).save()
        }

        return retVals
    }
}
