<ul>
    <li>
        <h3>
            <g:link class="group" controller="student" action="list">Students</g:link>
        </h3>
        <ul>
            <li>
                <g:link class="search" name="searchStudentsLink" 
                action="list" 
                controller="student" >Search</g:link>
            </li>
            <li>
            
                    <g:link name="newStudentLink" class="useradd" 
                    controller="contact" 
                    action="create">New Student</g:link>
            </li>
            <g:if test="${studentInstance}">
                <li>
                    <g:link name="editStudentLink" class="useredit" controller="student" action="edit" id="${studentInstance.id}">Edit</g:link>
                </li>
            </g:if>
        </ul>
    </li>
</ul>
