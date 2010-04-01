package org.bworks.bworksdb

import grails.test.*

class InterestDataLoadingIntegrationTests extends GrailsUnitTestCase {
    def dataLoadingService

    protected void setUp() {
        super.setUp()
        // load class session, contact and student data
        // yes, it's tedious but it's an integration test, meow.
        dataLoadingService.loadClassSessions(fixtureSingleClassSession())
        dataLoadingService.loadContacts(fixtureMultipleContacts())
    }

    void testStudentInterests() {
        def initEnrollmentCount = Enrollment.count()
        def initInterestCount = Interest.count()
        // load student fixtures.   There's 2 students that have existing enrollments
        // and 3 that are not enrolled in anything.
        dataLoadingService.loadStudents(fixtureMultipleStudents())

        // Make sure we didn't enroll too many students
        def enrStuds = Enrollment.list()

        assertEquals "Students w/o classIds were not enrolled", initEnrollmentCount + 2, enrStuds.size()
        assertNull enrStuds.find {
            it.student.lastName == "Interested"
        }

        def ints = Interest.list()
        assertEquals "Correct # of interests created", initInterestCount + 3, ints.size()

        def ima = ints.find {
            it.student.lastName == "Interested" && it.student.firstName == "Ima"
        }
        assertNotNull "Interest added for Ima Interested", ima
        // Interest date should be signup date of the student's parent.
        assertEquals "Signup date for ima is correct?", '2006-01-02', ima.signupDate.format('yyyy-MM-dd')

        def alsoa = ints.find {
            it.student.lastName == "Interested" && it.student.firstName == "Alsoa"
        }
        assertNotNull "Interest added for Alsoa Interested", alsoa
        // Interest date should be signup date of the student's parent.
        assertEquals "Signup date for alsoa is correct?", '2006-01-02', alsoa.signupDate.format('yyyy-MM-dd')

        def metooa = ints.find {
            it.student.lastName == "Chateaubolognese" && it.student.firstName == "Metooa"
        }
        assertNotNull "Interest added for Metooa", metooa
        // Interest date should be signup date of the student's parent.
        assertEquals "Signup date for metooa is correct?", '2009-10-10', metooa.signupDate.format('yyyy-MM-dd')
        

    }


    def fixtureMultipleStudents() {
        def xml = '''<?xml version="1.0" encoding="UTF-8"?><dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  
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

        // Students not enrolled in any class (3)
        xml = xml + '''<Student>
<StudentID>470</StudentID>
<ParentID>3</ParentID>
<LastName>Interested</LastName>
<FirstName>Ima</FirstName>
<BirthDate>2001-05-12T00:00:00</BirthDate>
<Grade>3</Grade>
<DropOut>0</DropOut>
<SystemReceivedID>0</SystemReceivedID>
</Student>
<Student>
<StudentID>471</StudentID>
<ParentID>3</ParentID>
<LastName>Interested</LastName>
<FirstName>Alsoa</FirstName>
<BirthDate>1998-05-12T00:00:00</BirthDate>
<Grade>8</Grade>
<DropOut>0</DropOut>
<SystemReceivedID>0</SystemReceivedID>
</Student>
<Student>
<StudentID>470</StudentID>
<ParentID>1</ParentID>
<LastName>Chateaubolognese</LastName>
<FirstName>Metooa</FirstName>
<BirthDate>2000-03-12T00:00:00</BirthDate>
<Grade>7</Grade>
<DropOut>0</DropOut>
<SystemReceivedID>0</SystemReceivedID>
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
<LastName>Interested</LastName>
<FirstName>Parentia</FirstName>
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
<ParentEmail>parent@interested.com</ParentEmail>
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

