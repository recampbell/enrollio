import grails.test.*

class EnrollioTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    
    // test Enrollio's awesome date format
    void testFormatDate() {
        def testDate = Date.parse('MM/dd/yyyy', '4/20/2010')
        tagLib.formatDate(date:testDate)
        assertEquals  'April 20, 2010', tagLib.out.toString()

    }

    void testFormatDateSingleDigitDay() {
        def testDate = Date.parse('MM/dd/yyyy', '1/1/2009')
        tagLib.formatDate(date:testDate)
        assertEquals 'January 1, 2009', tagLib.out.toString()

    }

    void testFormatDateNullDay() {
        def testDate = null
        tagLib.formatDate(date:testDate)
        assertEquals '', tagLib.out.toString()
    }

}
