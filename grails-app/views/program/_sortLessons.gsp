<div class="box">
    <h3>${programInstance} : Sort Lessons</h3>
    <ul id="sortable">
        <g:each var="l" in="${programInstance.lessons}">
        <li class="ui-state-default" name="lessonName_${l.id}">
                <span class="ui-icon ui-icon-arrowthick-2-n-s"></span>${l} 
                <input class="sequenceReadOnly" type="hidden" readonly="true"
                        name="lessonId_${l.id}" value="${l.sequence}" />
        </g:each>
        <!-- Create another list item <li> for new Lesson, if we're creating a 
        new Lesson -->
        <g:if test="${lessonInstance}">
            <li id="newLessonNameInSequence" class="ui-state-default">
                <span class="ui-icon ui-icon-arrowthick-2-n-s"></span>Foo Lesson</li>
                    <input class="sequenceReadOnly" readonly="true" type="hidden"
                            name="lessonId_NEW_KID_ON_THE_BLOCK"
                            value="${lessonInstance.sequence}" />
            </li>
        </g:if>
    </ul>
</div>
