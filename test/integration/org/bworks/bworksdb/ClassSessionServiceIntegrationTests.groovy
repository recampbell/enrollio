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
}
