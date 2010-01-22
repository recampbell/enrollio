<h3>${programInstance} : Sort Lessons</h3>
<table>
    <thead>
    </thead>
    <tbody class="draggable">
        <g:each var="l" in="${programInstance.lessons}">
            <tr>
                <td name="lessonName_${l.id}">${l}</td>
                <td>
                    <input class="sequenceReadOnly" type="text" readonly="true" name="lessonId_${l.id}" 
                    value="${l.sequence}" />
                </td>
            </tr>
        </g:each>
        <g:if test="${lessonInstance}">
            <tr>
                <td id="newLessonNameInSequence">Foo Lesson</td>
                <td>
                    <input class="sequenceReadOnly" readonly="true" type="text" 
                        name="lessonId_NEW_KID_ON_THE_BLOCK" 
                        value="${lessonInstance.sequence}" />
                </td>
            </tr>
        </g:if>
    </tbody>
</table>
