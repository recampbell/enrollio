package org.bworks.bworksdb
import grails.test.*

class ProgramTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints_Green() {
        mockDomain(Program)

        def course = new Program(name:"Lahey", description:"desc")

        // test
        assertTrue course.validate()
    }    

    void testConstraints_Red_NameBlank() {
        mockDomain(Program)

        def course = new Program(name:"", description:"desc")

        // test
        assertFalse course.validate()
    }    

    void testConstraints_Red_NoDescription() {
        mockDomain(Program)

        def course = new Program(name:"Lahey")
                
        // test
        assertFalse course.validate()
    }    

}
