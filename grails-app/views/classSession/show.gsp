<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="classSession" />
        <title>Class Session: ${classSessionInstance.name}</title>
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
                                    <td><enrollio:formatDate date="${classSessionInstance.startDate}" /></td>
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
                                <g:link name="editEnrollmentsLink" action="editEnrollments" id="${classSessionInstance.id}">
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
                    <g:render template="individualClassSessionMenu" model="[classSessionInstance:classSessionInstance]" />
                </div>
            </div>
        </div>
    </body>
</html>
