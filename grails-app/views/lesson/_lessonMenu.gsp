<ul>
    <li>
        <h3>
            <g:link class="book_open" controller="lesson" action="list">Lessons</g:link>
        </h3>
        <ul>
            <li>
                <g:link class="book_next" controller="lesson" action="create">New</g:link>
            </li>
            <g:if test="${lessonInstance}">
                <li>
                    <g:link class="book_edit" controller="lesson" action="edit" id="${lessonInstance.id}">Edit</g:link>
                </li>
            </g:if>
        </ul>
    </li>
</ul>
