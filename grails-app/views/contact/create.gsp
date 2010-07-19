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
                 $('#signupDate').datepicker({changeYear:true});
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
                            <enrollio:phoneNumberInput 
                            phoneNumberList="${contactInstance.phoneNumbers}" />
                    </div>
                    <div class="infobox margin-left">
                        <h3 class="reallynow">Student Info</h3>
                        <p> You can add more students after saving the Contact.</p>
                        <table width="100%">
                            <tbody>
                                <tr class="prop">
                                    <td><label for="student.firstName">First Name : </label> </td>
                                    <td>
                                        <input type="text" id="firstName" 
                                        name="student.firstName" 
                                        value="${fieldValue(bean:studentInstance,field:'firstName')}"/><br />
                                    </td> 
                                </tr>
                                <tr class="prop">
                                    <td><label for="student.middleName">Middle Name: </label> </td>
                                    <td>
                                        <input type="text" id="middleName" 
                                        name="student.middleName" 
                                        value="${fieldValue(bean:studentInstance,field:'middleName')}"/><br />
                                    </td> 
                                </tr>

                                <tr class="prop">
                                    <td><label for="student.lastName">Last Name: </label> </td>
                                    <td>
                                        <input type="text" id="lastName" 
                                        name="student.lastName" 
                                        value="${fieldValue(bean:studentInstance,field:'lastName')}"/><br />
                                    </td> 
                                </tr>
                                <tr>
                                    <td><label for="student.signupDate">Signup Date</label></td>
                                    <td>
                                        <input class="hasDatePicker" type="text" 
                                        id="student.signupDate" 
                                        name="student.signupDate" 
                                        value="${new Date().format('MM/dd/yyyy')}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="lastName">Interests</label> </td>
                                    <td>
                                        <enrollio:courseDropDown studentInstance="${studentInstance}"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
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
