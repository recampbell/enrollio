<div class="box" id="studentListDiv">
<h3>Students</h3>
<table width="100%">
    <thead>
    <tr>
        <th width="25%">Name</th>
        <th width="10%">Gender</th>
        <th width="10%">Grade</th>
        <th>Birth Date</th>
        <th>Interests</th>
</tr>
</thead>
<tbody>
<g:each in="${contactInstance.students}" var="student" status="idx">
  <g:render template="/student/studentDetails" model="[student:student, idx:idx]" />
</g:each></tbody>
</table>
<!-- placeholder for a new student -->
<div id="createStudentDiv"></div>
</div>
