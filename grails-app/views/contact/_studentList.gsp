
    <tr>
    <th width="10%">First Name</th>
    <th width="10%">Middle Name</th>
    <th width="10%">Last Name</th>
    <th width="5%">Grade</th>
    <th width="5%">Gender</th>
    <th>Birth Date</th>
    <th>Interests</th>
    
    </tr>

<g:each in="${contactInstance.students}" var="student" status="idx">
    <tr id="student_${idx}" class="prop">
       <g:render template="studentEdit" model="[student:student, idx:idx, availPrograms:availPrograms]" />
    </tr>
</g:each>

<g:set var="nextstudentidx" value="${contactInstance.students ? contactInstance.students.size() : 0}"/>

<!--Don't show the "New student" link if there are errors. User must fix existing errors before adding more students. -->
  <tr name="foo" id="foo">
    <g:render template='newInlineStudentButton' 
              model="['idx':nextstudentidx]"/>
  </tr>
