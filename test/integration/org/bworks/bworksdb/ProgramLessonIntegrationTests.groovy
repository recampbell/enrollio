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
        
        prog.addToLessons(new Lesson(name:'FTP Lesson 4', description: 'Foo', sequence: 4))
        prog.addToLessons(new Lesson(name:'FTP Lesson 3', description: 'Foo', sequence: 3))
        prog.addToLessons(new Lesson(name:'FTP Lesson 1', description: 'Foo', sequence: 1))
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
        prog.addToLessons(new Lesson(name:'FTP Lesson 2', description: 'Foo', sequence: 2))
        def lessons = prog.lessons.collect { it.name }
        assertEquals(['FTP Lesson 1',
                      'FTP Lesson 2',
                      'FTP Lesson 3',
                      'FTP Lesson 4'], lessons)
    }

    void testInsertLessonBeforeAnotherLesson() {
        def newLesson = new Lesson(name:'FTP Lesson 3.5', description:'Foo')
        programService.insertLesson(prog, newLesson, prog.lessons.last())
        prog.refresh()
        def lessons = prog.lessons.collect { it.name }
        assertEquals(['FTP Lesson 1',
                      'FTP Lesson 3',
                      'FTP Lesson 3.5',
                      'FTP Lesson 4'], lessons)
    }

    void testInsertLessonBeforeFirstLesson() {
        def newLesson = new Lesson(name:'FTP Lesson 3.5 but really 1st lesson', description:'Foo')
        def firstLesson = Lesson.findByName('FTP Lesson 1')
        programService.insertLesson(prog, newLesson, firstLesson)
        prog.refresh()
        def lessons = prog.lessons.collect { it.name }
        assertEquals(['FTP Lesson 3.5 but really 1st lesson',
                      'FTP Lesson 1',
                      'FTP Lesson 3',
                      'FTP Lesson 4'], lessons)
    }

    void testInsertLessonAtEnd() {
        def newLesson = new Lesson(name:'A Graduation Ceremony', description:'Foo')
        def firstLesson = Lesson.findByName('FTP Lesson 1')
        programService.insertLesson(prog, newLesson, null)
        prog.refresh()
        def lessons = prog.lessons.collect { it.name }
        assertEquals(['FTP Lesson 1',
                      'FTP Lesson 3',
                      'FTP Lesson 4',
                      'A Graduation Ceremony',], lessons)
    }
}
