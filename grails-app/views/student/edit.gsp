
<%@ page import="org.bworks.bworksdb.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <meta name="tabName" content="student" />
        <title>Edit Student</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="box">
                    <h3>Edit Student: ${studentInstance}</h3>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${studentInstance}">
            <div class="errors">
                <g:renderErrors bean="${studentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="update" name="editStudentForm" method="post" >
                <input type="hidden" name="id" value="${studentInstance?.id}" />
                <input type="hidden" name="version" value="${studentInstance?.version}" />
                <label for="firstName">First Name : </label> 
                <input type="text" id="firstName" 
                name="firstName" 
                value="${fieldValue(bean:studentInstance,field:'firstName')}"/><br />

                <label for="middleName">Middle Name : </label> 

                <input type="text" id="middleName" 
                name="middleName" 
                value="${fieldValue(bean:studentInstance,field:'middleName')}"/><br />

                <label for="lastName">Last Name : </label> 
                <input type="text" id="lastName" 
                name="lastName" 
                value="${fieldValue(bean:studentInstance,field:'lastName')}"/><br />

                <label for="birthDate">Birth Date : </label> 
                <g:set var="existingBday"
                       value="${fieldValue(bean:studentInstance, field:'birthDate')}" />

                <input type="text" id="newStudentBirthDate" 
                       name="birthDate" 
                       value="${formatDate(format:'MM/dd/yyyy', date:studentInstance?.birthDate)}"
                       />
                <br />
                <label for="grade">Grade : </label> 
                <input type="text" id="grade" 
                name="grade" 
                value="${fieldValue(bean:studentInstance,field:'grade')}"/><br />

                <label for="gender">Gender :</label>
                <g:select name="gender" from="${['Male', 'Female']}" /><br />

                <label for="contact">Contact:</label>
                <g:select optionKey="id" from="${org.bworks.bworksdb.Contact.list()}" name="contact.id" value="${studentInstance?.contact?.id}" ></g:select><br />
                        
                <label for="emailAddress">Email Address:</label>
                <input type="text" id="emailAddress" name="emailAddress" value="${fieldValue(bean:studentInstance,field:'emailAddress')}"/>

                <!-- only auto-select the default prog if we have a new student -->
                <fieldset id="studentInterests">
                    <legend>Interests</legend>
                    <g:interestCheckBoxes student="${studentInstance}"  />
                </fieldset>
                <div class="buttons">
                    <span class="button">
                        <input class="save" type="submit" value="Save" />
                    </span>
                </div>
            </g:form>
        </div>
        </div>
        </div><div id="sidebar">
            <g:render template="/common/sideMenu" />
        </div>
    </body>
</html>
