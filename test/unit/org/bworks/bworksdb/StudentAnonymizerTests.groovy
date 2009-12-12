package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.StudentAnonymizer

class StudentAnonymizerTests extends GrailsUnitTestCase {
    // Student anonymizer instance
    def sa 

    protected void setUp() {
        super.setUp()
    }

    void testInstantiate() {
        def singleTestStudent = getXmlStudents(1)
        sa = new StudentAnonymizer(singleTestStudent)
        assert sa
    }

    void testStudentCount() {
        def multipleTestStudents = getXmlStudents(7)
        sa = new StudentAnonymizer(multipleTestStudents)
        assert sa.students.size() == 7
    }

    // TODO test firstName
    // TODO test lastName
    //
    // ------------- TEST DATA DEFINITION -------------
    // make sure that the STUPID XML DECLARATION '<?xml version' IDIOTIC line is the 
    // FIRST FIRST FIRST FIRST THING IN THIS IDIOTIC STRING.  OR ELSE!
    // DIE DIE DIE DIE DIE DIE XML DECLARATIONS!!!!!!!!!!!!!!!!!!!
    def stupidXmlDeclaration = '<?xml version="1.0" encoding="UTF-8"?>'
    def xmlRootElementHeader = """
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" 
          xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  
          xsi:noNamespaceSchemaLocation="Student.xsd">
          """
    def singleTestXML = """
<Student>
<StudentID>188</StudentID>
<ParentID>136</ParentID>
<LastName>Sprankle72.6</LastName>
<FirstName>Lexicon#@#123^^^</FirstName>
<BirthDate>1997-01-07T00:00:00</BirthDate>
<Grade>10</Grade>
<ClassID>13</ClassID>
<GraduateDate>2007-08-18T00:00:00</GraduateDate>
<DropOut>0</DropOut>
<SystemReceivedID>0</SystemReceivedID>
<email>nocixeL123oopsilon@f1e2r3l4.com</email>
</Student>
"""
   def getXmlStudents(int count = 1) {
       return stupidXmlDeclaration + xmlRootElementHeader + (singleTestXML * count) + "</dataroot>"
   }
}
