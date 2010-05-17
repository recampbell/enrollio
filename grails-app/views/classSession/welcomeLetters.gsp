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
     <g:each var="contactInstance" in="${contactInstanceList}">
                        <h3 class="reallynow">
                            Welcome to Byteworks
                        </h3>
                        <p class="youhave">
         Sed ut perspiciatis unde omnis enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"
         1914 translation by H. Rackham
                        </p>
                        
                            <h3>Contact Info:</h3>
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
                            <h3>Student(s)</h3>
                            <ul>
                                    <g:each var="student"
                                    in="${classSessionInstance.enrollments*.student}">
                                        <li>
                                           ${student}
                                        </li>
                                    </g:each>
                                    </ul>
                        </div>
                    </div>
                    <div class="infobox margin-left">
                            <h3 class="reallynow">
                                <span>Enrollments</span>
                                <br />
                            </h3>
                            <table>
                                <tbody>
                                    <g:each var="lessonDate"
                                    in="${classSessionInstance.lessonDates}">
                                        <tr>
                                            <td>
                                                <g:link controller="lessonDate"
                                                action="show" id="${lessonDate.id}">
                                                ${lessonDate.lesson.name}</g:link>
                                            </td>
                                            <td>
                                                <enrollio:formatDate date="${lessonDate.lessonDate}" />
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
        </g:each>
    </body>
</html>
