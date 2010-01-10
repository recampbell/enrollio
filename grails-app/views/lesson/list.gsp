<%@ page import="org.bworks.bworksdb.Lesson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <title>Lessons</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>Lessons</span>
                        <br />
                    </h3>
                        <p class="youhave">List of programs/lessons taught.</p>
                </div>
                <g:each in="${programInstanceList}" status="i" var="programInstance">
                    <g:render template="lessonList" model="[programInstance:programInstance]" />
                </g:each>
            </div>
        </div>
        <div id="sidebar">
            <g:render template="lessonMenu" />
        </div>
    </body>
</html>
