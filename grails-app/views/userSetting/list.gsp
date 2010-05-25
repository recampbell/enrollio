<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Settings</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h1 class="reallynow">Settings
                        <br /></h1>
                        <table>
                            <thead>
                                <tr>
                                    <g:sortableColumn property="configKey" title="Key" />
                                    <g:sortableColumn property="value" title="Value" />
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${userSettingInstanceList}" status="i"
                                var="userSetting">
                                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                        <td>
                                            <g:link action="edit" 
                                            params="[ 'configKey': userSetting.configKey]">
                                            ${userSetting.configKey}</g:link>
                                        </td>
                                        <td>
                                            ${userSetting.value}
                                        </td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    <div class="paginateButtons">
                        <g:paginate total="${userSettingInstanceTotal}" />
                    </div>
                </div>
            </div>
        </div>
        <div id="sidebar">
            <g:render template="/admin/adminMenu" />
        </div>
    </body>
</html>
