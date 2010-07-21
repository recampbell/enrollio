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
     </g:if>
     <g:each var="contactInstance" in="${contactInstanceList}">
     <div style="page-break-after:always;">
         <h3 class="reallynow">
             Welcome to ByteWORKS
         </h3>
         <p class="youhave">
        Dear Parent/Guardian,
        </p>
        <p >
        I would like to welcome your child into the ByteWorks program.  I hope your
        child will learn a lot from our class and will benefit greatly from the
        refurbished computer they receive.
        </p>
        <p >
        As a reminder, there are no fees for this class.  This class is provided as a
        service to the community, provided by vounteers, to increase the
        awareness of technology in the community.
        </p>

         <p >
         Included in this letter you will find an attached letter of understanding. 
         Please have your child bring the signed paperwork to the first
         class.</p>

         <p >
         Below is listed the contact information we have for you and your child. 
         Please review this information and make any corrections if necessary.</p>
         <table>
             <tr>
                 <th>Contact Information</th>
                 <th>Enrolled Student(s)</th>
             </tr>
             <tr>
                 <td class="border">
                     <ul class="prop">

                         <li>${contactInstance}</li> 
                         <ul class="prop">
                             <g:if test="${contactInstance.phoneNumbers}">
                             <g:each var="phone" in="${contactInstance.phoneNumbers}">
                             <li>${phone.phoneNumber}</li>
                             </g:each>
                             </g:if>
                         </ul>
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
             </tr>
         </table>
         <h3 class="reallynow">Lesson Dates</h3>Classes start at <b>${classSessionInstance.startDate.format('h:mm a')}</b> 
         but students are encouraged to arrive 30 minutes early.
         <table>
             <tbody>
                 <g:each var="lessonDate"
                 in="${classSessionInstance.lessonDates}">
                 <tr>
                     <td>
                         ${lessonDate.lesson.name}
                     </td>
                     <td>
                         <enrollio:formatDate date="${lessonDate.lessonDate}" />
                     </td>
                 </tr>
                 </g:each>
             </tbody>
         </table>

         <h3 class="reallynow">
             <span>Directions to ByteWORKS:</span>
         </h3>
         <p>
         4100 Shenandoah<br />
         Saint Louis, MO 63110<br />
         (314) 827-6640
         </p>
         <table><tr><td>
                     <h4>From Missouri</h4>

                     <ul>
                         <li>Take Highway 44 or Highway 40 east</li>
                         <li>Go south on Kingshighway</li>
                         <li>Turn left at Magnolia (Tower Grove Park)</li>
                         <li>Drive for about 1 mile, and turn left at Thurman</li>
                         <li>Go north for two blocks.  ByteWORKS is at the corner of Thurman and Shenandoah</li>
                     </ul>
                 </td>
                 <td>
                     <h4>From Illinois / Downtown</h4>
                     <ul>
                         <li>Take Highway 44 or Highway 40 west</li>
                         <li>Go south on Grand</li>
                         <li>Turn right at Magnolia (Tower Grove Park)</li>
                         <li>Drive for about 1 mile, and turn right at Thurman</li>
                         <li>Go north for two blocks.  ByteWORKS is at the corner of Thurman and Shenandoah</li>
                     </ul>
                 </td>
             </tr>
     </table>
         <img src="${resource(dir:'images', file:'directions.png')}"/>
     </div>
    </g:each>
</body>
</html>
