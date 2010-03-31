<tr>
    <td width="20%">
        <ul class="prop">
        <g:each var="student" in="${contactInstance.students}">
        
        <li>
            <g:render template="/utility/starredThingy" model="[thingy:student]" />${student}
        </li>
        </g:each>

        </ul>
    
    
    </td>
    <td>${contactInstance}</td>
    <td>${contactInstance.address1} 
        <g:if test="${contactInstance.address2}">
        ${contactInstance.address2} 
        <br />
        </g:if>
        <g:if test="${contactInstance.city}">
        ${contactInstance.city},&#160;&#160;</g:if>
        <g:if test="${contactInstance.state}">
        ${contactInstance.state} 
        </g:if>
        <g:if test="${contactInstance.zipCode}">
        ${contactInstance.zipCode}&#160;&#160;
        </g:if>
        <br />
    </td>
    <td>

        <g:if test="${contactInstance.emailAddress}">
        <br />
        ${contactInstance.emailAddress}
        </g:if>
        <g:if test="${contactInstance.phoneNumbers}">
        <ul class="prop">
            <g:each var="phone" in="${contactInstance.phoneNumbers}">
            <li>${phone.phoneNumber}</li>
            </g:each>
        </ul>
        </g:if>
    </td>
</tr>
