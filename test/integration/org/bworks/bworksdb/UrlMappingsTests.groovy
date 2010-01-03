package org.bworks.bworksdb

import grails.test.*

class UrlMappingsTests extends GrailsUrlMappingsTestCase {
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDefaultMapping() {
        assertUrlMapping("/", controller:'help', action:'about')
    }

    void testContactMappings() {
        assertUrlMapping("/createContact", controller:'contact', action:'create')
        assertUrlMapping("/editContact/1", controller:'contact', action:'edit') {
            id = "1"
        }
    }
}
