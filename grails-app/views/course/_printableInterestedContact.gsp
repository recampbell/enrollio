<tr>
    <td colspan="2">
        ${callListContacts[contactInstance.id]?.callListPosition})
        ${contactInstance}
       <enrollio:signupDateForCourse contactInstance="${contactInstance}" courseInstance="${courseInstance}" />
        <g:if test="${callListContacts[contactInstance.id]?.user}">
            <b>(${callListContacts[contactInstance.id]?.user})</b>
        </g:if>
       <span style="float:right"> ${contactInstance.phoneNumbers?.join(", ")}</span>
   </td>
</tr>
<tr>
    <td>
        <ul class="prop">
            <li>
                <g:if test="${contactInstance.address1}">${contactInstance.address1}, &#160;&#160;</g:if>
                <g:if test="${contactInstance.address2}">${contactInstance.address2}</g:if>
                <g:if test="${contactInstance.city}">${contactInstance.city},&#160;&#160;</g:if>
                <g:if test="${contactInstance.state}">${contactInstance.state}&#160;</g:if>
                <g:if test="${contactInstance.zipCode}">${contactInstance.zipCode}&#160;&#160;</g:if>
            </li>
            <g:if test="${contactInstance.emailAddress}"> <li> ${contactInstance.emailAddress}</li> </g:if>
        <g:if test="${contactInstance.comments}">
        <li>
        <comments:each bean="${contactInstance}">
            <b>*</b> ${comment.body}
        </comments:each></li>
        </g:if>
        </ul>
    </td>
    <td>
        <table>
            <g:each var="stud" in="${contactInstance.students}">
            <tr>
            <td>
                    ${stud.starred ? '* ' : ''}
                    ${stud}
                </td>
                <td><enrollio:enrollmentAbbreviations studentInstance="${stud}" /></td>
            </tr>
            </g:each>
        </table>
    </td>
</tr>
