import org.bworks.bworksdb.util.TestKeys
class ContactFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def testDataService
    // TODO loginAs should be refactored into a
    // common method -- it's also used in SecurityFiltersFunctionalTests
    void loginAs(userName, pass) {
        get('/login')
        form('loginForm') {
            username = userName
            password = pass
            click "login"
        }
    }

    void testContactSearchBarOnContactList() {
        // Contact search bar should be on the /contacts and the /contact/(some id) pages
        loginAs('bob', 'bobbobbob0')
        click("Contacts")
        assertNotNull byId('contactSearchButton')
        assertNotNull byId('contactSearchForm')
        assertContentContains('Search')
    }
  
    // TODO: This test is a bit long, and really tests two things:
    // going to the contact/show page, and asserting that the search bar is there.
    void testContactSearchBarOnContactShow() {
        // Contact search bar should be on the /contacts and the /contact/(some id) pages
        loginAs('bob', 'bobbobbob0')
        click("Contacts")
        // Find any link that goes to a contact
        def contactLink = byXPath("//a[starts-with(@id,'contactLink')]")
        assertNotNull contactLink
        contactLink = contactLink instanceof ArrayList ? contactLink[0] : contactLink
        click(contactLink.id)
        assertStatus 200
        assertTitleContains('Contact:')
        // Assert that the search bar is here!
        assertContentContains('Search')
        assertNotNull byId('contactSearchButton')
        assertNotNull byId('contactSearchForm')
    }

    
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
                println "text content is: " + it.getTextContent()
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
