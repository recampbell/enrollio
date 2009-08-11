%{--
    _phoneNumbers.gsp
    Form for getting contact phone numbers
    Requires contactInstance
--}%
    <tr>
        <td>Phone Numbers:</td>
    </tr>
    <g:each var="phoneNumber" in="${contactInstance.phoneNumbers}" status="i">
            <tr>
                    <td>
                            <g:hiddenField name='phoneNumbers[${i}].id' value='${phoneNumber.id}'/>
                            <g:textField name='phoneNumbers[${i}].phoneNumber' value='${phoneNumber.phoneNumber}'/>
                    </td>
            </tr>
    </g:each>

