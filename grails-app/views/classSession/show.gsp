<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="classSession" />
        <title>Class Sessions - Enrollio</title>
    </head>
    <body>
        <div id="container">
            <div id="wrapper">
                <div id="content">
                    <div class="rightnow">
                        <h3 class="reallynow">
                            <span>${classSessionInstance.name}</span>
                            <br />
                        </h3>
                        <p class="youhave"></p>
                        <table>
                            <thead></thead>
                            <tbody>
                                <tr>
                                    <td>
                                        Program:
                                    </td>
                                    <td><g:link controller="program"
                                                action="show"
                                                id="${classSessionInstance.program.id}">
                                        ${classSessionInstance.program.name}
                                                </g:link></td>
                                </tr>
                                <tr>
                                    <td>
                                        Start Date:
                                    </td>
                                    <td><g:formatDate format="MMMM d, yyyy"
                                        date="${classSessionInstance.startDate}" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="infowrap">
                        <div class="infobox">
                            <h3>Lesson Dates</h3>
                            <table>
                                <tbody>
                                    <g:each var="lessonDate"
                                    in="${classSessionInstance.lessonDates}">
                                        <tr>
                                            <td>
                                                <g:link controller="lessonDate"
                                                action="show" id="${lessonDate.id}">
                                                ${lessonDate.lesson.name}</g:link>
                                            </td>
                                            <td>
                                                <g:formatDate format="MMMM d, yyyy"
                                                date="${lessonDate.lessonDate}" />
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                        <div class="infobox margin-left">
                            <h3 class="reallynow">
                                <span>Enrollments</span>
                                <g:link action="enroll" id="${classSessionInstance.id}">
                                (Add Enrollments)</g:link>
                                <br />
                            </h3>
                            <table>
                                <tbody>
                                    <g:each var="enr"
                                    in="${classSessionInstance.enrollments}">
                                        <tr>
                                            <td>
                                                <a href="#">${enr.student}</a>
                                            </td>
                                            <td>${enr.status}</td>
                                        </tr>
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
                                <a href="#" class="calendar">Class Session</a>
                            </h3>
                            <ul>
                                <!-- TODO Refactor this into a taglib or template -->
                                <li>

                                    <script type="text/javascript">
                                        function submit_graduationCertificate(link) {
                                          link.parentNode._format.value = link.title;
                                          link.parentNode.submit();
                                          return false;
                                        }
                                    </script>
                                    <g:form name="graduationCertificate" class="sideMenuForm jasperReport" 
                                            action="printGraduationCertificates">
                                        <input type="hidden" name="_format" value="PDF" />
                                        <!-- Name shown on top of PDF report -->
                                        <input type="hidden" name="_name" value="Graduation Certificates" />
                                        <input type="hidden" name="_file" value="graduationCertificate" />
                                        <input type="hidden" name="id" value="1" />
                                        <!-- TODO The &nbsp; is a kludge find CSS way to justify image
                                        and text so it looks o.k. -->
                                        <a href="#" class="graduation_cap" title="PDF" onClick="return submit_graduationCertificate(this)">
                                        &nbsp;&nbsp;Grad. Certificates
                                    </a>
                                    </g:form>
                                </li>
                                <li>
                                    <g:link class="application_list"
                                    action="attendanceSheet"
                                    id="${classSessionInstance.id}">&#160;Attendance
                                    Sheet</g:link>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
