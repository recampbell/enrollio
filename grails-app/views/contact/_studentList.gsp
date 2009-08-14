
    <tr>
    <th>First Name</th>
    <th>Middle Name</th>
    <th>Last Name</th>
    <th>Grade</th>
    <th>Gender</th>
    <th>Birth Date</th>
    
    </tr>

<g:each in="${contactInstance.students}" var="student" status="idx">
   <g:render template="studentEdit" model="[student:student, idx:idx]" />
</g:each>

<g:set var="nextstudentidx" value="${contactInstance.students ? contactInstance.students.size() : 0}"/>

<!--Don't show the "New student" link if there are errors. User must fix existing errors before adding more students. -->
  <tr name="foo" id="foo">
    <g:render template='newInlineStudentButton' 
              model="['studentidx':nextstudentidx]"/>
  </tr>
