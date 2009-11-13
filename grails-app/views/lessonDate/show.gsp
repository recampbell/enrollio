
<%@ page import="org.bworks.bworksdb.LessonDate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Something</title>
    </head>
    <body>
        <div id="container">
            <div id="wrapper">
                <div id="content">
                    <div id="rightnow">
                        <h3 class="reallynow">
            ${lessonDateInstance.lesson.name}, ${lessonDateInstance.classSession.name}
                        </h3>
                        <p class="youhave"> ${formatDate(format:'MM/dd/yyyy', date:lessonDateInstance.lessonDate)}</p>
                    </div>
                    <div id="infowrap">
                        <div id="infobox">
                            <h3>Attendees </h3>
                            <table>
                                <tbody>
                                 <g:each var="a" in="${lessonDateInstance.attendees}">
                                 <tr><td><g:link controller="attendance" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></td></tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="sidebar">
                    <ul>
                        <li>
                        <h3>
                            <a href="#" class="house">Menu</a>
                        </h3>
                        <li>
                        Something
                        </li>
                        <li>
                        </li>%{-- lucky that we can pass the ID of the program, meow --}%
                        
                        <ul>
                            <li>
                                <a href="#" class="report_seo">Something</a>
                            </li>
                        </ul></li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>





            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attendees:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                               </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Lesson Date:</td>
                            
                            
                        </tr>
 
