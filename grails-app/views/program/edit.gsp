<%@ page import="org.bworks.bworksdb.Program" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="program" />
        <title>Edit Program: ${programInstance}</title>
    </head>
    <body>
         <div id="wrapper">
            <div id="content">
                <g:hasErrors bean="${programInstance}">
                    <div class="errors">
                        <g:renderErrors bean="${programInstance}" as="list" />
                    </div>
                </g:hasErrors>
                <div id="box">
                    <h3 id="adduser">Edit Program: ${programInstance}</h3>
                    <g:form method="post">
                        <label for="name">Name : </label> 
                        <input name="id" id="id" type="hidden" 
                            value="${programInstance.id}" />
                        <input type="hidden" name="version" 
                            value="${programInstance?.version}" />
                        <input name="name" id="name" type="text" tabindex="1" 
                            value="${fieldValue(bean:programInstance,field:'name')}"/>
                        <br />
                        <label for="description">Description : </label>
                        <g:textArea name="description" 
                            value="${fieldValue(bean:programInstance,field:'description')}"
                            rows="5" cols="40"/>
                        <br />
                        <!-- TODO the label below is a hack to get the 
                            save button to align w/the description and name -->
                        <label for="saveButton"></label>
                        <g:actionSubmit name="saveButton" class="save" value="Save" />
                            or&nbsp;
                        <g:link name="cancelLink" class="cancelLink" action="show" id="${programInstance.id}" >Cancel</g:link>
                    </g:form>
                </div>
            </div>
        </div>
    </body>
</html>
