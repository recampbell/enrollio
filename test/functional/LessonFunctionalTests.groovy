import org.bworks.bworksdb.util.TestKeys
class LessonFunctionalTests extends functionaltestplugin.FunctionalTestCase {
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

        assertContentContains TestKeys.LESSON_KIDS_AEC_INTRO_DESCRIPTION
        
    }

    void testLessonEdit() {
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
