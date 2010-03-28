package org.bworks.bworksdb

import grails.test.*

class FileDataLoadingIntegrationTests extends GrailsUnitTestCase {
    def dataLoadingService

    void testFileLoad() {
        def initSessionCount = ClassSession.count()
        def initEnrollmentCount = Enrollment.count()
        def initInterestCount = Interest.count()
        def initStudentCount = Student.count()
        def initContactCount = Contact.count()

        dataLoadingService.loadFromFiles()

        assertEquals "Students loaded from file successfully", 
                    initStudentCount + 7, Student.count()

        assertEquals "Contacts loaded from file successfully", 
                    initContactCount + 4, Contact.count()

        assertEquals "Class Sessions loaded from file successfully", 
                    initSessionCount + 24, ClassSession.count()

        assertEquals "Enrollments loaded from file successfully", 
                    initEnrollmentCount + 4, Enrollment.count()

        assertEquals "Interests loaded from file successfully", 
                    initInterestCount + 3, Interest.count()
    }
}
