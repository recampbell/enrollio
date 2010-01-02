class ContactFunctionalTests extends functionaltestplugin.FunctionalTestCase {
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
        assertTitleContains('Show Contact')
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
        assertTitleContains('Show Contact')
    }
}
