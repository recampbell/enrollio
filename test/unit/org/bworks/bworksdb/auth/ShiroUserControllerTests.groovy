
package org.bworks.bworksdb.auth

import grails.test.*

class ShiroUserControllerTests extends grails.test.ControllerUnitTestCase {
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUpdate_NotFound() {
        mockDomain(ShiroUser, [new ShiroUser(username:'username', firstName:'first', lastName:'last', id:66)] )
        mockParams.id = 99
        
        // test
        def model = controller.update()
        
        assertNotNull mockFlash.message 
        assertEquals controller.list, redirectArgs.action
    }
    
    void testUpdate_VersionMisMatch() {
        def user = new ShiroUser(username:'username', firstName:'first', lastName:'last', id:66, version:"5")
        mockDomain(ShiroUser, [user] )
        mockParams.id = 66
        mockParams.version = "1"
        
        // test
        def model = controller.update()
        
        assertEquals "edit", renderArgs.view
        assertNotNull user.errors
    }

    void testUpdate_Green() {
        def user = new ShiroUser(username:'username', firstName:'first', 
                                    lastName:'last', id:66, password:"swordfish", passwordConfirm:"swordfish")
        mockDomain(ShiroUser, [user] )
        mockParams.id = 66
        
        // test
        def model = controller.update()

        assertEquals controller.show, redirectArgs.action        
    }
    
}
