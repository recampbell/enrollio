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
        def comment = existingSession.comments.findAll {
            it.body =~ /Imported./ && it.body =~ /Original ID was :1:/
        }

        assertNotNull "Comment about original ID was saved.", comment
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


}

