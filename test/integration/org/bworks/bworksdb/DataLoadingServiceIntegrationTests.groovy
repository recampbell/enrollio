package org.bworks.bworksdb

import grails.test.*

class DataLoadingServiceIntegrationTests extends GrailsUnitTestCase {
    def dataLoadingService

    void testLoadClassSession() {
        def existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNull "Should not be a class session", existingSession

        def xml = fixtureClassSession()
        dataLoadingService.loadClassSessions(xml)

        existingSession = ClassSession.findByNameIlike("%2006-03-11%")
        assertNotNull "Class session should have been saved.", existingSession

        assertTrue "Class session name should have first class session date in it",
                   existingSession.name =~ /2006-03-11/

                     
    }


    def fixtureClassSession() {
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

}

