<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'jquery.multiselect.css')}" />

	<script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.4.2.min.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'jquery.multiselect.min.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.datepicker.js')}"></script>
        <script type="text/javascript">
             $(document).ready(function(){
                  // Show date picker for the "Signup Date" that shown on the
                  // new student form.  (It's rendered in the createStudent.gsp template below)
                     $('#signupDate').datepicker({changeYear:true});
                  $("select.multiselect").multiSelect({
                      showHeader : false,
                      noneSelectedText : 'Select Interests',
                       selectedList:2   // selectedList shows the names of the selected interests!
                  });
  
                  // toggle students' starred status, depending on students current starred status                
                  // We have to use a <span></span> to hold the image, because we
                  // replace the contents of the span with the result of this POST.
                  // if we used only an image w/o a parent <span> it doesn't work
                  $(".star").click(function(){
                    $(this).load('${createLink(controller:"student",
                                            action:"toggleStar")}',
                        { 'starred' : $(this).children('img').attr('starred'), 'id' : $(this).attr("starId") });
                    });

                  $(".editNote").click(function() {
                      var noteId = $( this ).attr( 'editNoteId' );
                      var noteElement=$( "[noteId=" + noteId + "]");
                      var noteText = noteElement.text().trim();
                      
                      noteElement.empty().html(
                          '<input noteTextId="' + noteId + '" ' +
                          'value="' + noteText + '" />'
                      );
                      // Don't actually trigger the href
                      return false;
                      
                      
                  });
            });
            function updateInterests() {
                // Collect the labels of each of the selected Interests
                // and put them into the "Interests" section of the newStudentForm
                // from http://groups.google.com/group/jquery-en/browse_thread/thread/6bbc26e14a59526c
                var selectedInterestLabels = 
                    $('input[name=interestInCourse]:checked').map(function() {
                        return $(this).attr("courseName")
                }).get();
                $('#interestNames').val(selectedInterestLabels.join(", "))
            }
        </script>

        <title>${studentInstance ? "Student: " + studentInstance : "Contact:" + contactInstance}</title>
    </head>
    <body>
        <div id="wrapper">
        <div id="foo">
        </div>
            <div id="content">
                <div class="infowrap">
                    <div class="infobox">
                        <g:each var="stu" in="${contactInstance.students}">
                            <g:render template="/student/studentQuickView" model="[studentInstance:stu]" />
                        </g:each>
                    </div>
                    <div class="infobox margin-left">
                        <g:if test="${flash.message}">
                        <div class="message">${flash.message}</div>
                        </g:if>
                        <h3 class="reallynow">Contact Info:</h3>
                        <table>
                            <tbody>
                                <g:if test="${contactInstance.cannotReach}">
                                <tr>
                                    <td colspan="4" class="errors">Cannot Reach</td>
                                </tr>
                                </g:if>
                                <tr>
                                    <td>${contactInstance}</td>
                                    <td>${contactInstance.address1} 
                                        <br />
                                        <g:if test="${contactInstance.address2}">
                                        ${contactInstance.address2} 
                                        <br /></g:if>
                                        <g:if test="${contactInstance.city}">
                                        ${contactInstance.city},&#160;&#160;</g:if>
                                        <g:if test="${contactInstance.state}">
                                        ${contactInstance.state} 
                                        <br /></g:if>
                                        <g:if test="${contactInstance.zipCode}">
                                        ${contactInstance.zipCode}&#160;&#160;</g:if>
                                        <br /></td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td>${contactInstance.emailAddress}</td>
                                </tr>
                                <g:each var="phone" in="${contactInstance.phoneNumbers}">
                                <tr>
                                    <td>${phone.label}</td>
                                    <td>${phone.phoneNumber}</td>
                                </tr>
                                </g:each>
                                <g:if test="${contactInstance.comments}">
                                </g:if>
                            </tbody>
                        </table>
                    <div class="infobox">
                        <h3 class="reallynow">Notes:</h3><g:form id="${contactInstance.id}" controller="contact" action="addNote">
                        <table>
                            <tbody>
                                <comments:each bean="${contactInstance}">
                                    <g:render template="/common/showNote"
                                              model="[ noteInstance : comment ]"/>
                                </comments:each>
                                <tr>
                                    <td>
                                    <g:textField name="noteText" />
                                </td>
                                <td>
                                    <input type="submit" value="Add Note" />
                                    </td>
                                      </tr>
                            </tbody>
                        </table></g:form>
                    </div>
                    </div>
                </div>
            </div>
            <div id="sidebar">
                <g:render template="/student/studentMenu" model="[contactInstance:contactInstance]"/>
            </div>
            <div id="wrapper">
                <div id="content">
                    <div class="infowrap">
                        <g:render template='/contact/createStudent' 
                            model="[contactInstance:contactInstance, studentInstance : newStudentInstance]" />
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
