<h3> 
    <g:link action="edit" id="${studentInstance.id}" controller="student">
    ${studentInstance}
    </g:link>
</h3>
<table>
    <tbody>
        <tr class="prop">

            <g:if test="${studentInstance.birthDate}">
                <tr class="prop">
                    <td valign="top" class="name">Birth Date:</td>

                    <td valign="top" class="value">
                        <enrollio:formatDate date="${studentInstance.birthDate}" />
                    </td>

                </tr>
            </g:if>

            <g:if test="${studentInstance.grade}">
            <tr class="prop">
                <td valign="top" class="name">Grade:</td>

                <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'grade')}</td>

            </tr>
            </g:if>
            <g:if test="${studentInstance.gender}">
            <tr class="prop">
                <td valign="top" class="name">Gender:</td>

                <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'gender')}</td>

            </tr>
            </g:if>
            <g:if test="${studentInstance.emailAddress}">
            <tr class="prop">
                <td valign="top" class="name">Email Address:</td>

                <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'emailAddress')}</td>

            </tr>
            </g:if>

            <tr class="prop">
                <td valign="top" class="name">Interests:</td>

                <td  valign="top" style="text-align:left;" class="value">
                    <g:activeInterestLinks student="${studentInstance}"/>
                </td>

            </tr>


        </tbody>
    </table>
