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
                    showHeader : false
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

        <title>Contact: ${contactInstance}</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <g:if test="${flash.message}">
                        <div class="message">${flash.message}</div>
                    </g:if>
                    <h3 class="reallynow">Contact: ${contactInstance}</h3>
                    <table>
                        <tbody>
                            <tr>
                                <td>Address:</td>
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
                    <br />
                </div>
                <g:render template='studentList' model="[contactInstance:contactInstance]" />
                <g:render template='createStudent' model="[contactInstance:contactInstance, studentInstance : studentInstance]" />
            </div>
            <div id="sidebar">
                <g:render template="contactMenu" model="[contactInstance:contactInstance]"/>
            </div>
       </div>
    </body>
</html>
