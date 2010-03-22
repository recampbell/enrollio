<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="course" />
        <title>Courses</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                            <span>Courses</span>
                        <br />
                        </h3>
                        <table>
                            <thead></thead>
                            <tbody>
                                <g:each in="${courseInstanceList}" status="i"
                                var="courseInstance">
                                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                        <td>
                                            <g:link name="courseLink${courseInstance.id}" 
                                                  action="show" 
                                                      id="${courseInstance.id}">
                                            ${fieldValue(bean:courseInstance,
                                            field:'name')}</g:link>
                                        </td>
                                        <td>${fieldValue(bean:courseInstance,
                                        field:'description')}</td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                </div>
            </div>
            <div id="sidebar">
                <g:render template="courseMenu" />
            </div>
        </div>
    </body>
</html>
