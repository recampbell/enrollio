package org.bworks.bworksdb

class StudentService {

    boolean transactional = true

    // Loop through interests, and if an interest is present,
    // make sure the student has that interest active.
    // Otherwise, make sure that any interests that are *not* present are rendered
    // inactive if they exist for that student.  Read on.  You'll get it.
    // Interests is an array of goofy Grails checkboxes
    def saveInterests(student, interests) {
        println "--" * 200
        if (interests == null ) return;
        println "Milf" * 10
        def allPrograms = Program.findAll()
        allPrograms.each { prog ->
        println "Checking program: " + prog.id
        // println interests.className
            // See if there's a checkbox in the array that's selected 
            if (interests && interests instanceof java.lang.String) {
                println "Trying to match " + interests.toString() + " with " + prog.id.toString()
                if (interests.toString() == prog.id.toString()) {
                    println "Matched" * 10
                    if (!Interest.findWhere(student:student, program:prog, active:true)) {
                    println "adding" * 10
                        student.addToInterests(new Interest(program:prog, active:true))
                    } else {
                        println "Exists already, mofo!"
                    }
                }
                else {
                    def i = Interest.findWhere(student:student, program:prog, active:true)
                    if (i) {
                        println "Blasting from foomanchu " + i
                        i.active = false
                        i.save()
                    }
                }
            }
            else if (interests && interests instanceof java.lang.String[]) {
                def found = false
                interests.each {
                    println "it : " + it
                    println "prog.id : " + prog.id
                    if (it.toString() == prog.id.toString()) {
                        found = true
                        println "Yes sld"
                        if (!Interest.findWhere(student:student, program:prog, active:true)) {
                            println "mached array" * 10
                            student.addToInterests(new Interest(program:prog, active:true))
                        }
                    }
                }
                if (!found) {
                    def i = Interest.findWhere(student:student, program:prog, active:true)
                    if (i) {
                        println "Blasting from interests[]" + i
                        i.active = false
                        i.save()
                    }
                }

            }
            else {
                // Momma said knock you out!
                def i = Interest.findWhere(student:student, program:prog, active:true)
                if (i) {
                    println "Blasting from wherever" + i
                    i.active = false
                    i.save()
                }
            }
        }
    }
}
