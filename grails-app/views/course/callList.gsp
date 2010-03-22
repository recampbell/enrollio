
<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Contact List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Contact</g:link></span>
        </div>
        <div class="body">
            <h1>Contact List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                    <g:each in="${contactInstanceList}" status="i" var="contactInstance">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="lastName" title="Contact Name" />
                            <g:sortableColumn property="emailAddress" title="Email Address" />
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link controller="contact" action="show" id="${contactInstance.id}">${contactInstance}</g:link></td>
                            <td>${fieldValue(bean:contactInstance, field:'emailAddress')}</td>
                        </tr>
                        <tr>
                        <th>Student(s)</th>
                        <th>Grade</th>
                        <th>Gender</th>
                        <th>Birthdate</th>
                        </tr>
                        <g:each in="${contactInstance.students}" var="student">

                            <g:render template="/student/studentDetails" model="[student:student]" />
                        </g:each>
                        
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contactInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
