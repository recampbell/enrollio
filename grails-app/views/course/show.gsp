<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="course" />
        <title>Course: ${courseInstance} </title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>${courseInstance}</span>
                    
                        <br />
                    </h3>
                    <p class="youhave">${courseInstance.description}</p>
                </div>
                <div class="infowrap">
                    <div class="infobox">
                        <h3 class="reallynow">
                            <g:link class="headerLink book_open" 
                            action="lessons" id="${courseInstance.id}" >
                            Lessons</g:link>
                            <!-- For some reason, these links get added out of order (add->edit) -->
                                <g:link class="book_next" name="newLessonLink" action="create" controller="lesson" 
                                        params="[ 'course.id' : courseInstance.id ]">New Lesson</g:link>
                                        <br />
                            </h3>
                        <table>
                            <tbody>
                                <g:each var="lesson" in="${courseInstance.lessons}">
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
                                action="create" params="['course.id':courseInstance.id]" 
                                class="calendar_add">New Session</g:link></h3>
                        <table>
                            <tbody>
                                <g:each var="session"
                                in="${courseInstance.classSessions}">
                                    <tr>
                                        <td>
                                            <g:link controller="classSession"
                                            action="show" id="${session.id}">
                                            ${session?.name}</g:link>
                                        </td>
                                        <td><enrollio:formatDate date="${session.startDate}" /></td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                    <div class="infobox margin-left">
                        <h3>Interested Students</h3>
                        <table>
                            <tbody>
                                <g:each var="interest" in="${courseInstance.interests}">
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
                <g:render template="individualCourseMenu" />
            </div>
        </div>
    </body>
</html>
