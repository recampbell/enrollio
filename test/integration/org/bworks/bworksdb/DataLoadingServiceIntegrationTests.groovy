package org.bworks.bworksdb
import org.bworks.bworksdb.util.TestKeys

import grails.test.*

class DataLoadingServiceIntegrationTests extends GrailsUnitTestCase {
    def dataLoadingService

    void testLoadClassSession() {
        def initClassSessionCount = ClassSession.count()

        def existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNull "Should not be a class session", existingSession

        def xml = fixtureSingleClassSession()
        dataLoadingService.loadClassSessions(xml)

        assertEquals "One more class session should now exist.", 1 + initClassSessionCount, ClassSession.count()

        existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNotNull "Class session should have been saved.", existingSession

    }

    void testClassSesssionStartDate() {
        def xml = fixtureSingleClassSession()
        dataLoadingService.loadClassSessions(xml)
        def cs = ClassSession.findByNameIlike("%2006-03-11%")
        assertEquals "Start date imported correctly", '2006-03-11', cs.startDate?.format('yyyy-MM-dd')
    }

    // Data loading service should use default Children's
    // program lessons and create lesson dates
    void testClassSesssionLessonDates() {
        def xml = fixtureSingleClassSession()
        dataLoadingService.loadClassSessions(xml)
        def cs = ClassSession.findByNameIlike("%2006-03-11%")
        assertEquals "Six lesson dates for imported session", 6, cs.lessonDates.size()

        // lesson dates are assigned in the lesson sequence order.
        // So, the first lesson in the list should be the Class1Date, and should be Intro to Computers
        // Test Lessons are loaded in TestDataService.loadDefaultCourses
        def introDate = cs.lessonDates.find { it.lesson.name == TestKeys.LESSON_KIDS_AEC_INTRO }
        assertNotNull introDate
        assertEquals "Intro class has correct date", '2006-03-11', introDate.lessonDate.format('yyyy-MM-dd')

        def wordDate = cs.lessonDates.find { it.lesson.name == "Word Processing" }
        assertNotNull wordDate
        assertEquals "Word Processing lesson date has correct date", '2006-03-25', 
                  wordDate.lessonDate.format('yyyy-MM-dd')

        def grad = cs.lessonDates.find { it.lesson.name == 'Graduation' }
        assertNotNull grad
        assertEquals "Grad class has correct date", '2006-08-26', grad.lessonDate.format('yyyy-MM-dd')
    }

    void testClassSesssionOrigIdIsPreserved() {
        def existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNull "Should not be a class session", existingSession
        def xml = fixtureSingleClassSession()
        dataLoadingService.loadClassSessions(xml)
        existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNotNull "Class session should have been saved.", existingSession

        assertNotNull "Comment about original ID was saved.", 
             getCommentWithImportInfo(existingSession, "1")

        existingSession = dataLoadingService.findClassSessionByOldId("1")
        assertNotNull "Comment about original ID was saved.", existingSession

    }

    void testLoadMultipleClassSessions() {
        def initClassSessionCount = ClassSession.count()

        def existingSession = ClassSession.findByNameIlike("%2006-09-09%")
        assertNull "Should not be a 09-09 class session", existingSession

        existingSession = ClassSession.findByNameIlike("%2006-07-22%")
        assertNull "Should not be a 7-22 class session either", existingSession

        def xml = fixtureMultipleClassSessions()
        dataLoadingService.loadClassSessions(xml)

        assertEquals "Four more class sessions should now exist.", 4 + initClassSessionCount, ClassSession.count()

        assertNotNull "Should have saved 09-09 class session", ClassSession.findByNameIlike("%2006-09-09%")
        assertNotNull "Should have saved 7-22 class session also", ClassSession.findByNameIlike("%2006-07-22%")

    }

    void testLoadContacts() {
        def initContactCount = Contact.count()

        def schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNull "Should not be Schmitzenbaumer", schmitz

        def torten = Contact.findByLastName("Tortenweasel")
        assertNull "Should not be Tortenweasel", torten

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)

        assertEquals "Four more contacts should now exist.", 4 + initContactCount, Contact.count()

        schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNotNull "Schmitzenbaumer should exist", schmitz

        torten = Contact.findByLastName("Tortenweasel")
        assertNotNull "Tortenweasel should exist", torten

    }

    void testContactNames() {
        def schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNull "Should not be Schmitzenbaumer", schmitz

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)

        // Last name loaded successfully if we find the contact.
        schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNotNull "Schmitzenbaumer should exist", schmitz
        assertEquals "Theresa", schmitz.firstName
    }

    void testContactAddresses() {
        def malachi = Contact.findByLastName("Malachi")
        assertNull "Should not be Malachi", malachi

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)

        malachi = Contact.findByLastName("Malachi")
        assertNotNull "malachi should exist", malachi

        assertEquals "Import address 1", "Level 1", malachi.address1
        assertEquals "Import address 2", "Level Nine", malachi.address2
        assertEquals "Import zip", "63666", malachi.zipCode
        assertEquals "Import city", "St. Elmo", malachi.city
        assertEquals "Import state", "MA", malachi.state
    }

    void testContactPhonesEmail() {
        def chateau = Contact.findByLastName("Chateaubolognese")
        assertNull "Should not be chateau", chateau

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)

        chateau = Contact.findByLastName("Chateaubolognese")
        assertNotNull "chateau should exist", chateau
        assertNotNull "Primary phone loaded", chateau.phoneNumbers.find {
            it.label == "Home" && it.phoneNumber =="(314) 111-3333"
        }

        assertNotNull "Secondary phone loaded", chateau.phoneNumbers.find {
            it.label == "Other" && it.phoneNumber == "(314) 111-2222"
        }

        assertEquals "Contact email imported", 'schmitzenblitzen@donner.com', chateau.emailAddress

        // torten should *not* have an "Other" phone loaded, nor an e-mail
        def torten = Contact.findByLastName("Tortenweasel")
        assertNull "NO Secondary phone loaded for Torten", torten.phoneNumbers.find {
            it.label == "Other"
        }
        
        assertNull "NO Contact email imported", torten.emailAddress
    }

    // test that "InfoTakenBy" field is recorded,
    // as well as a note about where the contact was loaded from.
    void testContactCommentAndNotes() {
        def schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNull "Should not be schmitzenbaumer", schmitz

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)

        schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNotNull "schmitz should exist", schmitz

        assertNotNull "Contact's Origin should be saved in a comment",
                      getCommentWithImportInfo(schmitz, '8675309')

        assertNotNull "Contact 'Info Taken By' should be saved ", schmitz.comments.find {
                          it.body == 'Info taken by: Herr Cookie Monster'
                      }

        assertNotNull "Contact Note should be saved ", schmitz.comments.find {
                          it.body == 'Some note that we should save.'
                      }
    }

    // Decided to add a signupDate to Contact
    void testContactDateCreated() {
        def schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNull "Should not be schmitzenbaumer", schmitz

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)
        schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNotNull "schmitz should exist", schmitz
        assertNotNull "Date of signup comment", schmitz.comments.find {
                          it.body =~ '2006-01-21'
                      }

        assertEquals "ContactSignup date is correct.", '2006-01-21', schmitz.signupDate.format('yyyy-MM-dd')
    }

    void testContactCannotBeReached() {
        def schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNull "Should not be schmitzenbaumer", schmitz

        def torten = Contact.findByLastName("Tortenweasel")
        assertNull "Should not be Tortenweasel", torten

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)

        torten = Contact.findByLastName("Tortenweasel")
        assertFalse 'Torten cannotreach should be false', torten.cannotReach
        
        schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertTrue 'Schmitz cannot reach should be true', schmitz.cannotReach

    }

    void testGetContactByOldId() {

        def schmitz = Contact.findByLastName("Schmitzenbaumer")
        assertNull "Should not be Schmitzenbaumer", schmitz

        def xml = fixtureMultipleContacts()
        dataLoadingService.loadContacts(xml)

        def con = dataLoadingService.findContactByOldId('8675309')

        assertNotNull "Contact should have comment with old ID", con
        assertEquals "Schmitzenbaumer", con.lastName
        assertEquals "Theresa", con.firstName
    }

    def fixtureSingleClassSession() {
        def xml = '''<?xml version="1.0" encoding="UTF-8"?>
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  xsi:noNamespaceSchemaLocation="Class.xsd">
<Class>
<ClassID>1</ClassID>
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


    def fixtureMultipleClassSessions() {
        def xml = '''<?xml version="1.0" encoding="UTF-8"?>
<dataroot xmlns:od="urn:schemas-microsoft-com:officedata" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"  xsi:noNamespaceSchemaLocation="Class.xsd">
<Class>
<ClassID>1</ClassID>
<Class1Date>2006-03-11T00:00:00</Class1Date>
<Class2Date>2006-03-18T00:00:00</Class2Date>
<Class3Date>2006-03-25T00:00:00</Class3Date>
<Class4Date>2006-04-01T00:00:00</Class4Date>
<Class5Date>2006-07-22T00:00:00</Class5Date>
<Class6Date>2006-08-26T00:00:00</Class6Date>
<ClassStartTime>11:00 am</ClassStartTime>
<IsActive>0</IsActive>
</Class>
<Class>
<ClassID>4</ClassID>
<Class1Date>2006-06-03T00:00:00</Class1Date>
<Class2Date>2006-06-10T00:00:00</Class2Date>
<Class3Date>2006-06-17T00:00:00</Class3Date>
<Class4Date>2006-06-24T00:00:00</Class4Date>
<Class5Date>2006-07-01T00:00:00</Class5Date>
<Class6Date>2006-07-08T00:00:00</Class6Date>
<ClassStartTime>11:00 am</ClassStartTime>
<IsActive>0</IsActive>
</Class>
<Class>
<ClassID>5</ClassID>
<Class1Date>2006-07-22T00:00:00</Class1Date>
<Class2Date>2006-07-29T00:00:00</Class2Date>
<Class3Date>2006-08-05T00:00:00</Class3Date>
<Class4Date>2006-08-12T00:00:00</Class4Date>
<Class5Date>2006-08-19T00:00:00</Class5Date>
<Class6Date>2006-08-26T00:00:00</Class6Date>
<ClassStartTime>11:00 am</ClassStartTime>
<IsActive>0</IsActive>
</Class>
<Class>
<ClassID>7</ClassID>
<Class1Date>2006-09-09T00:00:00</Class1Date>
<Class2Date>2006-09-16T00:00:00</Class2Date>
<Class3Date>2006-09-23T00:00:00</Class3Date>
<Class4Date>2006-09-30T00:00:00</Class4Date>
<Class5Date>2006-10-07T00:00:00</Class5Date>
<Class6Date>2006-10-14T00:00:00</Class6Date>
<ClassStartTime>11:00 am</ClassStartTime>
<IsActive>0</IsActive>
</Class>
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
<ParentID>4</ParentID>
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


}

