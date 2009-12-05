package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.util.TestKeys

class ProgramControllerTests extends grails.test.ControllerUnitTestCase {
    def programService
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCallListReportData() {
         controller.programService = programService 

         def program = Program.findByName(TestKeys.PROGRAM)
         assertEquals TestKeys.PROGRAM, program.name
       
         def student = Student.findByLastName(TestKeys.STUDENT)
         assertEquals TestKeys.STUDENT, student.lastName
         
         mockParams.id = program.id
         
         // test
         def reportData = controller.callListReportData()
        
         assertEquals TestKeys.PROGRAM, mockParams['PROGRAM_NAME']         
         assertNotNull reportData
         def thisMap = reportData[0]
         assertEquals student.fullName(), thisMap['STUDENT_NAME'] 
         assertEquals TestKeys.CONTACT_EMAIL, thisMap['CONTACT_EMAIL']
     }
}
