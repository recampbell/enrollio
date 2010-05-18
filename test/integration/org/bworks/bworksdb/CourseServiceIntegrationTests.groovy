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

    // Only one instance of a contact should be returned,
    // even if that contact has > 1 interested student
    void testUniqueContacts() {
        def contact, student1, student2, student3, computerCourse
        ( contact, student1, computerCourse) = 
           setupContactAndStudentWithCourse('Contact', 'Student1', 'Computer Course')

        ( contact, student2) =
           setupContactAndStudentWithCourse('Contact', 'Student2', 'Computer Course')
        ( contact, student3) =
           setupContactAndStudentWithCourse('Contact', 'Student3', 'Computer Course')

        def contactList = courseService.callList(computerCourse.id)
        assertEquals 1, contactList.size()
        assertEquals 'Contact', contactList[0].lastName
    }

    // Call list should be ordered by the date
    // that people sign up
    // Also, Starred students get priority over non-starred
    void testSignupDateOrder() {

        // register stu5 today, should be last
        def con5, stu5, computerCourse
        ( con5, stu5, computerCourse) = 
           setupContactAndStudentWithCourse('con5', 'stu5', 'Computer Course')

        // register stu4 yesterday
        def con4, stu4
        ( con4, stu4) = 
           setupContactAndStudentWithCourse('con4', 'stu4', 'Computer Course')

        stu4.interests.each { it.signupDate = new Date() - 1 }
        stu4.save()

        // stu3 registered a year ago, but isn't starred, should be behind the starred students
        def con3, stu3
        ( con3, stu3) = 
           setupContactAndStudentWithCourse('con3', 'stu3', 'Computer Course')
        stu3.interests.each { it.signupDate = new Date() - 365 }
        stu3.save()

        // stu1 registered last year, and is starred
        def con1, stu1
        ( con1, stu1) = 
           setupContactAndStudentWithCourse('con1', 'stu1', 'Computer Course')
        stu1.starred = true
        stu1.interests.each { it.signupDate = new Date() - 365 }
        stu1.save()


        // stu2 registered today, but is starred
        def con2, stu2
        ( con2, stu2) = 
           setupContactAndStudentWithCourse('con2', 'stu2', 'Computer Course')
        stu2.starred = true
        stu2.save()

        def contactList = courseService.callList(computerCourse.id)

        assertEquals 'con1', contactList[0].lastName
        assertEquals 'con2', contactList[1].lastName
        assertEquals 'con3', contactList[2].lastName
        assertEquals 'con4', contactList[3].lastName
        assertEquals 'con5', contactList[4].lastName
        
        
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
