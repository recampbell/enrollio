package org.bworks.bworksdb

import grails.test.*

class UrlMappingsTests extends GrailsUrlMappingsTestCase {
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDefaultMapping() {
        assertUrlMapping("/", controller:'help', action:'about')
    }

    void testContactMappings() {
        assertUrlMapping("/createContact", controller:'contact', action:'create')
        assertUrlMapping("/editContact/1", controller:'contact', action:'edit') {
            id = "1"
        }
    }

    void testProgramMappings() {

        assertUrlMapping("/editProgram/1", controller:'program', action:'edit') {
            id = "1"
        }

        // Ensure that /editProgram needs to be passed an ID
        shouldFail(IllegalArgumentException) {
            assertUrlMapping('/editProgram', controller: 'program',
                                                  action: 'edit')
        }
    }

    void testClassSessionMappings() {
        assertUrlMapping("/createClassSession", controller:'classSession', action:'create')
        assertUrlMapping("/editEnrollments/73", controller:'classSession', action:'editEnrollments') {
            id = 73
        }
        shouldFail(IllegalArgumentException) {
            assertUrlMapping('/classSession/enroll', controller: 'classSession',
                                                         action: 'enroll')
        }

        shouldFail(IllegalArgumentException) {
            assertUrlMapping('/editEnrollments/', controller: 'classSession', action:'editEnrollments')
        }

        // absence of trailing '/' in URL shouldn't make a difference
        shouldFail(IllegalArgumentException) {
            assertUrlMapping('/editEnrollments', controller: 'classSession', action:'editEnrollments')
        }

        assertUrlMapping('/gradCerts', controller:'classSession', action:'gradCerts')
        assertUrlMapping('/attendanceSheet', controller:'classSession', action:'attendanceSheet')

    }

    void testLessonMappings() {
        assertUrlMapping("/lessonDate/1", controller:'lessonDate', action:'show') {
            id = "1"
        }

    }

}
