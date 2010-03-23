<%@ page import="org.bworks.bworksdb.Course;" %>
<div class="box" id="studentListDiv">
<h3>Students</h3>
<g:if test="${flash.studentMessage}">
  <div class="message">${flash.studentMessage}</div>
</g:if>
<table width="100%">
<tbody>
        <g:each in="${contactInstance.students}" var="student" status="idx">
            <g:render template="/student/studentDetails" model="[student:student, idx:idx]" />
        </g:each>
</tbody>
</table>

</div>
