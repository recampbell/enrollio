<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <title>Programs</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                            <span>Programs</span>
                        <br />
                        </h3>
                        <table>
                            <thead></thead>
                            <tbody>
                                <g:each in="${programInstanceList}" status="i"
                                var="programInstance">
                                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                        <td>
                                            <g:link action="show" id="${programInstance.id}">
                                            ${fieldValue(bean:programInstance,
                                            field:'name')}</g:link>
                                        </td>
                                        <td>${fieldValue(bean:programInstance,
                                        field:'description')}</td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                </div>
            </div>
            <div id="sidebar">
                <g:render template="programMenu" />
            </div>
        </div>
    </body>
</html>
