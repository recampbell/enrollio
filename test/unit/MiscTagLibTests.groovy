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
        // called tabName, which returns 'course'
        MiscTagLib.metaClass.pageProperty = { tabName ->
            return 'course'
        }

        // pretend like we're checking if the tab
        // we're evaluating should have a class called 'current', 
        // or the default class of 'enr-top-menu-itme' 
        tagLib.isCurrentTab(tabName:'thisAintTheProgramTabName') 
        assertEquals 'enr-top-menu-item', tagLib.out.toString()

    }

    void testIsCurrentTab() {
        // pretend like a page we're viewing has a pageProperty
        // called tabName, which returns 'course'
        MiscTagLib.metaClass.pageProperty = { attr ->
            return 'course'
        }

        // pretend like we're checking if we're on a
        // page that should have a tabName == 'course'.
        // In this case, we should get back the word 'current'
        tagLib.isCurrentTab(tabName:'course') 
        assertEquals 'current', tagLib.out.toString()

    }

     void testIsLoginTab() {
        // pretend like a page we're viewing has a pageProperty
        // called tabName, which returns 'course'
        MiscTagLib.metaClass.pageProperty = { attr ->
            return 'login'
        }

        // pretend like we're checking if we're on a
        // page that should have a tabName == 'course'.
        // In this case, we should get back the word 'current'
        tagLib.isLoginTab(tabName:'login')
        assertEquals 'logintab current', tagLib.out.toString()

    }

    void testMascotIcon() {
        def iconFile = 'http://monkeyboy.tx/someimage.gif' 
        def mockConfig = [
            getSetting: { attrs ->
                return iconFile
            }
        ]
        def tl = new MiscTagLib(configSettingService:mockConfig)

        tl.mascotIcon()
        assertEquals '<img src="' + iconFile + '" />', tagLib.out.toString()
    }

    // We shouldn't get anything back if there's no mascotIcon defined.
    void testNoMascotIcon() {
        def mockConfig = [
            getSetting: { attrs ->
                return ''
            }
        ]
        def tl = new MiscTagLib(configSettingService:mockConfig)

        tl.mascotIcon()
        assertEquals '', tagLib.out.toString()
    }
}
