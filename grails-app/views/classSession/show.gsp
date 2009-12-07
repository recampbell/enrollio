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
                            <span>Class Session: ${classSessionInstance.name}</span>
                            <br />
                        </h3>
                        <p class="youhave"></p>
                        <table>
                            <thead></thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <a href="#">Program:</a>
                                    </td>
                                    <td>${classSessionInstance.program.name}</td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="#">Start Date</a>
                                    </td>
                                    <td>${classSessionInstance.startDate}</td>
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
                                                <g:formatDate format="MM/dd/yyyy"
                                                date="${lessonDate.lessonDate}" />
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                        <div class="infobox" class="margin-left">
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
                                <a href="#" class="house">Menu</a>
                            </h3>
                        </li>
                        <li>
                        <!-- TODO Refactor this into a taglib or template -->
                        <script type="text/javascript">
                            function submit_graduationCertificate(link) {
                              link.parentNode._format.value = link.title;
                              link.parentNode.submit();
                              return false;
                            }
                        </script>
                        <g:form name="graduationCertificate" class="jasperReport" 
                                action="printGraduationCertificates">
                            <input type="hidden" name="_format" value="PDF" />
                            <!-- Name shown on top of PDF report -->
                            <input type="hidden" name="_name" value="Graduation Certificates" />
                            <input type="hidden" name="_file" value="graduationCertificate" />
                            <input type="hidden" name="id" value="1" />
                            <!-- TODO The &nbsp; is a kludge find CSS way to justify image
                            and text so it looks o.k. -->
                            <a href="#" class="manage_page" title="PDF" onClick="return submit_graduationCertificate(this)">
                            &nbsp;Grad. Certificates
                            </a>
                        </g:form>

                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
