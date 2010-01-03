<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />    
        <title>Contact List</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                        <g:render template="contactSearchForm" />
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
                                            <a href="${createLink(action:'show',
                                                                      id:contactInstance.id)}"
                                               id="contactLink${contactInstance.id}">
                                            ${contactInstance}
                                            </a> 
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
