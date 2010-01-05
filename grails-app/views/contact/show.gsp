<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />
        <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}"></script>
        <title>Contact: ${contactInstance}</title>
        <script type="text/javascript">
            $(document).ready(function(){
                // When user clicks the "Add Student" button
                // load the createStudentDiv (located in the studentList template)
                // with the goodies that we get from the createStudent action
                var createUrl = "${createLink(action:'createStudent')}";
                $('#createStudentLink').click(function() {
                    $.get(createUrl, { 'id' : ${contactInstance.id} }, function(data) {
                            $('#createStudentDiv').hide().html(data).slideDown('slow');
                    });
                });

                // When user clicks the "Save" button,
                // Reload the 'studentListDiv' in the parent page
                // with the goodies that we get from this form
                // Commenting out, because the POST doesn't work w/Ajax
                // $('#newStudentForm').live('submit', function() {
                //     var saveUrl = "${createLink(controller:'contact', action:'saveStudent')}";
                //     $.post(saveUrl, $(this).serialize(), function(data) { 
                //         $('#studentListDiv').hide().html(data);
                //     });
                // });
           });
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="box">
                    <g:render template="contactSearchForm" />
                    <g:if test="${flash.message}">
                        <div class="message">${flash.message}</div>
                    </g:if>
                </div>
                <br />
                <div class="box">
                    <h3>Contact: ${contactInstance}</h3>
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
            </div>
            <div id="sidebar">
                <g:render template="contactMenu" model="[contactInstance:contactInstance]"/>
            </div>
       </div>
    </body>
</html>
