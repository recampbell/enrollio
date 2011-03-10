package org.bworks.bworksdb

import org.bworks.bworksdb.util.TestKeys
import grails.test.*

class StudentServiceIntegrationTests extends GrailsUnitTestCase {
    def studentService
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void assertInterestSelection(student, courseName, isSelected, signupDate = null) {
        def result = student.interests.find { it.course.name == courseName }
        assertEquals isSelected, result.active        
        // If caller wants us to verify signupDate, then do so.
        if (signupDate) {
            assertEquals signupDate, result.signupDate
        }
    }
    
    void testSaveInterests_NewInterest() {
        // see TestDataService for preloaded data
        def student = Student.findByLastName(TestKeys.STUDENT)
        def selection = Course.findByName(TestKeys.PROGRAM_MENTORSHIP).id.toString()
        
        // test
        studentService.saveInterests(student, selection)

        assertInterestSelection(student, TestKeys.PROGRAM_MENTORSHIP, true)
        assertInterestSelection(student, TestKeys.PROGRAM_KIDS_AEC, false)
        assertInterestSelection(student, TestKeys.PROGRAM_ADULT_AEC, false)
    }
    
    void testSaveInterests_SelectInterest() {
        // see TestDataService for preloaded data
        def student = Student.findByLastName(TestKeys.STUDENT)
        def selection = Course.findByName(TestKeys.PROGRAM_KIDS_AEC).id.toString()
        
        // test
        studentService.saveInterests(student, selection)

        assertInterestSelection(student, TestKeys.PROGRAM_KIDS_AEC, true)
        assertInterestSelection(student, TestKeys.PROGRAM_ADULT_AEC, false)
    }

    void testSaveInterests_SelectAllInterests() {
        // see TestDataService for preloaded data
        def student = Student.findByLastName(TestKeys.STUDENT)
        def selections = []
        
        selections << Course.findByName(TestKeys.PROGRAM_KIDS_AEC).id.toString()
        selections << Course.findByName(TestKeys.PROGRAM_ADULT_AEC).id.toString()
        selections << Course.findByName(TestKeys.PROGRAM_MENTORSHIP).id.toString()
        
        // test
        studentService.saveInterests(student, selections)

        assertInterestSelection(student, TestKeys.PROGRAM_MENTORSHIP, true)
        assertInterestSelection(student, TestKeys.PROGRAM_KIDS_AEC, true)
        assertInterestSelection(student, TestKeys.PROGRAM_ADULT_AEC, true)
    }
    
    void testSaveInterests_SelectNone() {
        // see TestDataService for preloaded data
        def course = Course.findByName(TestKeys.PROGRAM_KIDS_AEC)
        def student = Student.findByLastName(TestKeys.STUDENT)
        
        // test
        studentService.saveInterests(student, [])

        assertInterestSelection(student, TestKeys.PROGRAM_KIDS_AEC, false)
        assertInterestSelection(student, TestKeys.PROGRAM_ADULT_AEC, false)
    }
    
    // Test signup with a date that's in the past
    void testSaveInterests_SelectAllInterests_WithDate() {
        // see TestDataService for preloaded data
        def student = Student.findByLastName(TestKeys.STUDENT)
        def selections = []
        
        def interestDates = [:]
        def eacId = Course.findByName(TestKeys.PROGRAM_KIDS_AEC).id.toString()
        interestDates[eacId] = Date.parse("MM/dd/yyyy", "4/20/2010")

        def adultId = Course.findByName(TestKeys.PROGRAM_ADULT_AEC).id.toString()
        interestDates[adultId] = Date.parse("MM/dd/yyyy", "4/20/2009")

        def mentId = Course.findByName(TestKeys.PROGRAM_MENTORSHIP).id.toString()
        interestDates[mentId] = Date.parse("MM/dd/yyyy", "5/11/2010")

        selections << eacId
        selections << adultId
        selections << mentId
        
        studentService.saveInterests(student, selections, interestDates)

        assertInterestSelection(student, TestKeys.PROGRAM_KIDS_AEC, true, interestDates[eacId])
        assertInterestSelection(student, TestKeys.PROGRAM_ADULT_AEC, true, interestDates[adultId])
        assertInterestSelection(student, TestKeys.PROGRAM_MENTORSHIP, true, interestDates[mentId])
    }
}
