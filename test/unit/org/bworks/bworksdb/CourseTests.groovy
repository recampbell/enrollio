package org.bworks.bworksdb
import grails.test.*

class CourseTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints_Green() {
        mockDomain(Course)

        def course = new Course(name:"Lahey", description:"desc")

        // test
        assertTrue course.validate()
    }    

    void testConstraints_Red_NameBlank() {
        mockDomain(Course)

        def course = new Course(name:"", description:"desc")

        // test
        assertFalse course.validate()
    }    

    void testConstraints_Red_NoDescription() {
        mockDomain(Course)

        def course = new Course(name:"Lahey")
                
        // test
        assertFalse course.validate()
    }    

}
