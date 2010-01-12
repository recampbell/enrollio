package org.bworks.bworksdb

class ProgramService {

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

        p.addToLessons(newLesson).save(flush:true)

        sequenceLessons(p)
    }

}
