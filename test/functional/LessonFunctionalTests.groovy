import org.bworks.bworksdb.util.TestKeys
import org.bworks.bworksdb.Program

// Note: BootStrap adds methods to FunctionalTestCase

class LessonFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def testDataService

    void gotoLessonShow() {
        loginAs('bob', 'bobbobbob0')
        click('Programs')
        click(TestKeys.PROGRAM_KIDS_AEC)
        click(TestKeys.LESSON_KIDS_AEC_INTRO)
    }

    void testLessonShow() {
        loginAs('bob', 'bobbobbob0')
        click('Programs')
        assertStatus 200
        click(TestKeys.PROGRAM_KIDS_AEC)
        assertStatus 200
        click(TestKeys.LESSON_KIDS_AEC_INTRO)
        assertStatus 200

        assertContentContains TestKeys.LESSON_KIDS_AEC_INTRO_DESCRIPTION.replace("\n", "<br />")
        
    }

    void testLessonNew() {
        loginAs('bob', 'bobbobbob0')
        click('Programs')
        assertStatus 200
        click(TestKeys.PROGRAM_KIDS_AEC)
        assertStatus 200
        click('New Lesson')
        assertStatus 200

        form('newLessonForm') {
            name = 'New Foo Lesson'
            // Give this lesson the same seq. as another lesson
            sequence = 123
            click('Save')
        }

        assertStatus 200
        assertContentContains 'created'
        assertContentContains 'New Foo Lesson'
        // Now, make sure our brand-new lesson belongs to the PROGRAM_KIDS_EAC
        click('Programs')
        assertStatus 200
        click(TestKeys.PROGRAM_KIDS_AEC)
        assertStatus 200
        assertContentContains 'New Foo Lesson'
    }

    // TODO just test the lesson re-sort page
    void testLessonResort() {
    }

    void testLessonList() {
        loginAs('bob', 'bobbobbob0')
        get('/lessons')
        assertStatus 200
        assertTitleContains 'Lessons'
        assertContentContains TestKeys.PROGRAM_KIDS_AEC
        assertContentContains TestKeys.PROGRAM_MENTORSHIP
        assertContentContains TestKeys.PROGRAM_ADULT_AEC

        assertContentContains TestKeys.LESSON_KIDS_AEC_INTRO

        // Break description into parts on newline, 'cause the HTML
        // formatting causes problems by putting in a <br />.
        def desc_parts = TestKeys.LESSON_KIDS_AEC_INTRO_DESCRIPTION.split("\n") 
        // Just make sure we see the pieces of the description.
        desc_parts.each {
            assertContentContains it
        }
        
    }

    void testLessonEdit() {
        loginAs('bob', 'bobbobbob0')
        gotoLessonShow()
        click('Edit')
        assertStatus 200
        assertTitleContains 'Edit Lesson:'
        assertContentContains 'Edit Lesson:'
        // Should belong to kids Earn-A-Computer course
        assertContentContains TestKeys.PROGRAM_KIDS_AEC
        form('editLessonForm') {
            name = 'This is a Lesson in Politics'
            description = 'You ain\'t seen nothin\' yet'
            // Select Mentorship Program
            // TODO: See if we can select course by name, like the
            // user would.
            selects['course.id'].select "3"
            sequence = 12345
            click ('Update')
        }

        assertStatus 200
        assertContentContains TestKeys.PROGRAM_MENTORSHIP
        assertContentContains 'This is a Lesson in Politics'
        assertContentContains 'You ain\'t seen nothin\' yet'
    }




}
