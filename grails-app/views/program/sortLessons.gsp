<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
<%@ page import="org.bworks.bworksdb.Lesson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
	<script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'table_drag_drop.js')}"></script>
        <title>Sort Lessons - ${programInstance}</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <g:form action="saveLessonSort" method="post" name="sortLessonsForm">
                <input type="hidden" name="id" value="${programInstance.id}" />
            <div class="box">
                <g:render template="sortLessons" 
                model="[programInstance:programInstance]" />
            </div>
             <label for="saveButton"></label>
                    <g:submitButton class="save" name="saveButton" value="Save" />
                        or&nbsp;
                        <g:if test="${cancelLink}">
                        <a href="${cancelLink}">Cancel</a>
                        </g:if>
                        <g:else>
                        <g:link name="cancelLink" class="cancelLink" controller="program"
                        id="${programInstance.id}" action="show" >Cancel</g:link>
                        </g:else>
            </g:form>
           </div>
        </div>
        <div id="sidebar">
            <g:render template="individualProgramMenu" />
        </div>
    </body>
</html>
