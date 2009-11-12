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
                            <span>${programInstance}</span>
                            <br />
                        </h3>
                        <p class="youhave">${programInstance.description}</p>
                    </div>
                    <div id="infowrap">
                        <div id="infobox">
                            <h3>Lessons
                                <span>
                                    <g:link action="create" controller="lesson" 
                                            params="[ 'program.id' : programInstance.id ]">
                                    (New)
                                    </g:link></span></h3>
                            <table>
                                <tbody>
                                    <g:each var="lesson" in="${programInstance.lessons}">
                                        <tr>
                                            <td>
                                                <g:link controller="lesson" action="show"
                                                id="${lesson.id}">
                                                ${lesson?.encodeAsHTML()}</g:link>
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                        <div id="infobox">
                            <h3>Sessions</h3>
                            <table>
                                <tbody>
                                    <g:each var="session"
                                    in="${programInstance.classSessions}">
                                        <tr>
                                            <td>
                                                <g:link controller="classSession"
                                                action="show" id="${session.id}">
                                                ${session?.encodeAsHTML()}</g:link>
                                            </td>
                                            <td>${session.startDate}</td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="infowrap">
                        <div id="infobox">
                            <h3>Interested Students</h3>
                            <table>
                                <tbody>
                                    <g:each var="interest" in="${programInstance.interests}">
                                        <tr>
                                            <td>
                                                ${interest.dateCreated}
                                            </td>
                                            <td>
                                                ${interest.student}
                                            </td>
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
                        <li>
                            <g:link action="callList" id="${programInstance.id}">View
                            Call List</g:link>
                        </li>
                        <li>
                            <g:link controller="classSession" action="create"
                            params="['program.id':programInstance.id]">New Class
                            Session</g:link>
                        </li>%{-- lucky that we can pass the ID of the program, meow --}%
                        
                        <li>
                            <g:pdfLink url="/program/pdfCallList">PDF Call List</g:pdfLink>
                        </li>
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
