class SecurityFiltersTests extends grails.util.WebTest {

    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001SecurityFiltersListNewDelete

   void testSecurityFiltersHelp() {
        invoke 'help'
        verifyTitle(regex:true, text:'.*Help.*', description:'Get to help files w/o login')
    }
}
