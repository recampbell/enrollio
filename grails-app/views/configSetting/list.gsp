<%@ page import="org.bworks.bworksdb.ConfigSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Settings</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="rightnow">
                    <h1 class="reallynow">Settings
                        <g:link action="create" class="add">Add Setting</g:link>
                        <br /></h1>
                        <table>
                            <thead>
                                <tr>
                                    <g:sortableColumn property="id" title="Id" />
                                    <g:sortableColumn property="value" title="Value" />
                                    <g:sortableColumn property="description"
                                    title="Description" />
                                    <g:sortableColumn property="isDefault"
                                    title="Is Default" />
                                    <g:sortableColumn property="configKey" title="Key" />
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${configSettingInstanceList}" status="i"
                                var="configSettingInstance">
                                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                        <td>
                                            <g:link action="show"
                                            id="${configSettingInstance.id}">
                                            ${fieldValue(bean:configSettingInstance,
                                            field:'id')}</g:link>
                                        </td>
                                        <td>${fieldValue(bean:configSettingInstance,
                                        field:'value')}</td>
                                        <td>${fieldValue(bean:configSettingInstance,
                                        field:'description')}</td>
                                        <td>${fieldValue(bean:configSettingInstance,
                                        field:'isDefault')}</td>
                                        <td>${fieldValue(bean:configSettingInstance,
                                        field:'configKey')}</td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    <div class="paginateButtons">
                        <g:paginate total="${configSettingInstanceTotal}" />
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
