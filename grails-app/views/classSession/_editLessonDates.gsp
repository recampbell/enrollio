<table>
    <g:each var="lessonDate" in="${lessonDates}">
    <tr class="prop">
        <td valign="top">
            <input type="text" class="lessonDate" name="lessonDates[]" value="${lessonDate?.lessonDate}" />
        </td>
        <td valign="top" class="name">
            <label for="lessonDate">${lessonDate.lesson.name}</label>
        </td>
    </tr>
</g:each>
</table>
