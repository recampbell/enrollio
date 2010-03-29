<tr>
                <td>${contactInstance}</td>
                <td>${contactInstance.address1} 
                    <br />
                    <g:if test="${contactInstance.address2}">
                        ${contactInstance.address2} 
                        <br />
                        </g:if>
                    <g:if test="${contactInstance.city}">
                        ${contactInstance.city},&#160;&#160;</g:if>
                    <g:if test="${contactInstance.state}">
                    ${contactInstance.state} 
                    <br /></g:if>
                    <g:if test="${contactInstance.zipCode}">
                    ${contactInstance.zipCode}&#160;&#160;</g:if>
                    <br /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${contactInstance.emailAddress}</td>
            </tr>
            <g:each var="phone" in="${contactInstance.phoneNumbers}">
            <tr>
                <td>${phone.label}</td>
                <td>${phone.phoneNumber}</td>
            </tr>
            </g:each>
</tr>
