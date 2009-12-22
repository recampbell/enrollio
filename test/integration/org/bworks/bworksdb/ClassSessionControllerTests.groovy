package org.bworks.bworksdb

import grails.test.*

class ClassSessionControllerTests extends grails.test.ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    // Invalid class session should result in a redirect
    // to the create method
    void testInvalidClassSessionDoesntCrash() {
        controller.save()
        // Make sure we're redirected to the 'create' view.
        assertEquals "create", renderArgs.view

    }
}
