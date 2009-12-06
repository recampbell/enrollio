package org.bworks.bworksdb

class StudentService {

    boolean transactional = true

    protected def buildSelectedIds(def interests) {
        def selected = []
        
        if (interests) {
            // turn 1 or N into a single list and iterate
            def tmpInterests = []
            tmpInterests << interests
            tmpInterests.flatten().each { it ->
                selected << it.toString()
            }
        }
        
        selected
    }
        
    // Loop through interests, either:
    // create a new one
    // or set active to 'selected'
    // Interests is an array of goofy Grails checkboxes
    def saveInterests(student, interests) {

        def selectedInterests = buildSelectedIds(interests)
        
        Program.findAll().each { program ->
            def interest = Interest.findWhere(student:student, program:program)
            def isSelected = selectedInterests.contains(program.id.toString())

            if (! interest) {
                if (isSelected) {
                    def note = new Note().save()
                    def newInterest = new Interest(active:true, student:student, program:program, note:note).save()        
                    program.addToInterests(newInterest)
                    student.addToInterests(newInterest)
                }
            }
            else {    
                interest.active = isSelected            
            }
        }            
    }
}
