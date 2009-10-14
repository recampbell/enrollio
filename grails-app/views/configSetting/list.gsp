
<%@ page import="org.bworks.bworksdb.ConfigSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ConfigSetting List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ConfigSetting</g:link></span>
        </div>
        <div class="body">
            <h1>ConfigSetting List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="value" title="Value" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="isDefault" title="Is Default" />
                        
                   	        <g:sortableColumn property="configKey" title="Key" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${configSettingInstanceList}" status="i" var="configSettingInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${configSettingInstance.id}">${fieldValue(bean:configSettingInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:configSettingInstance, field:'value')}</td>
                        
                            <td>${fieldValue(bean:configSettingInstance, field:'description')}</td>
                        
                            <td>${fieldValue(bean:configSettingInstance, field:'isDefault')}</td>
                        
                            <td>${fieldValue(bean:configSettingInstance, field:'configKey')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${configSettingInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
