<tr>
    <td width="45%">
        <g:link controller="contact" action="edit" id="${contactInstance.id}">${placeInList}) ${contactInstance}</g:link>
        <ul class="prop">
        
            <li>${contactInstance.address1}</li> 
            <g:if test="${contactInstance.address2}"> <li>${contactInstance.address2}</li></g:if>
            <li>
                <g:if test="${contactInstance.city}">${contactInstance.city},&#160;&#160;</g:if>
                <g:if test="${contactInstance.state}">${contactInstance.state}&#160;</g:if>
                <g:if test="${contactInstance.zipCode}">${contactInstance.zipCode}&#160;&#160;</g:if>
            </li>
        </ul>
    </td>
    <td width="20%">
        
        <ul class="prop">

            <g:if test="${contactInstance.emailAddress}"> <li> ${contactInstance.emailAddress}</li> </g:if>
            <g:if test="${contactInstance.phoneNumbers}">
                <g:each var="phone" in="${contactInstance.phoneNumbers}">
                    <li>${phone.phoneNumber}</li>
                </g:each>
            </g:if>
        </ul>
    </td>

    <td width="35%">
        <ul class="prop">
        <g:each var="student" in="${contactInstance.students}">
        
        <li>
        <g:render template="/utility/starredThingy" model="[thingy:student]" />
        
        <g:link controller="student" action="edit" id="${student.id}">${student}</g:link>
        </li>
        </g:each>

        </ul>
    
    
    </td>
</tr>
