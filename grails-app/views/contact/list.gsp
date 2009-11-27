<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Contact List</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="rightnow">
                        <h3 class="reallynow">Contacts
                        <g:link action="create" class="addorder">Add Contact</g:link>
                        <br /></h3>
                        <table>
                            <thead>
                                <tr>
                                    <g:sortableColumn property="lastName" title="Name" />
                                    <g:sortableColumn property="emailAddress"
                                    title="Email Address" />
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${contactInstanceList}" status="i"
                                var="contactInstance">
                                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                        <td>
                                            <g:link action="show" id="${contactInstance.id}">
                                            ${contactInstance}</g:link>
                                        </td>
                                        <td>${fieldValue(bean:contactInstance,
                                        field:'emailAddress')}</td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                <div class="paginateButtons">
                    <g:paginate total="${contactInstanceTotal}" />
                </div>
            </div>
            </div><div id="sidebar">
                <g:render template="/common/sideMenu" />
            </div>
        </div>
    </body>
</html>
