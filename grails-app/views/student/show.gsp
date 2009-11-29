
<%@ page import="org.bworks.bworksdb.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Student</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
        <div class="body">
            <h1>Show Student</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'id')}</td>
                            
                            <td valign="top" class="name">Contact:</td>
                            
                            <td valign="top" class="value"><g:link controller="contact" action="show" id="${studentInstance?.contact?.id}">${studentInstance?.contact?.encodeAsHTML()}</g:link></td>
                        </tr>
                        <tr class="prop">
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${studentInstance}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Birth Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'birthDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Grade:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'grade')}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Gender:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'gender')}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Email Address:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:studentInstance, field:'emailAddress')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Interests:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="i" in="${studentInstance.interests}">
                                    <li><g:link controller="interest" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${studentInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
        </div>
        </div><div id="sidebar">
            <g:render template="/common/sideMenu" />
        </div>
    </body>
</html>
