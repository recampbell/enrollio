<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <title>Lessons - ${programInstance}</title>
    </head>
    <body>
        <div id="content">
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:render template="/lesson/lessonList"
            model="[programInstance:programInstance]" />
        </div>
        <div id="sidebar">
            <g:render template="individualProgramMenu" />
        </div>
    </body>
</html>
