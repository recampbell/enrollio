package org.bworks.bworksdb

class ProgramService {

    boolean transactional = true

    def getCallList(id) {
        def prog = Program.get(id)
        if (!prog) return null;
        def interests = prog.interests.findAll { it.active == true }
        println interests
        def students = interests.collect { it.student }
        def contacts = students.collect { it.contact }

        return contacts
    }
}
