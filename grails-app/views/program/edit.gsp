
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
                <div id="box">
                        <h3 id="adduser">Edit Program:</h3>
                    <form id="form" action="..." method="post">
                      <fieldset id="personal">
                          <legend>${programInstance}</legend>
                        <label for="name">Name : </label> 
                        <input name="name" id="name" type="text" tabindex="1" 
                        value="${fieldValue(bean:programInstance,field:'name')}"/>
                        <br />
                        <label for="description">Description : </label>
                        <g:textArea name="description" 
                        value="${fieldValue(bean:programInstance,field:'description')}"
                        rows="5" cols="40"/>
                        <br />
                      </fieldset>
                      <div align="center">
                      <input id="button1" type="submit" value="Save" /> 
                      <input id="button2" type="reset" value="Cancel" />
                      </div>
                    </form>
                </div >


                </div>

            <h1>Edit Program</h1>
            <g:hasErrors bean="${programInstance}">
            <div class="errors">
                <g:renderErrors bean="${programInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${programInstance?.id}" />
                <input type="hidden" name="version" value="${programInstance?.version}" />
                <div class="dialog">
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
