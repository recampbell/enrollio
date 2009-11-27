<%@ page import="org.bworks.bworksdb.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Student List</title>
    </head>
    <body>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <li>
                        <g:link controller="contact" action="create" class="addorder">New
                        Student</g:link>
                    </li>
                </ul>
            </div>
        </div>
            <div id="wrapper">
                <div id="content">
                    <h3>Students</h3>
                    <g:if test="${flash.message}">
                        <div class="message">${flash.message}</div>
                    </g:if>
                    <div class="list">
                        <table>
                            <thead>
                                <tr>
                                    <g:sortableColumn property="id" title="Id" />
                                    <g:sortableColumn property="lastName"
                                    title="Last Name" />
                                    <g:sortableColumn property="birthDate"
                                    title="Birth Date" />
                                    <th>Contact</th>
                                    <g:sortableColumn property="firstName"
                                    title="First Name" />
                                    <g:sortableColumn property="gender" title="Gender" />
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${studentInstanceList}" status="i"
                                var="studentInstance">
                                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                        <td>
                                            <g:link action="show"
                                            id="${studentInstance.id}">
                                            ${fieldValue(bean:studentInstance,
                                            field:'id')}</g:link>
                                        </td>
                                        <td>${fieldValue(bean:studentInstance,
                                        field:'lastName')}</td>
                                        <td>${fieldValue(bean:studentInstance,
                                        field:'birthDate')}</td>
                                        <td>${fieldValue(bean:studentInstance,
                                        field:'contact')}</td>
                                        <td>${fieldValue(bean:studentInstance,
                                        field:'firstName')}</td>
                                        <td>${fieldValue(bean:studentInstance,
                                        field:'gender')}</td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                    <div class="paginateButtons">
                        <g:paginate total="${studentInstanceTotal}" />
                    </div>
                </div>
            <div id="sidebar">
                <g:render template="/common/sideMenu" />
            </div>
            </div>
    </body>
</html>
