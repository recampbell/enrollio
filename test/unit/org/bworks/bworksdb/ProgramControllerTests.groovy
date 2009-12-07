package org.bworks.bworksdb

import grails.test.*

class ProgramControllerTests extends grails.test.ControllerUnitTestCase {
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testList_LessThanMax() {
        mockDomain(Program, [new Program(name:'p1'), new Program(name:'p2')] )
        mockParams.max = 3
        
        // test
        def model = controller.list()
        
        assertEquals 2, model.programInstanceList.size()
        assertEquals 2, model.programInstanceTotal
    }

    void testList_MoreThanMax() {
        mockDomain(Program, [new Program(name:'p1'), new Program(name:'p2'), new Program(name:'p3')] )
        mockParams.max = 2
        
        // test
        def model = controller.list()
        
        assertEquals 2, model.programInstanceList.size()
        assertEquals 3, model.programInstanceTotal
    }
    
    void testShow_Found() {
        mockDomain(Program, [new Program(id:88)] )
        mockParams.id = 88
        
        // test
        def model = controller.show()
        
        assertEquals 88, model.programInstance.id
    }
    
    void testShow_NotFound() {
        mockDomain(Program, [new Program(id:77)] )
        mockParams.id = 88
        
        // test
        def model = controller.show()
        
        assertNotNull mockFlash.message 
        assertEquals controller.list, redirectArgs.action
    }
}
