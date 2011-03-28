<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="tabName" content="classSession" />
        <title>Welcome Letter</title>
        <link rel="stylesheet" media="print" type="text/css" href="${resource(dir:'css', file:'print.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'print.css')}" />
    </head>
 <body>
     <g:if test="${!contactInstanceList}">
     Nobody's enrolled in ${classSessionInstance} yet.
     <g:link action="show" id="${classSessionInstance.id}">Back to Session</g:link>
     </g:if><b>Class Date: </b>${classSessionInstance.startDate.format('MM/dd/yyyy h:mm a')} 
         <table>
             <tr>
                 <th>Contact</th>
                 <th>Phone</th>
                 <th>Enrolled Student(s)</th>
             </tr>
     <g:each var="contactInstance" in="${contactInstanceList}">
             <tr>
                 <td class="border">
                     ${contactInstance}
                 </td>
             <td class="border">
                 <ul class="prop">
                 <g:if test="${contactInstance.phoneNumbers}">
                 <g:each var="phone" in="${contactInstance.phoneNumbers}">
                 <li>${phone.phoneNumber}</li>
                 </g:each>
                 </g:if>
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
                 <td class="border">
                     <ul>
                         <g:each var="student"
                         in="${classSessionInstance.enrollments*.student}">
                         <g:if test="${student.contact.id == contactInstance.id}">
                         <li> ${student} </li>
                         </g:if>
                         </g:each>
                     </ul>
                 </td>
                 <td>&nbsp;</td>
             </tr>
    </g:each>
         </table>
</body>
</html>
