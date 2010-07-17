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

    void testAddEnrollmentWithTwoStudentsTheSameName() {
        // create an interested student in 'computer course'
        def (contact, originalStudent, course) = 
            testDataService.setupContactAndStudentWithCourse('con', 'stuey', 'computer course')

        def session = new ClassSession(name:'May 2010', startDate: new Date(), course:course).save()

        classSessionService.enrollStudent(originalStudent, session)
        def sameNameStudent = new Student(firstName:'stuey', lastName:'con')
        contact.addToStudents(sameNameStudent).save()
        assertEquals 2, contact.students.size()

        classSessionService.enrollStudent(sameNameStudent, session)
        assertEquals 2, session.enrollments.size()

        def originalStudentEnrollment = Enrollment.findByStudentAndClassSession(originalStudent, session)
        assertNotNull originalStudentEnrollment
        assertEquals EnrollmentStatus.IN_PROGRESS, originalStudentEnrollment.status

        def sameNameStudentEnrollment = Enrollment.findByStudentAndClassSession(sameNameStudent, session)
        assertNotNull sameNameStudentEnrollment
        assertEquals EnrollmentStatus.IN_PROGRESS, sameNameStudentEnrollment.status

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

    // Student who has had no attendances should have enrollment removed
    // when we call disrollStudent method.
    void testDisEnrollWithPresentAttendances() {
        // create an interested student in 'computer course'
        def (contact, student, course) = 
            testDataService.setupContactAndStudentWithCourse('con', 'removeme', 'computer course')

        def session = testDataService.setupFullClassSession(course)

        classSessionService.enrollStudent(student, session)

        def lessonDate = session.lessonDates.toArray()[0]
        lessonDate.addToAttendees(student : student, status:'present').save()

        // Student should still have a record of their enrollment
        classSessionService.disrollStudent(student, session)

        def enrollment = session.enrollments.find {
            it.student == student
        }

        assertEquals 'Students who attended a class but didn\'t graduate should be marked as dropout', 
            EnrollmentStatus.DROPPED_OUT,
            enrollment.status

    }
}
