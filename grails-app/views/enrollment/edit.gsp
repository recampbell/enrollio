
<%@ page import="org.bworks.bworksdb.Enrollment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'enrollment.label', default: 'Enrollment')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${enrollmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${enrollmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="update" method="post" >
                <g:hiddenField name="id" value="${enrollmentInstance?.id}" />
                <g:hiddenField name="version" value="${enrollmentInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="status"><g:message code="enrollment.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: enrollmentInstance, field: 'status', 'errors')}">
                                    <g:select name="status" from="${org.bworks.bworksdb.EnrollmentStatus?.values()}" value="${enrollmentInstance?.status}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="student"><g:message code="enrollment.student.label" default="Student" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: enrollmentInstance, field: 'student', 'errors')}">
                                    <g:select name="student.id" from="${org.bworks.bworksdb.Student.list()}" optionKey="id" value="${enrollmentInstance?.student?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="classSession"><g:message code="enrollment.classSession.label" default="Class Session" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: enrollmentInstance, field: 'classSession', 'errors')}">
                                    <g:select name="classSession.id" from="${org.bworks.bworksdb.ClassSession.list()}" optionKey="id" value="${enrollmentInstance?.classSession?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
