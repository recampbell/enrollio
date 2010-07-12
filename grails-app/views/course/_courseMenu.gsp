<ul name="courseMenu">
    <li>
        <h3>
            <g:link class="modules" controller="course" action="list">Courses</g:link>
        </h3>
        <ul>
            <li>
                <g:link url="${[controller:'course', action:'create']}"
                id="newCourseLink" class="module_add">&#160;New</g:link>
            </li>
            <g:if test="${courseInstance}">
                <li>
                    <g:link class="module_edit" controller="course" action="edit"
                    id="${courseInstance.id}">Edit</g:link>
                </li>
            </g:if>
        </ul>
    </li>
</ul>
