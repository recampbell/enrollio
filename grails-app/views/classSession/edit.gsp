
<%@ page import="org.bworks.bworksdb.ClassSession" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="classSession" />
        <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.core.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.datepicker.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'date.js')}"></script>
        <script type="text/javascript">
            $(document).ready(function(){ 
            $('#startDate').datepicker({
                  showOn: 'both',
                  buttonImage: '/enrollio/images/calendar.gif',
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
        <title>Edit ClassSession</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <a href="#" class="headerLink calendar_edit">Edit Class Session</a>
                        <br />
                    </h3>
                        <g:hasErrors bean="${classSessionInstance}">
                            <div class="errors">
                                <g:renderErrors bean="${classSessionInstance}"
                                as="list" />
                            </div>
                        </g:hasErrors>
                        <g:if test="${flash.message}">
                            <div class="message">${flash.message}</div>
                        </g:if>
            <g:form method="post" >
                <input type="hidden" name="id" value="${classSessionInstance?.id}" />
                <input type="hidden" name="version" value="${classSessionInstance?.version}" />
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:classSessionInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:classSessionInstance,field:'name')}"/>
                                </td>
                            </tr>

                            <tr>
                                <td valign="top" class="name">
                                    <label for="startDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:classSessionInstance,field:'startDate','errors')}">
                                    <input type="text" id="startDate"
                                    name="startDate"
                                    value="${formatDate(format:'MM/dd/yyyy', date:classSessionInstance.startDate)}"
                                    class="lessonDate"></input>
                                Time
                                  <enrollio:timeSelectors date="${classSessionInstance.startDate}" /></td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lessonDates">Lesson Dates:</label>
                                </td>
                                <td >
                                    <g:render template="editLessonDates"
                                    model="[lessonDates:classSessionInstance.lessonDates]" />

                                </td>
                            </tr>

                        </tbody>
                    </table>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </div>
    <div id="sidebar">
        <g:render template="individualClassSessionMenu" model="[classSessionInstance:classSessionInstance]" />
    </div>
</div>
</body>
</html>

