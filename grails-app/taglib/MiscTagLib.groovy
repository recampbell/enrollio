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
            // TODO Find out why I can't put AndActive at the end of
            // this dynamic query
            def c = Interest.createCriteria()
            def i = c.list {
                eq("student", student)
                eq("program", prog)
                eq("active", true)
            }

            if (i) {
                out << g.checkBox(value:prog.id, checked:true, name:checkBoxName)
            }
            else {
                out << g.checkBox(value:prog.id, checked:false, name:checkBoxName)
            }
            out << "<label for='${checkBoxName}'>${prog.name}</label>"
            out << '<br />'
        }
        return out
    }
}
