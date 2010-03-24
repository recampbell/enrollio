<ul>
    <li>
        <h3>
            <g:link class="user" controller="student">Students</g:link>
        </h3>
        <ul>
            <li>
                <g:link class="useradd" controller="contact" action="create">New</g:link>
            </li>
            <g:if test="${contactInstance}">
                <li>
                    <g:link class="useredit" controller="contact" action="edit" id="${contactInstance.id}">Edit Contact</g:link>
                </li>
            </g:if>
        </ul>
    </li>
</ul>
