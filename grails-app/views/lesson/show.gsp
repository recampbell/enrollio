
<%@ page import="org.bworks.bworksdb.Lesson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <title>Show Lesson</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <div class="box">
            <h3>Lesson: ${lessonInstance}</h3>
                <table>
                    <tbody>
                    
                        <enrollio:textField model="${lessonInstance}" fieldName="description" />
                    
                        <tr class="prop">
                            <td valign="top" class="name">Program:</td>
                            
                            <td valign="top" class="value"><g:link controller="program" action="show" id="${lessonInstance?.program?.id}">${lessonInstance?.program?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                        <enrollio:textField model="${lessonInstance}" fieldName="sequence" />
                    </tbody>
                </table
                <div class="box">
                <h3>Lesson Dates:</h3>
                <table>
                    <thead>
                        <tr>
                            <td>Session</td>
                            <td>Date</td>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each var="l" in="${lessonInstance.lessonDates}">
                        <tr class="prop">
                            
                            <td valign="top" style="text-align:left;" class="value">
                            <g:link controller="lessonDate" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link>
                            </td>
                            
                            <td valign="top" class="value">
                                <enrollio:formatDate date="${l.lessonDate}" />
                            
                            </td>
                        </tr>
                        </g:each>
                
                    </tbody>
                </table>
            </div>
        </div>
        </div>
            <div id="sidebar">
                <g:render template="lessonMenu" />
            </div>
    </body>
</html>
