<h3>Sequence</h3>
<table>
    <thead>
    </thead>
    <tbody class="draggable">
        <g:each var="l" in="${programInstance.lessons}">
        <tr>
            <td>
                <input type="text" name="lessonId_${l.id}" 
                value="${l.sequence}" /></td>
            <td name="lessonName_${l.id}">${l}</td>
        </tr>
        </g:each>
        <g:if test="${lessonInstance}">
        <tr>
            <td><input type="text" 
                name="lessonId_NEW_KID_ON_THE_BLOCK" 
                value="${lessonInstance.sequence}" /></td>
            <td id="newLessonNameInSequence">Foo Lesson</td>
        </tr>
        </g:if>
    </tbody>
</table>
