package org.bworks.bworksdb

import grails.test.*

class ClassSessionServiceIntegrationTests extends GrailsUnitTestCase {

    def classSessionService
    def testDataService

    void testAddEnrollment() {
        // create an interested student in 'computer course'
        def (contact, student, course) = 
            testDataService.setupContactAndStudentWithCourse('con', 'stuey', 'computer course')

        def session = new ClassSession(name:'May 2010', startDate: new Date(), course:course).save()

        classSessionService.enrollStudent(student, session)

        assertEquals 1, session.enrollments.size()
        def enrollment = session.enrollments.find {
            it.student.firstName == 'stuey'
        }
        assertNotNull enrollment
        assertEquals EnrollmentStatus.IN_PROGRESS, enrollment.status

    }

    // Enrollments should be totally removed from the database
    // when we call disRollStudent
    void testRemoveEnrollment() {
        // create an interested student in 'computer course'
        def (contact, student, course) = 
            testDataService.setupContactAndStudentWithCourse('con', 'removeme', 'computer course')

        def session = new ClassSession(name:'May 2010', startDate: new Date(), course:course).save()

        classSessionService.enrollStudent(student, session)
        classSessionService.disrollStudent(student, session)

        assertEquals 0, session.enrollments.size()
    }

    // Student who has had no attendances should have enrollment removed
    // when we call disrollStudent method.
    void testDisEnrollWithNoAttendances() {
        // create an interested student in 'computer course'
        def (contact, student, course) = 
            testDataService.setupContactAndStudentWithCourse('con', 'removeme', 'computer course')

        def session = testDataService.setupFullClassSession(course)

        classSessionService.enrollStudent(student, session)
        assertNotNull session.enrollments.find {
            it.student == student
        }

        def lessonDate = session.lessonDates.toArray()[0]
        assertNotNull lessonDate

        lessonDate.addToAttendees(student : student, status:'absent').save()
        def attendance = lessonDate.attendees.find {
            it.student == student
        }

        assertNull lessonDate.attendees.find {
            it.student == student && it.status == 'present'
        }

        // finally, run our test.
        classSessionService.disrollStudent(student, session)
        // Session should not have student enrolled anymore, and enrollment
        // should not be DROPOUT -- it should just be gone, because
        // student never attended any classes
        assertNull 'Enrollment should be removed if student never attended classes', session.enrollments.find {
            it.student == student
        }


    }
}
