<input type="hidden" name="students[${studentidx}].id" value="${student?.id}"/>
<td valign="top" class="value ${hasErrors(bean:student,field:'firstName','errors')}">
    <input type="text" id="firstName" name="students[${studentidx}].firstName" value="${fieldValue(bean:student,field:'firstName')}"/>
</td>
<td valign="top" class="value ${hasErrors(bean:student,field:'middleName','errors')}">
    <input type="text" id="middleName" name="students[${studentidx}].middleName" value="${fieldValue(bean:student,field:'middleName')}"/>
</td>
<td valign="top" class="value ${hasErrors(bean:student,field:'lastName','errors')}">
    <input type="text" id="lastName" name="students[${studentidx}].lastName" value="${fieldValue(bean:student,field:'lastName')}"/>
</td>

<td valign="top" class="value ${hasErrors(bean:student,field:'grade','errors')}">
    <input type="text" id="grade" name="students[${studentidx}].grade" value="${fieldValue(bean:student,field:'grade')}"/>
</td>
<td valign="top" class="value ${hasErrors(bean:student,field:'gender','errors')}">
    <input type="text" id="gender" name="students[${studentidx}].gender" value="${fieldValue(bean:student,field:'gender')}"/>
</td>
<td valign="top" class="value ${hasErrors(bean:student,field:'birthDate','errors')}">
    <g:datePicker name="students[${studentidx}].birthDate" value="${student?.birthDate}" precision="day" ></g:datePicker>
</td>
<td>
<g:remoteLink action="cancelInlineStudent" update="newStudentDiv"
        params="['studentstudentidx':studentstudentidx]" onLoading="showSpinner();" on404="alert('Not found');">
  Cancel
</g:remoteLink>
</td>
