
<%@ page import="org.bworks.bworksdb.UserSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userSetting.label', default: 'UserSetting')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userSettingInstance}">
            <div class="errors">
                <g:renderErrors bean="${userSettingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="value"><g:message code="userSetting.value.label" default="Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userSettingInstance, field: 'value', 'errors')}">
                                    <g:textField name="value" value="${userSettingInstance?.value}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="useSystemDefault"><g:message code="userSetting.useSystemDefault.label" default="Use System Default" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userSettingInstance, field: 'useSystemDefault', 'errors')}">
                                    <g:checkBox name="useSystemDefault" value="${userSettingInstance?.useSystemDefault}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="configKey"><g:message code="userSetting.configKey.label" default="Config Key" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userSettingInstance, field: 'configKey', 'errors')}">
                                    <g:textField name="configKey" value="${userSettingInstance?.configKey}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
