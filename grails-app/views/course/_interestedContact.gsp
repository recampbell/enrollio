<tr class="${contactInstance.id.toString() == selectedContactId.toString() ? 'selectedLight' : ''}">
    <td width="45%">
        <g:link controller="contact" 
           action="show" id="${contactInstance.id}">${callListContacts[contactInstance.id]?.callListPosition}) ${contactInstance}</g:link>
        <ul class="prop">
        
            <li>${contactInstance.address1}</li> 
            <g:if test="${contactInstance.address2}"> <li>${contactInstance.address2}</li></g:if>
            <li>
                <g:if test="${contactInstance.city}">${contactInstance.city},&#160;&#160;</g:if>
                <g:if test="${contactInstance.state}">${contactInstance.state}&#160;</g:if>
                <g:if test="${contactInstance.zipCode}">${contactInstance.zipCode}&#160;&#160;</g:if>
                <g:if test="${contactInstance.emailAddress}"> <li> ${contactInstance.emailAddress}</li> </g:if>
            </li>
        </ul>
    </td>
    <td width="20%">
        <ul class="prop">
            <g:if test="${contactInstance.phoneNumbers}">
                <g:each var="phone" in="${contactInstance.phoneNumbers}">
                    <li>${phone.phoneNumber}</li>
                </g:each>
            </g:if>
        </ul>
    </td>

    <td width="35%">
        <ul class="prop">
        <g:each var="stud" in="${contactInstance.students}">
        
        <li>
        <g:render template="/utility/starredThingy" model="[thingy:stud]" />
         <g:if test="${classSessionInstance}">
             <g:checkBox id="enrollStudent${stud.id}"
             name="enrollStudent${stud.id}" 
             class="enrollStudent" 
             classSessionId="${classSessionInstance.id}" 
             studentId="${stud.id}"
             value="${classSessionInstance.enrollments.find { it.student.id == stud.id }}" />
         </g:if>
        <g:link controller="student" action="edit" id="${stud.id}">${stud}</g:link>
        </li>
        </g:each>

        </ul>
    
    
    </td>
    <td>
        <g:select 
            contactId="${contactInstance.id}" 
            courseId="${courseInstance.id}"
            class="reserveContact" 
            from="${users}" 
            optionValue="username" 
            optionKey="id" 
            value="${callListContacts[contactInstance.id]?.user?.id}"
            noSelection="['':'']" />

            <!-- show red push pin if this contact is reserved for currently logged
                 in user -->
        <g:if test="${callListContacts[contactInstance.id]?.user?.id == currentUser.id}">
            <img src="${resource(dir:'images/icons', file:'push_pin_red.png')}" 
                    contactId="${contactInstance.id}" 
                    userId=""
                    class="reservationPushPin" 
                    courseId="${courseInstance.id}"/>
        </g:if>
        <g:else>
        <!-- Post a gray pin, and the current users ID -->
            <img src="${resource(dir:'images/icons', file:'push_pin_gray.png')}" 
                    contactId="${contactInstance.id}" 
                    userId="${currentUser.id}"
                    class="reservationPushPin" 
                    courseId="${courseInstance.id}"/>
        </g:else>
    </td>
</tr>
