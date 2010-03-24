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
                        <g:render template="studentSearchForm" />
                    </div>
                    
             <div class="rightnow">
                    <h3 class="reallynow">Students 
                    <br /></h3>
                    <table>
                        <thead>
                            <tr>
                                <g:sortableColumn property="firstName" title="Student" />
                                <g:sortableColumn property="contact" title="Contact" />
                                <th>Info</th>
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
                                <td>
                                    <g:link controller="contact" action="show" 
                                    name="contactLink_${studentInstance.contact.id}"
                                    id="${studentInstance.contact.id}">
                                    ${studentInstance.contact.fullName()}
                                    </g:link>
                                </td>
                                <td>${studentInstance.contact.fullAddress() + ' ' +
                                    studentInstance.contact.abbrevPhoneNumbers()}</td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>
                    <div class="paginateButtons">
                        <g:if test="prevQuery">
                            <g:paginate params="[q:prevQuery]" total="${studentInstanceTotal}" />
                        </g:if>
                        <g:else>
                            <g:paginate total="${studentInstanceTotal}" />
                        </g:else>
                    </div>
                </div>
            </div>
            <div id="sidebar">
                <g:render template="studentMenu" />
            </div>
        </div>
    </body>
</html>
