

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ShiroUser List</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="rightnow">
        <div class="body">
            <h1>ShiroUser List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="username" title="Username" />
                        
                   	        <g:sortableColumn property="firstName" title="First Name" />
                        
                   	        <g:sortableColumn property="lastName" title="Last Name" />
                        
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${shiroUserInstanceList}" status="i" var="shiroUserInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${shiroUserInstance.id}">${fieldValue(bean:shiroUserInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:shiroUserInstance, field:'username')}</td>
                        
                            <td>${fieldValue(bean:shiroUserInstance, field:'firstName')}</td>
                        
                            <td>${fieldValue(bean:shiroUserInstance, field:'lastName')}</td>
                            <td><g:link action="edit" id="${shiroUserInstance.id}">Edit User</g:link></td>
                        
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${shiroUserInstanceTotal}" />
            </div>
        </div>
        </div>
        </div>
        <div id="sidebar">
            <g:render template="/common/sideMenu" />
            <g:render template="/admin/adminMenu" />
        </div>
    </body>
</html>
