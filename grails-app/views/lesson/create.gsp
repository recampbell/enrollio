
<%@ page import="org.bworks.bworksdb.Lesson" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/table_drag_drop.js"></script>
        <script type="text/javascript">
             $(document).ready(function(){
                 $('#name').change(function() {
                     $('#newLessonNameInSequence').text($(this).val());
                 });
             });
         </script>
        <title>Create Lesson:</title>         
    </head>
    <body>
         <div id="wrapper">
            <div id="content">
                <g:form action="save" method="post" name="newLessonForm">
                <div class="box">
                <h3>Create Lesson</h3>
                <g:hasErrors bean="${lessonInstance}">
                    <div class="errors">
                        <g:renderErrors bean="${lessonInstance}" as="list" />
                    </div>
                </g:hasErrors>
                    <label for="name">Name : </label> 
                    <input name="id" id="id" type="hidden" value="${lessonInstance.id}" />
                    <input type="text" id="name" name="name" value="${fieldValue(bean:lessonInstance,field:'name')}"/><br />
                    
                    <label for="program">Program:</label>
                    <g:select optionKey="id" from="${org.bworks.bworksdb.Program.list()}" name="program.id" value="${lessonInstance?.program?.id}" ></g:select><br />
                    <input type="hidden" id="sequence" class="value ${hasErrors(bean:lessonInstance,field:'sequence','errors')}" name="sequence" value="${fieldValue(bean:lessonInstance,field:'sequence')}" /><br />
                    <label for="description">Description:</label>
                        <g:textArea name="description" 
                            value="${fieldValue(bean:lessonInstance,field:'description')}"
                            rows="5" cols="40"/>
                        <br />
                </div>
                <div class="box">
                    <g:render template="/program/sortLessons"
                              model="[programInstance : lessonInstance.program,
                              lessonInstance  : lessonInstance]" />
                </div>

                    <label for="saveButton"></label>
                    <g:submitButton class="save" name="saveButton" value="Save" />
                        or&nbsp;
                        <g:if test="${cancelLink}">
                        <a href="${cancelLink}">Cancel</a>
                        </g:if>
                        <g:else>
                        <g:link name="cancelLink" class="cancelLink" controller="program"
                        id="${lessonInstance.program?.id}" action="show" >Cancel</g:link>
                        </g:else>
            </g:form>
        </div>
    </div>
    </body>
</html>
