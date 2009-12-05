
<%@ page import="org.bworks.bworksdb.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="student" />
        <title>Create Student</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Student List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Student</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${studentInstance}">
            <div class="errors">
                <g:renderErrors bean="${studentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                                    <g:select optionKey="id" from="${org.bworks.bworksdb.Contact.list()}" name="contact.id" value="${studentInstance?.contact?.id}" ></g:select>
                    <table>
                        <tbody>
                        <tr>
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Grade</th>
                        <th>Gender</th>
                        <th>Birth Date</th>
                        
                        </tr>
                            <tr class="prop">
                                <td valign="top" class="value ${hasErrors(bean:studentInstance,field:'firstName','errors')}">
                                    <input type="text" id="firstName" name="firstName" value="${fieldValue(bean:studentInstance,field:'firstName')}"/>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentInstance,field:'middleName','errors')}">
                                    <input type="text" id="middleName" name="middleName" value="${fieldValue(bean:studentInstance,field:'middleName')}"/>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentInstance,field:'lastName','errors')}">
                                    <input type="text" id="lastName" name="lastName" value="${fieldValue(bean:studentInstance,field:'lastName')}"/>
                                </td>
                        
                                <td valign="top" class="value ${hasErrors(bean:studentInstance,field:'grade','errors')}">
                                    <input type="text" id="grade" name="grade" value="${fieldValue(bean:studentInstance,field:'grade')}"/>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentInstance,field:'gender','errors')}">
                                    <input type="text" id="gender" name="gender" value="${fieldValue(bean:studentInstance,field:'gender')}"/>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentInstance,field:'birthDate','errors')}">
                                    <g:datePicker name="birthDate" value="${studentInstance?.birthDate}" precision="day" ></g:datePicker>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
