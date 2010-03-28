package org.bworks.bworksdb

import grails.test.*

class StudentDataLoadingIntegrationTests extends GrailsUnitTestCase {
    def dataLoadingService

   void testStudentBasicImport() {
        def torten1 = Student.findByLastName("Tortenweasel")
        assertNull "No Tortenweasel students", torten1

        // must load contacts before loading students
        dataLoadingService.loadContacts(fixtureMultipleContacts())
        def xml = fixtureMultipleStudents()
        dataLoadingService.loadStudents(xml)

        // Firstname and lastname are tested here
        torten1 = Student.findByLastNameAndFirstName("Tortenweasel", "Totoro" )
        assertNotNull "Tortenweasel student loaded", torten1

        assertEquals "Import Student Grade", 10, torten1.grade
        assertEquals "Import Student Email", 'totoro@alum.bworks.org', torten1.emailAddress
        assertEquals "Import Student Gender", 'M', torten1.gender
        

    }

    void testStudentEnrollments() {
        // load class session, contact and student data
        // yes, it's tedious but it's an integration test, meow.
        dataLoadingService.loadClassSessions(fixtureSingleClassSession())

        def sess = dataLoadingService.findClassSessionByOldId("13")
        assertNull "Class Sesion doesn't have any enrollments'", sess.enrollments

        dataLoadingService.loadContacts(fixtureMultipleContacts())
        dataLoadingService.loadStudents(fixtureMultipleStudents())

        sess = dataLoadingService.findClassSessionByOldId("13")
        assertEquals "Students correctly imported to Class Session", 3, sess.enrollments.size()

        def banditEnr = sess.enrollments.find {
            it.student.firstName == 'Smokey' && it.student.lastName == 'Bandit'
        }

        assertNotNull banditEnr
        assertEquals "Bandit dropped out (of course (no pun intended))", EnrollmentStatus.DROPPED_OUT, banditEnr.status 

        def billyEnr = sess.enrollments.find {
            it.student.firstName == 'Billy' && it.student.lastName == 'Tortenweasel'
        }

        assertNotNull billyEnr
        assertEquals "Billy graduated", EnrollmentStatus.GRADUATED, billyEnr.status

        def totoroEnr = sess.enrollments.find {
            it.student.firstName == 'Totoro' && it.student.lastName == 'Tortenweasel'
        }

        assertNotNull totoroEnr
        assertEquals "Totoro is still taking class", EnrollmentStatus.IN_PROGRESS, totoroEnr.status

    }

   void testStudentBirthDateAndNotes() {
        def bandit = Student.findByLastName("Bandit")
        assertNull "No Bandit students", bandit

        // must load contacts before loading students
        dataLoadingService.loadContacts(fixtureMultipleContacts())
        def xml = fixtureMultipleStudents()
        dataLoadingService.loadStudents(xml)

        // Firstname and lastname are tested here
        bandit = Student.findByLastNameAndFirstName("Bandit", "Smokey" )
        assertNotNull "Smokey student loaded", bandit

        assertEquals "Birthdate", '1997-01-07', bandit.birthDate?.format('yyyy-MM-dd')
        assertNotNull "Student Notes should be saved ", bandit.comments.find {
                          it.body == 'Mentorship Interested'
                      }
        

    }

 // <ParentID>8675309</ParentID>
 // <ParentID>1010101</ParentID>
 // <ParentID>1</ParentID>
 // <ParentID>1010101</ParentID>

 // <ParentID>1</ParentID>
 // <ParentID>3</ParentID>
 // <ParentID>1010101</ParentID>
 // <ParentID>8675309</ParentID>

   void testStudentContacts() {
        def bandit = Student.findByLastName("Bandit")
        assertNull "No Bandit students", bandit

        // must load contacts before loading students
        dataLoadingService.loadContacts(fixtureMultipleContacts())

        def xml = fixtureMultipleStudents()
        dataLoadingService.loadStudents(xml)

        // Firstname and lastname are tested here
        bandit = Student.findByLastNameAndFirstName("Bandit", "Smokey" )
        assertNotNull "Smokey student loaded", bandit

        assertEquals 'Smokey assigned to correct parent', 'Theresa', bandit.contact.firstName
        assertEquals 'Smokey assigned to correct parent', 'Schmitzenbaumer', bandit.contact.lastName

        def smittyContact = Contact.findByLastNameAndFirstName("Tortenweasel", "Smitty")
        assertEquals 'Smitty has 2 students', 2, smittyContact.students.size()

        assertNotNull 'Totoro should have Smitty as a Contact', smittyContact.students.find {
            it.firstName == "Totoro"
        }

        assertNotNull 'Billy should have Smitty as a Contact', smittyContact.students.find {
            it.firstName == "Billy"
        }
   }

    def fixtureMultipleStudents() {
        def xml = '''<?xml version="1.0" encoding="UTF-8"?>
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  
xsi:noNamespaceSchemaLocation="Student.xsd">
'''

        // Smokey Bandit, dropped out, Parent is Tortenweasel, ClassID 13
        xml = xml + '''<Student> <StudentID>188</StudentID> <ParentID>8675309</ParentID> <LastName>Bandit</LastName> <FirstName>Smokey</FirstName> <Notes>Mentorship Interested</Notes>
<BirthDate>1997-01-07T00:00:00</BirthDate> <Grade>10</Grade> <ClassID>13</ClassID> <DropOut>1</DropOut> <SystemReceivedID>0</SystemReceivedID> <email>smokeybandit@reynolds.com</email>
</Student>
'''

        // Totoro Tortenweasel, Class ID 13, Graduated, Parent Tortenweasel
        xml = xml + '''<Student> <StudentID>188</StudentID> <ParentID>1010101</ParentID> <LastName>Tortenweasel</LastName>
<FirstName>Totoro</FirstName> <Gender>M</Gender> <BirthDate>1997-01-07T00:00:00</BirthDate> <Grade>10</Grade>
<ClassID>13</ClassID> <GraduateDate></GraduateDate> <DropOut>0</DropOut> <SystemReceivedID>0</SystemReceivedID> <email>totoro@alum.bworks.org</email>
</Student>
'''

        // Nobodys Student, Non-existent class ID
        xml = xml + ''' <Student> <StudentID>188</StudentID> <ParentID>1</ParentID>
<LastName>Nobodys</LastName> <FirstName>Iam</FirstName>
<BirthDate>1996-01-07T00:00:00</BirthDate> <Grade>4</Grade> <ClassID>1</ClassID> <GraduateDate>2007-08-18T00:00:00</GraduateDate> <DropOut>0</DropOut>
<SystemReceivedID>0</SystemReceivedID> <email></email> </Student> <Student>
'''

        // Billy Tortenweasel, graduated Class 13
        xml = xml + '''
<StudentID>188</StudentID>
<ParentID>1010101</ParentID>
<LastName>Tortenweasel</LastName>
<FirstName>Billy</FirstName>
<BirthDate>1997-01-07T00:00:00</BirthDate>
<Grade>10</Grade>
<ClassID>13</ClassID>
<GraduateDate>2007-08-18T00:00:00</GraduateDate>
<DropOut>0</DropOut>
<SystemReceivedID>0</SystemReceivedID>
<email>smokeybandit@reynolds.com</email>
</Student>
</dataroot>
'''

        return xml

    }

    def fixtureMultipleContacts() {
        def xml = '''<?xml version="1.0" encoding="UTF-8"?>
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  xsi:noNamespaceSchemaLocation="Parent.xsd">
<Parent>
<ParentID>1</ParentID>
<LastName>Malachi</LastName>
<FirstName>Satanya</FirstName>
<PrimaryPhone>(123) 222-4444</PrimaryPhone>
<DateOfSignUp>2009-10-10T00:00:00</DateOfSignUp>
<City>St. Elmo</City>
<State>MA</State>
<BroadbandAtHome>0</BroadbandAtHome>
<CouldNotReach>0</CouldNotReach>
<Address1>Level 1</Address1>
<Address2>Level Nine</Address2>
<Zip>63666</Zip>
</Parent>
<Parent>
<ParentID>3</ParentID>
<LastName>Chateaubolognese</LastName>
<ParentEmail>schmitzenblitzen@donner.com</ParentEmail>
<FirstName>Cherise</FirstName>
<PrimaryPhone>(314) 111-3333</PrimaryPhone>
<SecondPhone>(314) 111-2222</SecondPhone>
<DateOfSignUp>2006-01-02T00:00:00</DateOfSignUp>
<Address1>123 Chateaubolognese Street</Address1>
<Address2>Unit A</Address2>
<City>St. Louis</City>
<State>MO</State>
<Zip>63110</Zip>
<BroadbandAtHome>0</BroadbandAtHome>
<CouldNotReach>0</CouldNotReach>
</Parent>
<Parent>
<ParentID>1010101</ParentID>
<LastName>Tortenweasel</LastName>
<FirstName>Smitty</FirstName>
<PrimaryPhone>(314) 777-1111</PrimaryPhone>
<DateOfSignUp>2006-01-14T00:00:00</DateOfSignUp>
<Address1>789 Descending Street</Address1>
<City>St. Louis</City>
<State>MO</State>
<Zip>63104</Zip>
<BroadbandAtHome>0</BroadbandAtHome>
<CouldNotReach>0</CouldNotReach>
</Parent>
<Parent>
<ParentID>8675309</ParentID>
<LastName>Schmitzenbaumer</LastName>
<FirstName>Theresa</FirstName>
<PrimaryPhone>(314) 555-4444</PrimaryPhone>
<SecondPhone>(314) 333-2222</SecondPhone>
<DateOfSignUp>2006-01-21T00:00:00</DateOfSignUp>
<Address1>314 Phone Street</Address1>
<Address2>Unit Bevel Street</Address2>
<State>MO</State>
<Note>Some note that we should save.</Note>
<InfoTakenBy>Herr Cookie Monster</InfoTakenBy>
<BroadbandAtHome>0</BroadbandAtHome>
<CouldNotReach>1</CouldNotReach>
</Parent>
</dataroot>
'''
        return xml
    }
 
    // Utility method to search for comments that match
    // this thingy's origId
    def getCommentWithImportInfo(thingy, id) {

        def comment = thingy.comments.find {
            def matchString = "Original ID was :${id}"
            println "body" + it.body
            it.body =~ /Imported./ && it.body =~ /Original ID was :${id}:/
        }

        return comment
    }

    def fixtureSingleClassSession() {
        def xml = '''<?xml version="1.0" encoding="UTF-8"?>
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  xsi:noNamespaceSchemaLocation="Class.xsd">
<Class>
<ClassID>13</ClassID>
<Class1Date>2006-03-11T00:00:00</Class1Date>
<Class2Date>2006-03-18T00:00:00</Class2Date>
<Class3Date>2006-03-25T00:00:00</Class3Date>
<Class4Date>2006-04-01T00:00:00</Class4Date>
<Class5Date>2006-07-22T00:00:00</Class5Date>
<Class6Date>2006-08-26T00:00:00</Class6Date>
<ClassStartTime>11:00 am</ClassStartTime>
<IsActive>0</IsActive>
</Class>
</dataroot>
'''
        return xml
    }

}

