<ul>
    <li>
        <h3>
            <g:link class="user" controller="contact">Contacts</g:link>
        </h3>
        <ul>
            <li>
                <g:link class="useradd" controller="contact" action="create">New</g:link>
            </li>
            <g:if test="${contactInstance}">
                <li>
                    <g:link class="useredit" controller="contact" action="edit" id="${contactInstance.id}">Edit</g:link>
                </li>
            </g:if>
        </ul>
    </li>
    <g:if test="${contactInstance}">
    <li>
        <h3>
            <a href="#" class="group">Students</a>
        </h3>
        <ul class="navlist">
            <li>
            <a href="#" id="createStudentLink" class="groupadd">Add Student</a>
            </li>
            <li>
                <g:link class="groupedit" controller="contact" action="editStudents">Edit Students</g:link>
            </li>
        </ul>
    </li>
</g:if>
</ul>
