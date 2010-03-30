import org.bworks.bworksdb.*

class EnrollioTagLib {
    static namespace = "enrollio"

    // Standard Enrollio date formatting
    def formatDate = { attrs ->
        def d = attrs['date']
        try {
            def output = d ? d.format('MMMM d, yyyy') : ''
            out << output
        } catch (Exception e) {
            out << ''
        }
    }

    // split a CamelCase fieldName like 'birthDate'
    // and capitalize, and put a colon on the end
    def formatFieldName(fieldName) {
        def formattedFieldName
        if (fieldName) {
            def words = fieldName.split("(?=[A-Z])")
            def upperCaseWords = words.findAll({it.length()}).collect {
                it[0].toUpperCase() + it[1 .. -1].toLowerCase()
            }
            formattedFieldName = upperCaseWords.join(' ') + ':'
        }

        return formattedFieldName
    }


    def textField = { attrs ->
        def m = attrs['model']
        def fieldName = attrs['fieldName']
        def formattedFieldName = formatFieldName(fieldName)

        def output = """
        <tr class="prop">
            <td valign="top" class="name">
            ${formattedFieldName}
            </td>
            <td valign="top" class="value">
                ${fieldValue(bean: m, field:fieldName)?.replace("\n", "<br />")}
            </td>
        </tr>
        """

        out << output

    }

    def dateField = { attrs ->
        def m = attrs['model']
        def fieldName = attrs['fieldName']
        def formattedFieldName = formatFieldName(fieldName)

        def output = """
        <tr class="prop">
            <td valign="top" class="name">
            ${formattedFieldName}
            </td>
            <td valign="top" class="value">
                ${enrollio.formatDate(date:m.properties[fieldName])}
            </td>
        </tr>
        """

        out << output

    }

    // expects a single student's attendance data
    // as provided by ClassSessionService.attendancesForSession
    def studentAttendanceSummary = { attrs ->

        if (attrs.summary.attendanceCount == attrs.summary.totalLessons) {
            out << '100 %&nbsp;'
            out << '<img src="'
            out << resource(dir:'images/icons', file:'award_star_gold_3.png')
            out << '" />'
        }

        def missedClassesLinks = attrs.summary.missed.collect { lessonDate ->
            g.link(controller:'lessonDate', action:'show', id:lessonDate.id, lessonDate.lesson.name)
        }

        if (missedClassesLinks) {
            out << "&nbsp;Missed: "
            out << missedClassesLinks.join(', ')
        }

    }
}

