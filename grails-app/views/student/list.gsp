<%@ page import="org.bworks.bworksdb.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="student" />    
        <title>Student List</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                        <g:render template="/contact/contactSearchForm" />
                    </div>
                    
             <div class="rightnow">
                    <h3 class="reallynow">Students 
                    <br /></h3>
                    <table>
                        <thead>
                            <tr>
                                <g:sortableColumn property="firstName"
                                title="Name" />
                                <g:sortableColumn property="contact" title="Contact"></g:sortableColumn>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${studentInstanceList}" status="i"
                            var="studentInstance">
                                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                    <td>
                                        <g:link action="show" name="studentLink_${studentInstance.id}" 
                                        id="${studentInstance.id}">${studentInstance.fullName()}</g:link>
                                    </td>
                                <td><g:link controller="contact" action="show"
                                    id="${studentInstance.contact.id}">${fieldValue(bean:studentInstance,
                                    field:'contact')}</g:link></td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                    <div class="paginateButtons">
                        <g:paginate total="${studentInstanceTotal}" />
                    </div>
                </div>
            </div>
            <div id="sidebar">
                <g:render template="studentMenu" />
            </div>
        </div>
    </body>
</html>
