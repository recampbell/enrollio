
<%@ page import="org.bworks.bworksdb.Program" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Program List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Program</g:link></span>
        </div>
        <div class="body">
            <h1>Program List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                   	        <g:sortableColumn property="name" title="Name" />
                   	        <g:sortableColumn property="description" title="Description" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${programInstanceList}" status="i" var="programInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${programInstance.id}">${fieldValue(bean:programInstance, field:'name')}</g:link></td>
                        
                            <td>${fieldValue(bean:programInstance, field:'description')}</td>
                        
                            <td><g:link action="callList" id="${programInstance.id}">Create Call List</g:link></td>
                            <td><g:link controller="classSession" action="create" params="['program.id':programInstance.id]">New Class Session</g:link></td>
                            %{-- lucky that we can pass the ID of the program, meow --}%
                            <td><g:pdfLink url="/program/pdfCallList">PDF Call List</g:pdfLink></td>
                        </tr>


                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${programInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
