<ul>
    <li>
        <h3>
            <g:link class="module" controller="program" 
                   action="show"
                   id="${programInstance.id}">${programInstance}
            </g:link>
        </h3>
        <ul>
            <li>
                <g:link class="module_edit" name="editProgramLink" controller="program"
                action="edit" id="${programInstance.id}">Edit</g:link>
            </li>
            <li>
                <script type="text/javascript">function submit_callList(link) {
                link.parentNode._format.value = link.title; link.parentNode.submit();
                return false; }</script>
                <g:form name="callList" action="callList">
                    <input type="hidden" name="_format" value="PDF" />
                    <!-- Name shown on top of PDF report -->
                    <input type="hidden" name="_name" value="Call List" />
                    <input type="hidden" name="_file" value="callList" />
                    <input type="hidden" name="id" value="${programInstance.id}" />
                    <!-- TODO The &nbsp; is a kludge find CSS way to justify image
                            and text so it looks o.k. -->
                    <a href="#" name="callListLink" class="telephone" title="PDF"
                    onClick="return submit_callList(this)">&#160;Call List</a>
                </g:form>
            </li>
        </ul>
    </li>
    <li>
        <h3>
            <g:link class="book_open" controller="program" action="lessons"
            id="${programInstance.id}">Lessons</g:link>
        </h3>
        <ul>
            <li>
                <g:link class="book_next" controller="lesson" action="create">New
                Lesson</g:link>
            </li>
            <g:if test="${lessonInstance}">
                <li>
                    <g:link class="book_edit" controller="lesson" action="edit"
                    id="${lessonInstance.id}">Edit Lesson</g:link>
                </li>
            </g:if>
        </ul>
    </li>
</ul>
