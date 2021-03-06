
<%@ page import="org.bworks.bworksdb.LessonDate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="classSession" />
        <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                    $('.statusSwitcher').click(function() {
                        $.post('${createLink(controller:"attendance",
                                                 action:"updateStatus")}',
                               { 'status' : $(this).attr('attendanceStatus'),
                                 'id'     : $(this).attr('attendanceId') });
                        });
                    });


        </script>
        <title>${lessonDateInstance}</title>
    </head>
    <body>
        <div id="container">
            <div id="wrapper">
                <div id="content">
                    <div class="rightnow">
                        <h3 class="reallynow">
                            ${lessonDateInstance.lesson.name},
                            <enrollio:formatDate date="${lessonDateInstance.lessonDate}" />
                        </h3>
                        <p class="youhave">
             ${lessonDateInstance.classSession.name} </p>
                    </div>
                    <div class="rightnow">
                            <h3 class="reallynow">Attendees </h3>
                            <table>
                                <tbody>
                                    <g:each var="a" 
                                             in="${lessonDateInstance.attendees}">
                                    <tr>
                                        <td>
                                            <g:link controller="attendance" 
                                            action="show" 
                                            id="${a.id}">${a.student}</g:link>

                                            </td>
                                            <td>
                                                <g:radio class="statusSwitcher" attendanceId="${a.id}" attendanceStatus="present" name="status${a.id}" value="present" checked="${a.status == 'present'}"/>&nbsp;Present
                                                <g:radio class="statusSwitcher" attendanceId="${a.id}" attendanceStatus="absent" name="status${a.id}" value="absent" checked="${a.status == 'absent'}" />&nbsp;Absent
                                                <g:radio class="statusSwitcher" attendanceId="${a.id}" attendanceStatus="late" name="status${a.id}" value="late" checked="${a.status == 'late'}"/>&nbsp;Late
                                            </td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                    </div>
                </div>
                <div id="sidebar">
                    <g:render template="/classSession/individualClassSessionMenu"
                    model="[ classSessionInstance : lessonDateInstance.classSession ]"/>
                </div>
            </div>
        </div>
    </body>
</html>
