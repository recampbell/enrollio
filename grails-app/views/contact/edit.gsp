
<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="contact" />    
        <title>Edit Contact</title>         
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">Edit Contact</h3>
                    <g:hasErrors bean="${contactInstance}">
                    <div class="errors">
                        <g:renderErrors bean="${contactInstance}" as="list" />
                    </div>
                    </g:hasErrors>
                    <g:form action="update" name="editContactForm" method="post" >
                    <g:render template="editContact" 
                    model="[contactInstance:contactInstance, showDrop:true]"
                    showDrop="true"/>
                    <div class="buttons">
                        <g:actionSubmit class="save" value="Update" />
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
