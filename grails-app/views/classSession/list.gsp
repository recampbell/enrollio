<%@ page import="org.bworks.bworksdb.ClassSession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="classSession" />
        <title>Class Sessions</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">Sessions
                        <g:link action="create" class="add">Add Session</g:link>
                        <br />
                    </h3>
                <table>
                    <thead>
                        <tr>
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
                                    field:'name')}</g:link>
                                </td>
                                <td><g:formatDate format="MM/dd/yyyy"
                                                date="${classSessionInstance.startDate}" /></td>
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
