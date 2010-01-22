import org.bworks.bworksdb.util.TestKeys
import org.bworks.bworksdb.Program
class LessonFunctionalTests extends functionaltestplugin.FunctionalTestCase {

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

    // TODO: Should probably use hard-coded data
    // for the lesson names, and not fish it from the HTML pages
    void testNewLessonWithSort() {
        loginAs('bob', 'bobbobbob0')
        click('Programs')
        click(TestKeys.PROGRAM_KIDS_AEC)
        click('New Lesson')
        assertStatus 200


        def lessonNodes = byXPath("//*[starts-with(@name, 'lessonName_')]")
        // assertEquals 'Intro to Computers', lessonNodes[0].getTextContent()

        def lessonNames = lessonNodes.collect {
            it.getTextContent().trim()
        }

        def lessonSequences = byXPath("//input[starts-with(@name, 'lessonId_')]")
        lessonSequences.each {
            it.setAttribute('value', '-' + it.getValueAttribute())    
        }

        form('newLessonForm') {
            name = 'Re-sort Foo Lesson'
            // Give this lesson the same seq. as another lesson
            sequence = 123
            click('Save')
        }

        // TODO: assert that we saved o.k.

        // Whew!  Now, click 'Lessons'
        click('Lessons')

        // Make sure our lessons are reversed.
        def expectedLessons = lessonNames.reverse()
        expectedLessons.add(0, 'Re-sort Foo Lesson')

        def shownLessonNodes = byXPath("//a[starts-with(@name, 'lessonLink')]")
        def shownLessons = shownLessonNodes.collect {
            it.getTextContent().trim()
        }
        assertEquals expectedLessons, shownLessons

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
        // Should belong to kids Earn-A-Computer program
        assertContentContains TestKeys.PROGRAM_KIDS_AEC
        form('editLessonForm') {
            name = 'This is a Lesson in Politics'
            description = 'You ain\'t seen nothin\' yet'
            // Select Mentorship Program
            // TODO: See if we can select program by name, like the
            // user would.
            selects['program.id'].select "3"
            sequence = 12345
            click ('Update')
        }

        assertStatus 200
        assertContentContains TestKeys.PROGRAM_MENTORSHIP
        assertContentContains 'This is a Lesson in Politics'
        assertContentContains 'You ain\'t seen nothin\' yet'
    }




}
