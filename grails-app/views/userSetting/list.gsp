
<%@ page import="org.bworks.bworksdb.UserSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userSetting.label', default: 'UserSetting')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'userSetting.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="value" title="${message(code: 'userSetting.value.label', default: 'Value')}" />
                        
                            <g:sortableColumn property="useSystemDefault" title="${message(code: 'userSetting.useSystemDefault.label', default: 'Use System Default')}" />
                        
                            <g:sortableColumn property="configKey" title="${message(code: 'userSetting.configKey.label', default: 'Config Key')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userSettingInstanceList}" status="i" var="userSettingInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${userSettingInstance.id}">${fieldValue(bean: userSettingInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: userSettingInstance, field: "value")}</td>
                        
                            <td><g:formatBoolean boolean="${userSettingInstance.useSystemDefault}" /></td>
                        
                            <td>${fieldValue(bean: userSettingInstance, field: "configKey")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${userSettingInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
