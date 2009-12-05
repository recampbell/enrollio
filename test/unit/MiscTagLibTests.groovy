import grails.test.*

class MiscTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    
    void testIsNotCurrentTab() {
        // pretend like a page we're viewing has a pageProperty
        // called tabName, which returns 'program'
        MiscTagLib.metaClass.pageProperty = { tabName ->
            return 'program'
        }

        // pretend like we're checking if the tab
        // we're evaluating should have a class called 'current', 
        // or the default class of 'enr-top-menu-itme' 
        tagLib.isCurrentTab(tabName:'thisAintTheProgramTabName') 
        assertEquals 'enr-top-menu-item', tagLib.out.toString()

    }

    void testIsCurrentTab() {
        // pretend like a page we're viewing has a pageProperty
        // called tabName, which returns 'program'
        MiscTagLib.metaClass.pageProperty = { attr ->
            return 'program'
        }

        // pretend like we're checking if we're on a
        // page that should have a tabName == 'program'.
        // In this case, we should get back the word 'current'
        tagLib.isCurrentTab(tabName:'program') 
        assertEquals 'current', tagLib.out.toString()

    }
}
