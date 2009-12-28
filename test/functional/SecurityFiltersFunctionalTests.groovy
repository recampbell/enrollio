class SecurityFiltersFunctionalTests extends functionaltestplugin.FunctionalTestCase {
    void testHelpNoLogin() {
        // Here call get(uri) or post(uri) to start the session
        // and then use the custom assertXXXX calls etc to check the response
        //
        get('/help')
        assertTitleContains "Help"
        // assertContentContains 'the expected text'
    }
}
