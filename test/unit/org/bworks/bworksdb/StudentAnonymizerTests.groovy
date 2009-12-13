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

    void testFirstName() {
        def testStudent = getXmlStudents(1)
        sa = new StudentAnonymizer(testStudent)
        def origFirstName = sa.students[0].FirstName.text()
        sa.anonymize()
        assert origFirstName != sa.students[0].FirstName.text()
        // Make sure we just didn't blank out the name
        assert sa.students[0].FirstName.text().length() > 0
        // This test might be a little too picky, but
        // it tries to find the new name in the list
        // of the anonymizer's names
        def found = sa.anon.firstNames.find {
            it == sa.students[0].FirstName.text()
        }
        assert found != null
    }

    void testMultipleFirstNames() {
        def testStudents = getXmlStudents(2)

        sa = new StudentAnonymizer(testStudents)
        def origFirstNames = sa.students.collect {
            it.FirstName.text()
        }

        assert origFirstNames[0] == 'Lexicon#@#123^^^'
        assert origFirstNames[1] == 'Lexicon#@#123^^^'

        sa.anonymize()
        assert 'Lexicon#@123^^^' != sa.students[0].FirstName.text()
        assert 'Lexicon#@123^^^' != sa.students[1].FirstName.text()
        // Make sure we just didn't blank out the name
        assert sa.students[0].FirstName.text().length() > 0
        assert sa.students[1].FirstName.text().length() > 0

        sa.students.FirstName.each { firstName ->
            def found = sa.anon.firstNames.find {
                it == firstName.text()
            }
            assert found != null
        }
    }

    void testLastName() {
        def testStudent = getXmlStudents(1)
        sa = new StudentAnonymizer(testStudent)
        assert 'Sprankle72.6' == sa.students[0].LastName.text()
        sa.anonymize()
        assert 'Sprankle72.6' != sa.students[0].LastName.text()
        // Make sure we just didn't blank out the name
        assert sa.students[0].LastName.text().length() > 0
        // This test might be a little too picky, but
        // it tries to find the new name in the list
        // of the anonymizer's names
        def found = sa.anon.lastNames.find {
            it == sa.students[0].LastName.text()
        }
        assert found != null
    }

    void testMultipleLastNames() {
        def testStudents = getXmlStudents(3)

        sa = new StudentAnonymizer(testStudents)
        def origLastNames = sa.students.collect {
            it.LastName.text()
        }

        assert origLastNames[0] == 'Sprankle72.6'
        assert origLastNames[1] == 'Sprankle72.6'
        assert origLastNames[2] == 'Sprankle72.6'

        sa.anonymize()
        assert 'Sprankle72.6' != sa.students[0].LastName.text()
        assert 'Sprankle72.6' != sa.students[1].LastName.text()
        assert 'Sprankle72.6' != sa.students[2].LastName.text()
        // Make sure we just didn't blank out the name
        assert sa.students[0].LastName.text().length() > 0
        assert sa.students[1].LastName.text().length() > 0
        assert sa.students[2].LastName.text().length() > 0

        sa.students.LastName.each { lastName ->
            def found = sa.anon.lastNames.find {
                it == lastName.text()
            }
            assert found != null
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
