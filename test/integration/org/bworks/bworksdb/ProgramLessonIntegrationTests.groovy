package org.bworks.bworksdb

import grails.test.*
import org.bworks.bworksdb.util.TestKeys

class ProgramLessonIntegrationTests extends GroovyTestCase {

    ProgramService programService

    Program prog

    protected void setUp() {
        super.setUp()
        prog = new Program(name:'Functional Test Program', description: 'Func Test Desc.')
        assertTrue prog.validate()
        
        prog.addToLessons(new Lesson(name:'FTP Lesson 4', description: 'Foo', sequence: 300))
        prog.addToLessons(new Lesson(name:'FTP Lesson 3', description: 'Foo', sequence: 200))
        prog.addToLessons(new Lesson(name:'FTP Lesson 1', description: 'Foo', sequence: 100))
        prog.save(flush:true)
        
    }

    protected void tearDown() {
        super.tearDown()
        prog.delete(flush:true)
    }

    void testInitialSortOfLessons() {
        prog.refresh()
        def lessons = prog.lessons.collect { it.name }

        assertEquals(['FTP Lesson 1',
                      'FTP Lesson 3',
                      'FTP Lesson 4'], lessons)
    }

    void testAdditionOfLesson() {
        prog.refresh()
        prog.addToLessons(new Lesson(name:'FTP Lesson 2', description: 'Foo', sequence: 150))
        def lessons = prog.lessons.collect { it.name }
        assertEquals(['FTP Lesson 1',
                      'FTP Lesson 2',
                      'FTP Lesson 3',
                      'FTP Lesson 4'], lessons)
    }

    void testSortLessons() {
        // create params-like map of 
        // 'lessonId_3' : sequence
        // 'lessonId_4' : sequence
        // except, multiply by -1 to reverse the order.
        def params = prog.lessons.inject([:]) { map, lesson -> 
            map["lessonId_${lesson.id}"] = lesson.sequence * -1 ; map
        }

        programService.sortLessons(prog, params)
        prog.refresh()
        // make sure that order of lessons is reversed from orig. order.
        def lessons = prog.lessons.collect { it.name }
        assertEquals(['FTP Lesson 4',
                      'FTP Lesson 3',
                      'FTP Lesson 1'], lessons)
    }

    void testNextAvailSequence() {
        def nas = programService.nextAvailSequence(prog)
        assertEquals 400, nas
    }

    void testNextAvailSequenceWithNewProg() {
        def p = new Program(name:"Lahey", description:"desc")
        def nas = programService.nextAvailSequence(p)
        assertEquals 100, nas
    }

    void testSortedLessonIds() {
        def params = [
                        'lesson' : [ 'some', 'worhtless', 'param'],
                        'lessonId_32' : '-1',
                        'lessonId_1' : '100',
                        'lessonId_69' : '702',
                        'lessonId_3' : '8000',
                        'lessonId_42' : '1000',
                        'lessonId_123' : '900',
                        'lessonId_17' : '10000'
        ]
        def sortedLessonIds = programService.sortedLessonIdList(params)
        assertEquals([32, 1, 69, 123, 42, 3, 17], sortedLessonIds)
    }
}
