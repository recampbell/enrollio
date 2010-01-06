<ul>
    <li>
        <h3>
            <g:link class="group" controller="student" action="list">Students</g:link>
        </h3>
        <ul>
            <g:if test="${studentInstance}">
                <li>
                    <g:link class="useredit" controller="student" action="edit" id="${studentInstance.id}">Edit</g:link>
                </li>
            </g:if>
        </ul>
    </li>
</ul>
