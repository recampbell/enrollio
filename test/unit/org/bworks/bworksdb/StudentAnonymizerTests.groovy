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
        assertNotNull sa
    }

    void testStudentCount() {
        def multipleTestStudents = getXmlStudents(7)
        sa = new StudentAnonymizer(multipleTestStudents)
        assertEquals 7, sa.students.size()
    }

    void testFirstName() {
        def testStudent = getXmlStudents(1)
        sa = new StudentAnonymizer(testStudent)
        def origFirstName = sa.students[0].FirstName.text()
        sa.anonymize()
        assertTrue origFirstName != sa.students[0].FirstName.text()
        // Make sure we just didn't blank out the name
        assertTrue sa.students[0].FirstName.text().length() > 0
        // This test might be a little too picky, but
        // it tries to find the new name in the list
        // of the anonymizer's names
        def found = sa.anon.firstNames.find {
            it == sa.students[0].FirstName.text()
        }
        assertNotNull found
    }

    void testMultipleFirstNames() {
        def testStudents = getXmlStudents(2)

        sa = new StudentAnonymizer(testStudents)
        def origFirstNames = sa.students.collect {
            it.FirstName.text()
        }

        assertEquals 'Lexicon#@#123^^^', origFirstNames[0]
        assertEquals 'Lexicon#@#123^^^', origFirstNames[1] 

        sa.anonymize()
        assertTrue 'Lexicon#@123^^^' != sa.students[0].FirstName.text()
		assertTrue 'Lexicon#@123^^^' != sa.students[1].FirstName.text()
        // Make sure we just didn't blank out the name
        assertTrue sa.students[0].FirstName.text().length() > 0
        assertTrue sa.students[1].FirstName.text().length() > 0

        sa.students.FirstName.each { firstName ->
            def found = sa.anon.firstNames.find {
                it == firstName.text()
            }
            assertNotNull found 
        }
    }

    void testLastName() {
        def testStudent = getXmlStudents(1)
        sa = new StudentAnonymizer(testStudent)
        assertEquals 'Sprankle72.6', sa.students[0].LastName.text()
        sa.anonymize()
        assertTrue 'Sprankle72.6' != sa.students[0].LastName.text()
        // Make sure we just didn't blank out the name
        assertTrue sa.students[0].LastName.text().length() > 0
        // This test might be a little too picky, but
        // it tries to find the new name in the list
        // of the anonymizer's names
        def found = sa.anon.lastNames.find {
            it == sa.students[0].LastName.text()
        }
        assertNotNull found
    }

    void testMultipleLastNames() {
        def testStudents = getXmlStudents(3)

        sa = new StudentAnonymizer(testStudents)
        def origLastNames = sa.students.collect {
            it.LastName.text()
        }

        assertEquals 'Sprankle72.6', origLastNames[0]
		assertEquals 'Sprankle72.6', origLastNames[1]
		assertEquals 'Sprankle72.6', origLastNames[2]

        sa.anonymize()
        assertTrue 'Sprankle72.6' != sa.students[0].LastName.text()
        assertTrue 'Sprankle72.6' != sa.students[1].LastName.text()
		assertTrue 'Sprankle72.6' != sa.students[2].LastName.text()
        // Make sure we just didn't blank out the name
		assertTrue sa.students[0].LastName.text().length() > 0
		assertTrue sa.students[1].LastName.text().length() > 0
		assertTrue sa.students[2].LastName.text().length() > 0

        sa.students.LastName.each { lastName ->
            def found = sa.anon.lastNames.find {
                it == lastName.text()
            }
            assertNotNull found
        }
    }

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
