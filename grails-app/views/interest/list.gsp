
<%@ page import="org.bworks.bworksdb.Interest" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <g:if test="${courseInstance}">
            <title>Interest List for ${courseInstance}</title>
        </g:if>
        <g:else>
            <title>Interest List</title>
        </g:else>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Interest</g:link></span>
        </div>
        <div class="body">
        <g:if test="${courseInstance}">
            <h1>Interest List for ${courseInstance}<h1>
        </g:if>
        <g:else>
            <h1>Interest List</h1>
        </g:else>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <th>Student</th>
                        
                   	        <th>Note</th>
                   	    
                   	        <g:sortableColumn property="active" title="Active" />
                        
                   	    
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${interestInstanceList}" status="i" var="interestInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${interestInstance.id}">${fieldValue(bean:interestInstance, field:'student')}</g:link></td>
                        
                            <td>${fieldValue(bean:interestInstance, field:'note')}</td>
                        
                            <td>${fieldValue(bean:interestInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${interestInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
