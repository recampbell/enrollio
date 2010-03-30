<%@ page import="org.bworks.bworksdb.EnrollmentStatus;org.bworks.bworksdb.ClassSession" %>
<g:each var="enrStatus" in="${EnrollmentStatus.list()}">
    <g:radio class="statusSwitcher" enrollmentId="${enr.id}" 
    attendanceStatus="${enrStatus}" name="status${enr.id}" 
    value="present" 
    checked="${enr.status == enrStatus}"/>${enrStatus.name}
</g:each>
