
    <tr>
    <th>First Name</th>
    <th>Middle Name</th>
    <th>Last Name</th>
    <th>Grade</th>
    <th>Gender</th>
    <th>Birth Date</th>
    
    </tr>

<g:each in="${contactInstance.students}" var="student" status="idx">
        <tr id="student_${idx}" class="prop">
            <input type="hidden" name="students[${idx}].id" value="${student?.id}"/>
            <td valign="top" class="value ${hasErrors(bean:student,field:'firstName','errors')}">
                <input type="text" id="firstName" name="students[${idx}].firstName" value="${fieldValue(bean:student,field:'firstName')}"/>
            </td>
            <td valign="top" class="value ${hasErrors(bean:student,field:'middleName','errors')}">
                <input type="text" id="middleName" name="students[${idx}].middleName" value="${fieldValue(bean:student,field:'middleName')}"/>
            </td>
            <td valign="top" class="value ${hasErrors(bean:student,field:'lastName','errors')}">
                <input type="text" id="lastName" name="students[${idx}].lastName" value="${fieldValue(bean:student,field:'lastName')}"/>
            </td>
    
            <td valign="top" class="value ${hasErrors(bean:student,field:'grade','errors')}">
                <input type="text" id="grade" name="students[${idx}].grade" value="${fieldValue(bean:student,field:'grade')}"/>
            </td>
            <td valign="top" class="value ${hasErrors(bean:student,field:'gender','errors')}">
                <input type="text" id="gender" name="students[${idx}].gender" value="${fieldValue(bean:student,field:'gender')}"/>
            </td>
            <td valign="top" class="value ${hasErrors(bean:student,field:'birthDate','errors')}">
                <g:datePicker name="students[${idx}].birthDate" value="${student?.birthDate}" precision="day" ></g:datePicker>
            </td>
        </tr> 
    

</g:each>

<g:set var="nextstudentidx" value="${contactInstance.students ? contactInstance.students.size() : 0}"/>

<!--Don't show the "New student" link if there are errors. User must fix existing errors before adding more students. -->
  <tr name="foo" id="foo">
    <g:render template='newInlineStudentButton' 
              model="['studentidx':nextstudentidx]"/>
  </tr>
