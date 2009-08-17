package org.bworks.bworksdb

class ContactService {

    boolean transactional = true

    def getCallListForProgram(id) {
        def prog = Program.get(id)
        if (!prog) return null;
        def contacts = prog.activeInterests.collect { it.student.contact }

        return contacts
    }
}
