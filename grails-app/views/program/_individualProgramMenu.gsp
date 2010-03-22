<ul>
    <li>
        <h3>
            <g:link class="module" controller="course" 
                   action="show"
                   id="${courseInstance.id}">${courseInstance}
            </g:link>
        </h3>
        <ul>
            <li>
                <g:link class="module_edit" name="editCourseLink" controller="course"
                action="edit" id="${courseInstance.id}">Edit</g:link>
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
                    <input type="hidden" name="id" value="${courseInstance.id}" />
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
            <g:link class="book_open" controller="course" action="lessons"
            id="${courseInstance.id}">Lessons</g:link>
        </h3>
        <ul>
            <g:if test="${courseInstance.lessons}">
                <li>
                    <g:link class="number_list" 
                            name="sortLessonsLink" 
                            action="sortLessons" 
                            controller="course" 
                            params="[ 'id' : courseInstance.id ]">Sort Lessons</g:link>
                </li>
            </g:if>
            <li>
                <g:link class="book_next" name="newLessonLink" action="create" controller="lesson" 
                                        params="[ 'course.id' : courseInstance.id ]">New Lesson</g:link>
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
