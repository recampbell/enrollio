<table>
    <g:each var="lessonDate" in="${lessonDates}">
    <tr><td>${lessonDate.lessonDate}</td><td>${lessonDate.lesson.name}</td></tr>
    </g:each>
</table>
