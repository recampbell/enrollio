<%@ page import="org.bworks.bworksdb.EnrollmentStatus;org.bworks.bworksdb.ClassSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="classSession" />
        <title>Graduation: ${classSessionInstance.name}</title>
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
                        <p class="youhave"></p>
                        <table>
                            <thead></thead>
                            <tbody>
                                <tr>
                                    <td>
                                        Course:
                                    </td>
                                    <td><g:link controller="course"
                                                action="show"
                                                id="${classSessionInstance.course.id}">
                                        ${classSessionInstance.course.name}
                                                </g:link></td>
                                </tr>
                                <tr>
                                    <td>
                                        Start Date:
                                    </td>
                                    <td><enrollio:formatDate date="${classSessionInstance.startDate}" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                        <div class="rightnow">
                            <h3 class="reallynow">
                                <span>Enrollments</span>
                                <br />
                            </h3>
                            <table>
                                <thead>
                                    <th>Student</th>
                                    <th width="60%">Attendance</th>
                                    <th>Status</th>
                                </thead>
                                <tbody>
                                    <g:each var="enr" in="${classSessionInstance.enrollments}">
                                        <tr>
                                            <td>
                                                <a href="#">${enr.student}</a>
                                            </td>
                                            <td>
                                            <enrollio:studentAttendanceSummary 
                                                summary="${attendancesForSession[enr.student.id]}" />
                                            </td>
                                            <td>
                                                <g:render template="enrollmentStatus" model="[enr : enr]" />
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                <div id="sidebar">
                    <g:render template="individualClassSessionMenu" model="[classSessionInstance:classSessionInstance]" />
                </div>
            </div>
        </div>
    </body>
</html>
