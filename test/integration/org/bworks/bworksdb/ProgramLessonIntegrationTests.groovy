package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.util.TestKeys

class ProgramServiceTests extends GroovyTestCase {

    ProgramService programService

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSortLessons() {
        def p = Program.findByName(TestKeys.PROGRAM_KIDS_AEC)
        assertEquals 6, p.lessons.size()
        def l
        p.addToLessons(new Lesson(name:'New Lesson', description:'Blah', sequence: 7))
        p.save(flush:true)
        assertEquals 7, p.lessons.size()

        assertEquals 'New lesson is saved as last lesson', 'New Lesson', p.lessons.last().name

        // define a list of maps, with lesson id and its new sequence
        // Multiply by negative one, to reverse the sequence
        def m = p.lessons.collect {
            [ lessonId : it.id,
              sequence : it.sequence * -1 ]
        }

        println m

        programService.sortLessons(m)
        p = Program.findByName(TestKeys.PROGRAM_KIDS_AEC)

        assertEquals 'New lesson reordered as first lesson', 'New Lesson', p.lessons.first().name
    }
}
