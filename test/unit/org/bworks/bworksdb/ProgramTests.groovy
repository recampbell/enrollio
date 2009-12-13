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

        def program = new Program(name:"Lahey", description:"desc")

        // test
        assertTrue program.validate()
    }    

    void testConstraints_Red_NameBlank() {
        mockDomain(Program)

        def program = new Program(name:"", description:"desc")

        // test
        assertFalse program.validate()
    }    

    void testConstraints_Red_NoDescription() {
        mockDomain(Program)

        def program = new Program(name:"Lahey")
                
        // test
        assertFalse program.validate()
    }    

}
