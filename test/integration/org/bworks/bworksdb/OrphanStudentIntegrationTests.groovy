package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.util.TestData

class OrphanStudentIntegrationTests extends GrailsUnitTestCase {
    def dataLoadingService
    
    void testOrphanStudents() {
        // load class session, contact and student data
        // yes, it's tedious but it's an integration test, meow.
        def classSessions = TestData.fixtureSingleClassSession()
        dataLoadingService.loadClassSessions(classSessions)


        // Don't load any contacts, just load students
        dataLoadingService.loadStudents(TestData.fixtureMultipleStudents())

        def orphan1 = Student.findByFirstNameAndLastName('Totoro','Tortenweasel')
        assertNotNull "Orphan Totoro loaded successfully", orphan1
        assertEquals 'totoro@alum.bworks.org', orphan1.emailAddress


        def con = orphan1.contact
        assertEquals 'Contact generated correctly', 'Tortenweasel', con.lastName
        assert 'Contact firstname generated correctly', ( con.firstName =~ /Auto-generated/ ).matches()

    }
}
        
