
<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />    
        <title>Edit Contact</title>         
        <g:javascript src="jquery-1.4.2.min.js" />
        <g:javascript src="jquery.maskedinput-1.2.2.min.js" />
        <g:javascript src="enrollioContact" />
        <script type="text/javascript">
            $(document).ready(function() {
                    $(".phoneNumber").mask("(999) 999-9999")
            });
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="infowrap">
                <g:form action="update" name="editContactForm" method="post" >
                    <div class="infobox">
                        <h3 class="reallynow">Edit Contact</h3>
                        <g:hasErrors bean="${contactInstance}">
                        <div class="errors">
                            <g:renderErrors bean="${contactInstance}" as="list" />
                        </div>
                        </g:hasErrors>
                        <g:render template="editContact" 
                        model="[contactInstance:contactInstance, showDrop:true]"
                        showDrop="true"/>
                        <div class="buttons">
                            <g:actionSubmit class="save" value="Update" />
                        </div>
                    </div>
                    <div class="infobox margin-left">
                        <h3 class="reallynow">Phone Numbers</h3>
                        <enrollio:phoneNumberInput contactInstance="${contactInstance}" />
                    </div>
                </g:form>
                </div>
            </div>
                <div id="sidebar">
                <g:render template="/student/studentMenu" model="[contactInstance:contactInstance]"/>
            </div>
        </div>
    </body>
</html>
