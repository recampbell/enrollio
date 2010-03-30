<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'jquery.multiselect.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'jquery-ui-1.8rc3.custom.css')}" />

	<script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.4.2.min.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'jquery.multiselect.min.js')}"></script>
        <script type="text/javascript">
             $(document).ready(function(){
                $("select.multiselect").multiSelect({
                    showHeader : false,
                    noneSelectedText : 'Select Interests',
                    selectedList:2   // selectedList shows the names of the selected interests!
                });

                $(".star").click(function(){
                    alert("boo");
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
                            </tbody>
                        </table>
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
