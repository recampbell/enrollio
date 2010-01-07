<tr>
    <td>
        <g:link action="show" id="${student.id}" controller="student">${student}</g:link>
    </td>
    <td>${student.gender ?: ""}</td>
    <td>${student.grade ?: ""}</td>
    <td><g:formatDate format="MMMM d, yyyy"
        date="${student.birthDate}" /></td>
    <td>
    %{-- TODO create .activeInterests property on Student,
         so we don't need the g:if interest.active --}%
    <g:each var="interest" in="${student.interests}">
        <g:if test="${interest.active}">
            <g:link controller="program" action="show" id="${interest.program.id}">
                ${interest.program.name}&nbsp;
            </g:link>
        </g:if>
    </g:each>
    </td>
</tr>
