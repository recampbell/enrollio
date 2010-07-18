package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.util.TestKeys
import org.bworks.bworksdb.auth.ShiroUser
import org.bworks.bworksdb.CallListContact


class CourseServiceCallListTests extends GrailsUnitTestCase {
    def courseService
    def testDataService

    // Call list should be ordered by the date
    // that people sign up
    // Also, Starred students get priority over non-starred
    void testSignupDateOrderAndPagination() {

        // register stu5 today, should be last
        def (con5, stu5, computerCourse) = 
           testDataService.setupContactAndStudentWithCourse('con5', 'stu5', 'Computer Course')

        // also register stu6 and stu6.1 today, should be last
        def (con6, stu6) = 
           testDataService.setupContactAndStudentWithCourse('con6', 'stu6', 'Computer Course')
        def stu61
        (con6, stu61) = 
           testDataService.setupContactAndStudentWithCourse('con6', 'stu6.1', 'Computer Course')

        // register stu4 yesterday
        def (con4, stu4) = 
           testDataService.setupContactAndStudentWithCourse('con4', 'stu4', 'Computer Course')

        stu4.interests.each { it.signupDate = new Date() - 1 }
        stu4.save()

        // stu3 registered a year ago, but isn't starred, should be behind the starred students
        def (con3, stu3) = 
           testDataService.setupContactAndStudentWithCourse('con3', 'stu3', 'Computer Course')
        stu3.interests.each { it.signupDate = new Date() - 365 }
        stu3.save()

        // stu1 registered last year, and is starred
        def (con1, stu1) = 
           testDataService.setupContactAndStudentWithCourse('con1', 'stu1', 'Computer Course')
        stu1.starred = true
        stu1.interests.each { it.signupDate = new Date() - 365 }
        stu1.save()


        // stu2 registered today, but is starred
        def (con2, stu2) = 
           testDataService.setupContactAndStudentWithCourse('con2', 'stu2', 'Computer Course')
        stu2.starred = true
        stu2.save()

        // get first person in paginated list
        def options = [ offset:0, max:1 ]
        def contactList = courseService.callList(computerCourse.id, options)

        assertEquals 'con1', contactList[0].lastName

        // get second and third person in paginated list
        contactList = courseService.callList(computerCourse.id, [ offset:1, max:2])

        assertEquals 'con2', contactList[0].lastName
        assertEquals 'con3', contactList[1].lastName

        // get everyone after fourth in list
        contactList = courseService.callList(computerCourse.id, [offset:3, max:100])
        assertEquals 'con4', contactList[0].lastName
        assertEquals 'con5', contactList[1].lastName
        assertEquals 'con6', contactList[2].lastName
        // make sure that con6 only shows up once
        assertEquals 3, contactList.size()
        // make sure we get correct total count
        assertEquals 6, contactList.totalCount

        courseService.updateCallListContacts(computerCourse)
        def callListContacts = courseService.callListContacts(computerCourse)

        assertEquals 1, callListContacts[con1.id].callListPosition
        assertEquals 2, callListContacts[con2.id].callListPosition
        assertEquals 3, callListContacts[con3.id].callListPosition
        assertEquals 4, callListContacts[con4.id].callListPosition
        assertEquals 5, callListContacts[con5.id].callListPosition
        assertEquals 6, callListContacts[con6.id].callListPosition
    }

    // register three contacts interested in computer course
    // then, request to see contact #2, with pagination, and 
    // we should see #2, #3
    void testShowContactInCallList() {
        def (computerContact, computerStudent, computerCourse) = 
           testDataService.setupContactAndStudentWithCourse('ComputerContact', 'ComputerStudent', 'Computer Course')

        def (computerContact2) = 
           testDataService.setupContactAndStudentWithCourse('ComputerContact2', 'ComputerStudent2', 'Computer Course')

        def (computerContact3) = 
           testDataService.setupContactAndStudentWithCourse('ComputerContact3', 'ComputerStudent3', 'Computer Course')

        def (computerContact4) = 
           testDataService.setupContactAndStudentWithCourse('ComputerContact4', 'ComputerStudent4', 'Computer Course')

        // callList should ignore offset if contactId is specified.
        // if I want to see contact 'bob', then 'bob' should be first, regardless
        // of offset.
        def contactList = courseService.callList(computerCourse.id,
                                    [offset:3, max:2, contactId:computerContact2.id])
        assertEquals 2, contactList.size()
        assertEquals 'ComputerContact2', contactList[0].lastName
        assertEquals 'ComputerContact3', contactList[1].lastName
    }
}
        
