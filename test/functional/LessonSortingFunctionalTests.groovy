import org.bworks.bworksdb.util.TestKeys
import org.bworks.bworksdb.Course
class LessonSortingFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def testDataService
    def sessionFactory

    // TODO loginAs should be refactored into a
    // common method -- it's also used in SecurityFiltersFunctionalTests
    void loginAs(userName, pass) {
        get('/login')
        form('loginForm') {
            username = userName
            password = pass
            click "Sign in"
        }
    }

    // TODO: Should probably use hard-coded data
    // for the lesson names, and not fish it from the HTML pages
    void testNewLessonWithSort() {
        loginAs('bob', 'bobbobbob0')
        click('Courses')
        click(TestKeys.PROGRAM_KIDS_AEC)
        click('New Lesson')
        assertStatus 200


        def lessonNodes = byXPath("//*[starts-with(@name, 'lessonName_')]")
        // assertEquals 'Intro to Computers', lessonNodes[0].getTextContent().trim()

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

    void testLessonSortLink() {
        loginAs('bob', 'bobbobbob0')
        click('Courses')
        click(TestKeys.PROGRAM_KIDS_AEC)
        assertContentContains('Sort Lessons')

        click('Courses')
        // mentorship course doesn't have classes
        // make sure that no lessons are assigned to the mentorship
        // course in the tests!
        click(TestKeys.PROGRAM_MENTORSHIP)
        shouldFail() {
            assertContentContains('Sort Lessons')
        }
    }

    void testLessonSortPage() {
        loginAs('bob', 'bobbobbob0')
        click('Courses')
        click(TestKeys.PROGRAM_KIDS_AEC)
        click('Sort Lessons')
        assertStatus 200
        assertTitleContains 'Sort Lessons - Children'

        def lessonNodes = byXPath("//*[starts-with(@name, 'lessonName_')]")
        // assertEquals 'Intro to Computers', lessonNodes[0].getTextContent()

        def lessonNames = lessonNodes.collect {
            it.getTextContent().trim()
        }

        def lessonSequences = byXPath("//input[starts-with(@name, 'lessonId_')]")
        lessonSequences.each {
            it.setAttribute('value', '-' + it.getValueAttribute())    
        }

        // TODO: assert that we saved o.k.
        click('saveButton')

        // Should be re-directed back to lessons page
        assertTitleContains "Lessons - Children's"
        assertContentContains "Lessons successfully sorted."

        // Make sure our lessons are reversed.
        def expectedLessons = lessonNames.reverse()

        def shownLessonNodes = byXPath("//a[starts-with(@name, 'lessonLink')]")
        def shownLessons = shownLessonNodes.collect {
            it.getTextContent().trim()
        }
        assertEquals expectedLessons, shownLessons

    }

}
