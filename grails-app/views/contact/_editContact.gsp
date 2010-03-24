<div class="dialog">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${contactInstance}">
    <div class="errors">
        <g:renderErrors bean="${contactInstance}" as="list" />
    </div>
    </g:hasErrors>
    <input type="hidden" name="id" value="${contactInstance?.id}" />
    <input type="hidden" name="version" value="${contactInstance?.version}" />
    <table>
        <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <label for="firstName">First Name:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'firstName','errors')}">
                    <input type="text" id="firstName" name="firstName" value="${fieldValue(bean:contactInstance,field:'firstName')}"/>
                </td>
            </tr> 

            <tr class="prop">
                <td valign="top" class="name">
                    <label for="lastName">Last Name:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'lastName','errors')}">
                    <input type="text" id="lastName" name="lastName" value="${fieldValue(bean:contactInstance,field:'lastName')}"/>
                </td>
            </tr>
            <g:render template="phoneNumbers" bean="${contactInstance}"/>                         
            <tr class="prop">
                <td valign="top" class="name">
                    <label for="address1">Address1:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'address1','errors')}">
                    <input type="text" id="address1" name="address1" value="${fieldValue(bean:contactInstance,field:'address1')}"/>
                </td>
            </tr> 

            <tr class="prop">
                <td valign="top" class="name">
                    <label for="address2">Address2:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'address2','errors')}">
                    <input type="text" id="address2" name="address2" value="${fieldValue(bean:contactInstance,field:'address2')}"/>
                </td>
            </tr> 

            <tr class="prop">
                <td valign="top" class="name">
                    <label for="city">City:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'city','errors')}">
                    <input type="text" id="city" name="city" value="${fieldValue(bean:contactInstance,field:'city')}"/>
                </td>
            </tr> 

            <tr class="prop">
                <td valign="top" class="name">
                    <label for="state">State:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'state','errors')}">
                    <input type="text" id="state" name="state" value="${fieldValue(bean:contactInstance,field:'state')}"/>
                </td>
            </tr> 

            <tr class="prop">
                <td valign="top" class="name">
                    <label for="zipCode">Zip Code:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'zipCode','errors')}">
                    <input type="text" id="zipCode" name="zipCode" value="${fieldValue(bean:contactInstance,field:'zipCode')}"/>
                </td>
            </tr> 

            <tr class="prop">
                <td valign="top" class="name">
                    <label for="emailAddress">Email Address:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:contactInstance,field:'emailAddress','errors')}">
                    <input type="text" id="emailAddress" name="emailAddress" value="${fieldValue(bean:contactInstance,field:'emailAddress')}"/>
                </td>
            </tr>

        </tbody>
    </table>
</div>
