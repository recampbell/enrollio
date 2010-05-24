<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />    
        <title>Create Contact</title>         
        <g:javascript src="jquery-1.4.2.min.js" />
        <g:javascript src="jquery.maskedinput-1.2.2.min.js" />
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
                <h3 class="reallynow">New Student</h3>
                <p>
                All students need parent or contact info.  
                Please fill out Parent info, or search for an existing contact.
                </p>
            </div>
        <div class="infobox">
            <g:render template="/student/studentSearchForm" />
        </div>
        <div class="infobox">
            <h3 class="reallynow">New Parent/Contact</h3>
            <g:hasErrors bean="${contactInstance}">
                <div class="errors">
                    <g:renderErrors bean="${contactInstance}" as="list" />
                </div>
            </g:hasErrors>
            <g:form method="post" >
            <g:render template="editContact" model="[contactInstance:contactInstance]" />
                <div class="buttons">
                    <g:actionSubmit value="Create Contact" action="save" />
                </div>
            </g:form>
        </div>
        </div>
        </div>
    </body>
</html>
