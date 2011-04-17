<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />    
        <title>Create Contact</title>         
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'jquery.multiselect.css')}" />
        <g:javascript src="jquery-1.4.2.min.js" />
        <g:javascript src="jquery.maskedinput-1.2.2.min.js" />
        <g:javascript src="jquery.multiselect.min.js" />
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.datepicker.js')}"></script>
        <g:javascript src="enrollioContact" />
        <script type="text/javascript">
            
             $(document).ready(function(){
                  $("select.multiselect").multiSelect({
                      showHeader : false,
                      noneSelectedText : 'Select Interests',
                       selectedList:2   // selectedList shows the names of the selected interests!
                  });
                  // Show date picker for the "Signup Date" that shown on the
                  // new student form.  (It's rendered in the createStudent.gsp template below)
                 $('#studentSignupDate').datepicker({changeYear:true});
                 $('#newStudentOption').change(function() {
                     if($(this).attr('checked')) {
                         $('#newStudentForm').show('slow');
                     }
                     else {
                         $('#newStudentForm').hide('slow');
                         }
                 });
             });
        
        </script>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function() {
                    $(".phoneNumber").mask("(999) 999-9999")
            });
        </script>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <g:hasErrors bean="${contactInstance}">
                            <div class="errors">
                                <g:renderErrors bean="${contactIntance}" as="list" />
                            </div>
                        </g:hasErrors>
                        <g:hasErrors bean="${studentInstance}">
                            <div class="errors">
                                <g:renderErrors bean="${studentInstance}" as="list" />
                            </div>
                        </g:hasErrors>
                        <span>New Contact</span>
                        <br />
                    </h3>
                    <p class="youhave">All students need parent or contact info.  </p>
                    <p>Please fill out Parent info, or search for an existing contact.</p>
                </div>
                    
                    
                <div class="infowrap">
                <g:form method="post" >
                    <div class="infobox">
                        <h3 class="reallynow">Contact Info </h3>
                        <g:render template="editContact" model="[contactInstance:contactInstance]" />                      <br />
                    </div>
                    <div class="infobox margin-left">
                            <h3 class="reallynow">Phone Numbers</h3>
                            <enrollio:phoneNumberInput contactInstance="${contactInstance}" />
                    </div>
                    <g:actionSubmit value="Create Contact" action="save" />
                </g:form>
                </div>
        </div>
            <div id="sidebar">
                <g:render template="/student/studentMenu" />
            </div>
    </body>
</html>
