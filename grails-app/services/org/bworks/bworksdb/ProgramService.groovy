package org.bworks.bworksdb
import grails.test.*

class ProgramService extends GrailsUnitTestCase {

    boolean transactional = true

    // Go through Programs's Lessons, and create dummy
    // LessonDates, starting with startDate
    def nextAvailableLessonDates(Program p, Date startDate) {
        def d = startDate
        def proposedClasses = []
        p.lessons.each {
            proposedClasses << new LessonDate(lesson:it, lessonDate:d)
            d = d + 7
        }

        return proposedClasses
    }

    def getCallList(id) {
        def prog = Program.get(id)
        if (!prog) return null;
        def interests = prog.interests.findAll { it.active == true }
        println interests
        def students = interests.collect { it.student }
        def contacts = students.collect { it.contact }
    }

    def activeInterests(Program p) {
        return Interest.findAllByProgramAndActive(p, true)
    }


    // Utility method to make sure we have lesson sequences
    // in a standard order (separated by 100, so that we can easily
    // add new Lessons between other lessons w/o trampling existing sequences)
    def sequenceLessons(Program p) {
        def lessons = p.lessons.collect { it }
        def newSequence = 100
        lessons.each {
            if (it.sequence != newSequence) {
                it.sequence = newSequence
                it.save()
            }
            newSequence += 100
        }
    }

    // Inserts newLesson prior to existingLesson
    // if existingLesson is null, or if program has no lessons, then
    // newLesson is inserted at the end
    def insertLesson(Program p, Lesson newLesson, Lesson existingLesson) {
        sequenceLessons(p)
    
        if (existingLesson) {
            newLesson.sequence = existingLesson.sequence - 1
        }
        else {
            def last = p.lessons.last()
            // If program has a lesson, get the last lesson's sequence and add 100.
            // Otherwise, we have an empty list of lessons, so just use 100
            newLesson.sequence = last ? last.sequence + 100 : 100
        }

        p.addToLessons(newLesson).save(flush:true)

        sequenceLessons(p)
    }

    def sortLessons(Program p, params) {
        sequenceLessons(p)
        def seq = 100
        def lessonIdList = sortedLessonIdList(params)
        lessonIdList.each {
            println "Assigning ${it} ${seq}"
            def lesson = Lesson.get(it)
            if ( lesson.sequence != seq ) {
                lesson.sequence = seq
                lesson.save(flush:true)
            }
            seq = seq + 100
            
        }
        // clean up any lessons that might have been saved
        // since the lessonIdList was composed
        sequenceLessons(p)
    }

    // returns a list of lesson IDs, sorted by their
    // suggested sequence
    // Takes a map consisting of data like this:
    // [ 
    //     'lessonId_42' : 100,
    //     'lessonId_64' : 200,
    //     'lessonId_1'  : 400,
    //     'lessonId_76' : 300,
    //     'lessonId_2'  : '1600' 
    // ]
    //  And returns this : [ 42, 64, 76, 1, 2 ]
    def sortedLessonIdList(params) {
        def lessonMap = params.findAll { 
            it.key =~ /lessonId_/
        }

        def sortedLessonIds = lessonMap.keySet().sort {
            lessonMap[it].toInteger()
        } 

        // Rip the xx from 'lessonId_xx'
        println "sorted lessons are: " + sortedLessonIds
        sortedLessonIds = sortedLessonIds.collect {
            it.split('_')[1].toInteger()
        }

        return sortedLessonIds
        
    }

    def nextAvailSequence(Program p) {
        def l = p.lessons.last()
        return l ? l.sequence + 100 : 100
    }

}
