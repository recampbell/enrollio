package org.bworks.bworksdb

class ProgramService {

    boolean transactional = true

    // Go through Programs's Classes, and create dummy
    // LessonDates, starting with startDate
    def nextAvailableClasses(Program p, Date startDate) {
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
}
