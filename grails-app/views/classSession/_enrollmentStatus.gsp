<%@ page import="org.bworks.bworksdb.EnrollmentStatus;org.bworks.bworksdb.ClassSession" %>
<g:select name="status${enr.id}" from="${EnrollmentStatus.list()}"
    class="statusSwitcher" 
    enrollmentId="${enr.id}" 
    value="${enr.status}" 
    optionValue="name" />
