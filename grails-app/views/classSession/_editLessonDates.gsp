<table>
    <g:each var="lessonDate" in="${lessonDates}" status="i">
    <tr class="prop">
        <td valign="top">
            <input type="text" class="lessonDate" 
            name="lessonDate.lessonDates[${i}].lessonDate" 
            id="lessonDate.lessonDates[${i}].lessonDate" 
            value="${lessonDate?.lessonDate}" />
            <input type="hidden" 
                   name="lessonDate.lessonDates[${i}].lessonId"
                   id="lessonDate.lessonDates[${i}].lessonId"
                   value="${lessonDate.lesson.id}"</input>
        </td>
        <td valign="top" class="name">
            <label for="lessonDate">${lessonDate.lesson.name}</label>
        </td>
    </tr>
</g:each>
</table>
