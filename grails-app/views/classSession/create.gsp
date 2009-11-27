
<%@ page import="org.bworks.bworksdb.ClassSession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ClassSession</title>
        <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.core.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.datepicker.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'date.js')}"></script>
        <script type="text/javascript">
            // This function updates the other lessonDates when the startDate
            // is chosen from the Calendar.  Each lessonDate is given a date
            // that's a week later than the previous lessonDate
            $(document).ready(function(){
              $('#startDate').datepicker({
                  showOn: 'both',
                  buttonImage: '${resource(dir:'images', file:'calendar.gif')}',
                  onSelect: function(dateText, inst) { 
                  
                      $('.lessonDate').each(function(i) {
                          Date.format = 'mm/dd/yyyy';
                          var newDate = new Date(dateText);
                          newDate.addDays(i * 7);
                          $(this).val(newDate.asString('mm/dd/yyyy'));
                      });
                  }
              });
            });
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div id="rightnow">
            <h3 class="reallynow">New Class Session</h3>
            <g:hasErrors bean="${classSessionInstance}">
            <div class="errors">
                <g:renderErrors bean="${classSessionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Program:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:classSessionInstance,field:'name','errors')}">
                                    ${classSessionInstance.program?.name}
                                    <input type="hidden" id="program.id" name="program.id" value="${classSessionInstance.program?.id}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:classSessionInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:classSessionInstance,field:'name')}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:classSessionInstance,field:'startDate','errors')}">
                                    <input type="text" name="startDate" id="startDate"></input>
                                </td>
                            </tr>
                            <tr>
                                <g:render template="editLessonDates" model="[lessonDates:classSessionInstance.lessonDates]" /> 

                            </tr>

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </div>
    </div>
    </body>
</html>

