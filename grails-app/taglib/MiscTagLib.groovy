import org.bworks.bworksdb.*

class MiscTagLib {

    def configSettingService

    def debug = { map ->
        if (grailsApplication.config.grails.views.debug.mode == true) {
            def msg = map['msg']
            out << "<h2>${msg}</h2><br/>"
        }
    }
    
    // Create a checkbox for all programs for this student
    // Checkboxes are named using an index 'idx' which corresponds to the student's
    // index, so that the appropriate programs/interests can be assigned to the appropriate
    // student.
    // If student already has an active Interest in a program, the checkbox is checked.
    def interestCheckBoxes = { attrs ->
        def student = attrs['student']
        def defaultProgId
        // Whether to check the default program
        def checkDefaultProg = attrs['checkDefaultProg']
        if (checkDefaultProg) {
            defaultProgId = configSettingService.getSetting('defaultInterestProgram')
            if (defaultProgId) defaultProgId = defaultProgId.value;
        }
        def programs = Program.findAll()
        // def defaultProgram = configSettingService.getSetting('defaultInterestProgram')
        programs.each { prog ->
            // Note: Need to search for active == true, also
            def checkBoxName = "interestInProgram_${prog.id}"
            def results = Interest.withCriteria {
                eq("student", student)
                eq("program", prog)
                eq("active", true)
            }

            // If student already has an interest in this program, or if
            // the caller wants us to check the default program automatically
            def hasInterest = (results || 
                              (checkDefaultProg && (prog.id.toString() == defaultProgId)))
            def sCheckbox = """ 
                <label for="interestInProgram_${prog.id}">
                    <input class="checkbox" 
                    id="interestInProgram_${prog.id}" 
                    name="interestInProgram" 
                    type="checkbox" 
                    checked="${hasInterest ? 'true' : 'false'}"
                    value="${prog.id}" />${prog.name}
                </label>
            """

            out << sCheckbox
        }
        return out
    }

    def isCurrentTab = { attrs ->
        if (pageProperty(name:'meta.tabName') == attrs['tabName']) {
            out << 'current'
        }
        else {
            out << 'enr-top-menu-item'
        }
        return out
    }

     def isLoginTab = { attrs ->
         if (pageProperty(name:'meta.tabName') == attrs['tabName']) {
            out << 'logintab current'
         }
         else {
            out << 'logintab'
        }

     }

     // If the mascotIcon has been configured, then return an IMG tag
     // with the icon in it, as well as any attributes that are provided
     def mascotIcon = { attrs ->
         def iconFile = configSettingService.getSetting('mascotIcon')
         
         if (iconFile) {
             def attribs = ""
             attrs.each { key, value ->
                 attribs += "${key}=\"${value}\" "
             }
             out << "<img ${attribs}src=\"${iconFile}\" />"
         }
     }

}
