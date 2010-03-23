import org.bworks.bworksdb.util.TestKeys

// Note: BootStrap adds methods to FunctionalTestCase

class ContactFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def testDataService

    void testRegularUserGoesToContactShow() {
        
        loginAs('bob', 'bobbobbob0')
        click("Contacts")

        // this doesn't work:
        // def contactLink = byXPath("//a[matches(@href,'/contact')]")
        def contactLink = byXPath("//a[starts-with(@id,'contactLink')]")
        assertNotNull contactLink
        contactLink = contactLink instanceof ArrayList ? contactLink[0] : contactLink
        // Click on the link -- using its ID <evil laugh>
        click(contactLink.id)
        assertStatus 200
        assertTitleContains('Contact:')
    }

    void testRegularUserGoesToContactEdit() {
        
        loginAs('bob', 'bobbobbob0')
        click("Contacts")

        def contactLink = byXPath("//a[starts-with(@id,'contactLink')]")
        assertNotNull contactLink
        contactLink = contactLink instanceof ArrayList ? contactLink[0] : contactLink
        // Click on the link -- using its ID <evil laugh>
        click(contactLink.id)
        assertStatus 200
        assertTitleContains('Contact:')
        click("Edit")
        assertTitleContains('Edit')
        assertStatus 200
    }

    void testContactsShownOnContactsPage() {
        loginAs('bob', 'bobbobbob0')
        click("Contacts")
        assertContentContains TestKeys.CONTACT1_LAST_NAME

    }

    void testStudentEditLinkFromContactPage() {
        // Purify test data.  There's an issue to use setup and teardown
        // methods, so don't whine.
        loginAs('bob', 'bobbobbob0')
        click("Contacts")
        assertStatus 200
        assertContentContains TestKeys.CONTACT1_LAST_NAME

        def contactLink = byXPath('//a[starts-with(@id, "contactLink")]')
        if (contactLink instanceof ArrayList) {
            contactLink = contactLink.find {
                it.getTextContent() =~ TestKeys.CONTACT1_LAST_NAME
            }
        }
        assertNotNull contactLink
        contactLink.click()

        def studentLink = byXPath('//a[starts-with(@name, "editStudent")]')

        if (studentLink instanceof ArrayList) {
            studentLink = studentLink.find {
                it.getAttribute('title') =~ TestKeys.STUDENT
            }
        }
        assertNotNull studentLink
        studentLink.click()

        assertStatus 200
        assertTitleContains 'Edit Student'
        assertContentContains TestKeys.STUDENT
    }
}
