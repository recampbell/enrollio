
<%@ page import="org.bworks.bworksdb.ClassSession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ClassSession</title>
    </head>
    <body>
        <div class="body">
            <h1>Show ClassSession</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table style="float:left;">
                    <caption>Session: ${classSessionInstance.name}</caption>
                    <tbody>


                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>

                            <td valign="top" class="value">${fieldValue(bean:classSessionInstance, field:'id')}</td>

                        </tr>


                        <tr class="prop">
                            <td valign="top" class="name">Lesson Dates:</td>

                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="l" in="${classSessionInstance.lessonDates}">
                                    <li><g:link controller="lessonDate" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>

                        </tr>













                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>

                            <td valign="top" class="value">${fieldValue(bean:classSessionInstance, field:'name')}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Program:</td>

                            <td valign="top" class="value">${fieldValue(bean:classSessionInstance, field:'program.name')}</td>

                        </tr>


                        <tr class="prop">
                            <td valign="top" class="name">Start Date:</td>

                            <td valign="top" class="value">${fieldValue(bean:classSessionInstance, field:'startDate')}</td>

                        </tr>

                    </tbody>
                </table>
                <table>
                    <caption>Enrolled Students</caption>
                    <g:each var="e" in="${classSessionInstance.enrollments}">
                    <tr>
                        <td>
                            <g:link controller="student" action="show" id="${e.id}">
                            ${e.student}
                            </g:link>
                    </td>
                </tr>
                    </g:each>

                </table>


                        </tr>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${classSessionInstance?.id}" />
                    <g:link action="enroll" params="[classSessionId : classSessionInstance.id]">Enroll Students in this Session</g:link>
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>

