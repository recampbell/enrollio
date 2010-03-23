<tr>
    <td>
        <g:link action="show" id="${student.id}" controller="student">${student}</g:link>
    </td>
    <td>
    %{-- TODO create .activeInterests property on Student,
         so we don't need the g:if interest.active --}%
    <g:each var="interest" in="${student.activeInterests()}">
            <g:link controller="course" action="show" id="${interest.course.id}">
                ${interest.course.name},&nbsp;
            </g:link>
    </g:each>
    </td>
    <td>
        <g:link 
        title="Edit ${student}"
        name="editStudent${student.id}" 
        action="edit" controller="student" 
        id="${student.id}"><img src="../images/icons/user_edit.png" alt="Edit Student" />
        </g:link>
    </td>
</tr>
