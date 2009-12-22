<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <title>Program - ${programInstance} </title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>${programInstance}</span>
                    
                        <br />
                    </h3>
                    <p class="youhave">${programInstance.description}</p>
                </div>
                <div class="infowrap">
                    <div class="infobox">
                        <h3 class="reallynow"><span>Lessons</span>
                            <!-- For some reason, these links get added out of order (add->edit) -->
                                <g:link class="add" action="create" controller="lesson" 
                                        params="[ 'program.id' : programInstance.id ]">Add Lesson</g:link>
                                        <br />
                            </h3>
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
                    <div class="infobox margin-left">
                            <h3 class="reallynow">Sessions<g:link controller="classSession" 
                                action="create" params="['program.id':programInstance.id]" class="add">New Session</g:link></h3>
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
                                        <td><g:formatDate format="MMM. d, yyyy" 
                                            date="${session.startDate}" /></td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                    <div class="infobox margin-left">
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
                <g:render template="/common/sideMenu" />
                <ul>
                    <li>
                     <h3>
                        <a href="" class="modules">Program</a>
                    </h3>                       
                    </li>
                    <li>
                    <g:jasperReport controller="program" action="callList"
                        jasper="callList" format="PDF" name="Call List" delimiter=" ">
                        <input type="hidden" name="id" value="${programInstance.id}" />
                    </g:jasperReport>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>
