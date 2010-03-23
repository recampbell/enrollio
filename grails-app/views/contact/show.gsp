<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'comboCheckboxSelectbox.css')}" />
        <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js', file:'comboCheckboxSelectbox.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.core.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'ui.datepicker.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'date.js')}"></script>
	<script type="text/javascript" src="${resource(dir:'js', file:'jquery.qtip-1.0.0-rc3.min.js')}"></script>

        
        <title>Contact: ${contactInstance}</title>
        <script type="text/javascript">
            // apply qtip selector to the "Edit" link
            // that shows possible Interests that a new student has.
             $(document).ready(function(){
                $('#interestsSelector').qtip({
                   content: $('#newStudentInterests'),
                   hide: {
                      delay: 1000,
                      fixed: true
                   }
                });
            });
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                    <g:if test="${flash.message}">
                        <div class="message">${flash.message}</div>
                    </g:if>
                <div class="box">
                    <g:render template="contactSearchForm" />
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
                <g:render template='createStudent' model="[contactInstance:contactInstance]" />
            </div>
            <div id="sidebar">
                <g:render template="contactMenu" model="[contactInstance:contactInstance]"/>
            </div>
       </div>
    </body>
</html>
