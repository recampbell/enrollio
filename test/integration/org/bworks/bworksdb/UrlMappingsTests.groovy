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

    void testHelpMappings() {
        assertUrlMapping("/thanks", controller:'help', action:'thanks')
    }

    void testUserMappings() {
        assertUrlMapping("/showUser/bob", 
                         controller:'shiroUser', action:'show') {
            username = "bob"
        }
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

        assertUrlMapping("/nextAvailableLessonDates", controller:'program', action:'nextAvailableLessonDates')

        // Ensure that /editProgram needs to be passed an ID
        shouldFail(IllegalArgumentException) {
            assertUrlMapping('/editProgram', controller: 'program',
                                                  action: 'edit')
        }

        assertUrlMapping("/programLessons/1", controller:'program', action:'lessons') {
            id = 1
        }

        assertUrlMapping("/sortLessons/1", controller:'program', action:'sortLessons') {
            id = "1"
        }
        assertUrlMapping("/saveLessonSort", controller:'program', action:'saveLessonSort')

    }

    void testStudentMappings() {
        assertUrlMapping("/editStudent/123", controller:'student', action:'edit') {
            id = 123
        }
        assertUrlMapping("/updateStudent", controller:'student', action:'update')
    }

    void testClassSessionMappings() {
        assertUrlMapping("/createClassSession", controller:'classSession', action:'create')

        assertUrlMapping("/saveClassSession",controller:'classSession', action:'save')

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

        assertUrlMapping("/editLesson/1", controller:'lesson', action:'edit') {
            id = "1"
        }

        assertUrlMapping("/updateLesson", controller:'lesson', action:'update')
        assertUrlMapping("/lessons", controller:'lesson', action:'list')

    }
    
    void testSettingMappings() {
        assertUrlMapping("/editSetting/50", controller:'configSetting', action:'edit') {
            id = "50"
        }
        assertUrlMapping("/showSetting/5", controller:'configSetting', action:'show') {
            id = "5"
        }
    }
}
