<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="org.bworks.bworksdb.Contact" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Contact List</title>
    </head>
    <body>
        <div id="container">
            <div id="wrapper">
                <div id="content">
                    <div id="infowrap">
                        <div id="infobox">
                            <h3>Contacts</h3>
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
                                                <g:link action="show"
                                                id="${contactInstance.id}">
                                                ${contactInstance}</g:link>
                                            </td>
                                            <td>${fieldValue(bean:contactInstance,
                                            field:'emailAddress')}</td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="infowrap">
                        <div id="infobox">
                        <div class="paginateButtons">
                            <g:paginate total="${contactInstanceTotal}" />
                        </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="sidebar">
                <ul>
                    <li>
                        <h3> <a href="#" class="house">Menu</a> </h3>
                    </li>
                    <li>
                        <g:link action="create">New Contact</g:link>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>
