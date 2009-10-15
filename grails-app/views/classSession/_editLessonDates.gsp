<table>
    <g:each var="lessonDate" in="${lessonDates}">
    <tr class="prop">
        <td valign="top">
            <g:datePicker name="lessonDates[].lessonDate"
                         value="${lessonDate?.lessonDate}"
                        precision="day"></g:datePicker>
        </td>
        <td valign="top" class="name">
            <label for="lessonDate">${lessonDate.lesson.name}</label>
        </td>
    </tr>
</g:each>
</table>
