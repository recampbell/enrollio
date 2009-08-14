import org.bworks.bworksdb.*

class MiscTagLib {

    // Create a checkbox for all programs for this student
    // Checkboxes are named using an index 'idx' which corresponds to the student's
    // index, so that the appropriate programs/interests can be assigned to the appropriate
    // student.
    // If student already has an active Interest in a program, the checkbox is checked.
    def interestCheckBoxes = { attrs ->
        def student = attrs['student']
        def idx = attrs['idx']
        def programs = Program.findAll()
        programs.each { prog ->
            // Note: Need to search for active == true, also
            def checkBoxName = "studentInterests[${idx}]"
            out << "<label for='${checkBoxName}'>${prog.name}</label>"
            println "Searching for ${student} interested in ${prog}"
            if (Interest.findByStudentAndProgram(student, prog)) {
                println "Jar Jar! " * 1000
                out << g.checkBox(value:prog.id, checked:true, name:checkBoxName)
                out << '<br />'
            }
            else {
                out << g.checkBox(value:prog.id, checked:false, name:checkBoxName)
                out << '<br />'
            }
        }
        return out
    }
}
