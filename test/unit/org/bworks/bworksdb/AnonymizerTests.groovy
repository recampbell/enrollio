package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.Anonymizer

class AnonymizerTests extends GrailsUnitTestCase {
    // Anonymizer instance
    def anon 

    protected void setUp() {
        anon = new Anonymizer()
        super.setUp()
    }

    // TODO: Take these redundant tests, and find how to create one basic test pattern
    // that will test all the random methods in the Anonymizer class
    // Makes sure we only get what we have in our list
    void testFirstNamesExclusive() {
        def expectedNames = ['Bob', 'Dick', 'Jane'] 
        // Set the anonymizer's list of names == to our expected list
        // -- this will limit the random results to items in the expectedNames list
        // clone() is very important
        anon.firstNames = expectedNames.clone()
        50.times {
            // TODO figure out why one way of asserting works, but 
            // it doesn't work when I call the function inside the find { } closure
            // TODO This doesn't work.  Why?????
            // assert expectedNames.find { it == anon.firstName() }
            
            // This works
            def name = anon.firstName()
            def found = expectedNames.find { it == name }

            assert found != null, "${name} must be one of ${expectedNames}."
        }
    }

    // Make sure that a random sampling will select each name
    // at least once
    void testFirstNamesEachSelected() {
        def expectedNames = ['Bob', 'Dick', 'Jane'] 

        anon.firstNames = expectedNames.clone()
        50.times {
            def name = anon.firstName()
            expectedNames.remove(name)
        }

        assert expectedNames.size() == 0, "Randomizer did not select name: ${expectedNames}"
    }

    // TODO: Take these redundant tests, and find how to create one basic test pattern
    // that will test all the random methods in the Anonymizer class
    // Makes sure we only get what we have in our list
    void testLastNameExclusive() {
        def expectedNames = ['Neff', 'Wagner', 'Dracula'] 
        // Set the anonymizer's list of names == to our expected list
        // -- this will limit the random results to items in the expectedNames list
        // clone() is very important
        anon.lastNames = expectedNames.clone()
        50.times {
            def name = anon.lastName()
            def found = expectedNames.find { it == name }
            assert found != null, "${name} must be one of ${expectedNames}."
        }
    }

    // Make sure that a random sampling will select each name
    // at least once
    void testLastNamesEachSelected() {
        def expectedNames = ['Neff', 'Wagner', 'Dracula'] 

        anon.lastNames = expectedNames.clone()
        50.times {
            def name = anon.lastName()
            expectedNames.remove(name)
        }

        assert expectedNames.size() == 0, "Randomizer did not select name: ${expectedNames}"
    }
    void testLastName() {
        assert anon.lastName()
    }
}
