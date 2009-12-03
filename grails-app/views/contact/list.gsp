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
                <div class="rightnow">
                        <h3 class="reallynow">Contacts
                        <g:link action="create" class="add">Add Contact</g:link>
                        <br /></h3>
                        <g:form url='[controller: "contact", action: "list"]' id="searchableForm" name="searchableForm" method="get">
                          <g:textField name="q" value="${params.q}" size="20"/> <input type="submit" value="Search" />
                        </g:form>
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
                    <g:paginate total="${contactInstanceTotal}" params="${[q:previousQuery]}"/>
                </div>
            </div>
            </div>
            <div id="sidebar">
                <g:render template="/common/sideMenu" />
            </div>
        </div>
    </body>
</html>
