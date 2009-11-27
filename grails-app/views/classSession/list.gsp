<%@ page import="org.bworks.bworksdb.ClassSession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Class Sessions</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="rightnow">
                    <h3 class="reallynow">Sessions
                        <g:link action="create" class="add">Add Session</g:link>
                        <br />
                    </h3>
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="Id" />
                            <g:sortableColumn property="name" title="Name" />
                            <g:sortableColumn property="startDate" title="Start Date" />
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${classSessionInstanceList}" status="i"
                        var="classSessionInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td>
                                    <g:link action="show"
                                    id="${classSessionInstance.id}">
                                    ${fieldValue(bean:classSessionInstance,
                                    field:'id')}</g:link>
                                </td>
                                <td>${fieldValue(bean:classSessionInstance,
                                field:'name')}</td>
                                <td>${fieldValue(bean:classSessionInstance,
                                field:'startDate')}</td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
                </div>
                <div class="paginateButtons">
                    <g:paginate total="${classSessionInstanceTotal}" />
                </div>
            </div>
            <div id="sidebar">
                <g:render template="/common/sideMenu" />
            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
