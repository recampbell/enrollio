package org.bworks.bworksdb

import grails.test.*

class AttendanceDataLoadingIntegrationTests extends GrailsUnitTestCase {

    def dataLoadingService

    // If session < 90 days in the past, non-graduated students should
    // be marked 'IN PROGRESS'
    void testNonDropoutAttendances() {
        // load class session, contact and student data
        // yes, it's tedious but it's an integration test, meow.
        dataLoadingService.loadClassSessions(fixtureSingleClassSession())

        def sess = dataLoadingService.findClassSessionByOldId("13")

        dataLoadingService.loadContacts(fixtureMultipleContacts())
        dataLoadingService.loadStudents(fixtureMultipleStudents())

        def notGraduatedStudent = Student.findByFirstNameAndLastName('Totoro','Tortenweasel')
        
        sess.lessonDates.each {
            assertNull it.attendees.find { att ->
                att.student == notGraduatedStudent
            }
        }

        def graduatedStudent = Student.findByFirstNameAndLastName('Billy','Tortenweasel')
        def attendances = []
        sess.lessonDates.each { ld ->
            attendances.add(ld.attendees.findAll { att ->
                att.student == graduatedStudent
            })
        }
        assertNotNull attendances
        assertEquals 6, attendances.size()
        attendances.each {
            assertEquals "present", it.status
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

    // Utility method to search for comments that match
    // this thingy's origId
    def getCommentWithImportInfo(thingy, id) {

        return comment
    }

}
