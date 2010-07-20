<tr>
    <td width="45%">
       ${callListContacts[contactInstance.id]?.callListPosition}) ${contactInstance}
       <span style="float:right"> ${contactInstance.phoneNumbers?.join(", ")}</span>
        <ul class="prop">
            <li>
                <g:if test="${contactInstance.address1}">${contactInstance.address1}, &#160;&#160;</g:if>
                <g:if test="${contactInstance.address2}">${contactInstance.address2}</g:if>
                <g:if test="${contactInstance.city}">${contactInstance.city},&#160;&#160;</g:if>
                <g:if test="${contactInstance.state}">${contactInstance.state}&#160;</g:if>
                <g:if test="${contactInstance.zipCode}">${contactInstance.zipCode}&#160;&#160;</g:if>
            </li>
            <g:if test="${contactInstance.emailAddress}"> <li> ${contactInstance.emailAddress}</li> </g:if>
        </ul>
    </td>
    <td width="35%">
        <ul class="prop">
            <g:each var="stud" in="${contactInstance.students}">
                <li>
                    ${stud.starred ? '* ' : ''}
                    ${stud}
                    <enrollio:enrollmentAbbreviations studentInstance="${stud}" />
                </li>
            </g:each>
        </ul>
    </td>
</tr>
<g:if test="${callListContacts[contactInstance.id]?.user}">
<tr>
    <td>
        ${callListContacts[contactInstance.id]?.user}
    </td>
</tr>
</g:if>
