<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
<%@ page import="org.bworks.bworksdb.Lesson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <title>Lessons - ${programInstance}</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <g:render template="/lesson/lessonList" model="[programInstance:programInstance]" />
            </div>
        </div>
        <div id="sidebar">
            <g:render template="individualProgramMenu" />
        </div>
    </body>
</html>
