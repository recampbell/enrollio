<%@ page import="org.bworks.bworksdb.EnrollmentStatus;org.bworks.bworksdb.ClassSession" %>
<g:select name="status" 
class="statusSwitcher"
        enrollmentId="${enrollmentInstance.id}"
        optionValue="name"
        from="${org.bworks.bworksdb.EnrollmentStatus?.values()}" 
        value="${enrollmentInstance?.status}" noSelection="['': '']" />
