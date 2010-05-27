<%@ page import="org.bworks.bworksdb.EnrollmentStatus;org.bworks.bworksdb.ClassSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="classSession" />
        <title>Graduation: ${classSessionInstance.name}</title>
	<script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.4.2.min.js')}"></script>
        <script type="text/javascript">
             $(document).ready(function(){
                $('.statusSwitcher').change(function(){
                    $.post('${createLink(controller:"enrollment",
                                             action:"enrollmentStatus")}',
                                             { 'status' : $(this).val(), 
                                                   'id' : $(this).attr("enrollmentId") });
                    });
                });
        </script>
    </head>
    <body>
        <div id="container">
            <div id="wrapper">
                <div id="content">
                    <div class="rightnow">
                        <h3 class="reallynow">
                            <span>Graduation: ${classSessionInstance.name}</span>
                            <br />
                        </h3>
                            <table>
                                <thead>
                                    <th>Student</th>
                                    <th width="60%">Attendance</th>
                                    <th>Status</th>
                                </thead>
                                <tbody>
                                    <g:each var="enrollmentInstance" in="${classSessionInstance.enrollments}">
                                        <tr>
                                            <td>
                                                <g:link action="show"
                                                controller="student"
                                                id="${enrollmentInstance.student.id}">
                                                ${enrollmentInstance.student}</g:link>
                                            </td>
                                            <td>
                                            <enrollio:studentAttendanceSummary 
                                                summary="${attendancesForSession[enrollmentInstance.student.id]}" />
                                            </td>
                                            <td>
                                                <g:render template="enrollmentStatus" 
                                                model="[enrollmentInstance : enrollmentInstance]" />
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                <div id="sidebar">
                    <g:render template="individualClassSessionMenu" 

                    model="[showGradCertsLink:true, classSessionInstance:classSessionInstance]" />
                </div>
            </div>
        </div>
    </body>
</html>
