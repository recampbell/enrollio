package org.bworks.bworksdb

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

    void testClassSesssionOrigIdIsPreserved() {
        def existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNull "Should not be a class session", existingSession
        def xml = fixtureSingleClassSession()
        dataLoadingService.loadClassSessions(xml)
        existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNotNull "Class session should have been saved.", existingSession

        assertNotNull "Comment about original ID was saved.", 
             getCommentAboutThisThingy(existingSession, "1")
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
        // assertNotNull "Comment about Schmitzenbaumer's ID was saved.", 
             // getCommentAboutThisThingy(schmitz, 7771111)

        torten = Contact.findByLastName("Tortenweasel")
        assertNotNull "Tortenweasel should exist", torten
        // assertNotNull "Comment about Tortenweasel's ID was saved.", 
             // getCommentAboutThisThingy(torten, 696969)

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

    def loadContactPhones() {
// <PrimaryPhone>(314) 588-7080</PrimaryPhone>
// <SecondPhone>(314) 769-9875</SecondPhone>
        
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
<ParentID>5</ParentID>
<LastName>Schmitzenbaumer</LastName>
<FirstName>Theresa</FirstName>
<PrimaryPhone>(314) 555-4444</PrimaryPhone>
<SecondPhone>(314) 333-2222</SecondPhone>
<DateOfSignUp>2006-01-21T00:00:00</DateOfSignUp>
<Address1>314 Phone Street</Address1>
<Address2>Unit Bevel Street</Address2>
<City>St. Louis</City>
<State>MO</State>
<Zip>63107</Zip>
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
    def getCommentAboutThisThingy(thingy, id) {

        def comment = thingy.comments.find {
            def matchString = "Original ID was :${id}"
            println "body" + it.body
            it.body =~ /Imported./ && it.body =~ /Original ID was :${id}:/
        }

        return comment
    }


}

