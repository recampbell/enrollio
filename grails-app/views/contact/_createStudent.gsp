<g:hasErrors bean="${programInstance}">
    <div class="errors">
        <g:renderErrors bean="${student}" as="list" />
    </div>
</g:hasErrors>
<div id="box">
    <h3 id="adduser">New Student</h3>
    <g:form action="addStudent" name="newStudentForm" method="post">
        <label for="firstName">First Name : </label> 

        <input type="text" id="firstName" 
        name="firstName" 
        value="${fieldValue(bean:student,field:'firstName')}"/><br />

        <label for="middleName">Middle Name : </label> 

        <input type="text" id="middleName" 
        name="middleName" 
        value="${fieldValue(bean:student,field:'middleName')}"/><br />

        <label for="lastName">Last Name : </label> 
        <input type="text" id="lastName" 
        name="lastName" 
        value="${fieldValue(bean:student,field:'lastName')}"/><br />

        <label for="birthDate">Birth Date : </label> 
        <input type="text" id="birthDate" 
        name="birthDate" 
        value="${fieldValue(bean:student,field:'birthDate')}"/><br />

        <label for="grade">Grade : </label> 
        <input type="text" id="grade" 
        name="grade" 
        value="${fieldValue(bean:student,field:'grade')}"/><br />

        <label for="gender">Gender :</label>
        <g:select name="gender" from="${['Male', 'Female']}" /><br />

        <label for="interests">Interests :</label>
                   <label><input class="checkbox" id="red"
                       name="red" type="checkbox"
                       value="red" />Foo</label><br />
                   <label><input class="checkbox" id="red"
                       name="red" type="checkbox"
                       value="red" />Foo</label><br /
                   <label><input class="checkbox" id="blue"
                       name="blue" type="checkbox"
                       value="blue" />Bar</label><br /
        <label style="clear:left;" for="saveButton"></label>
        <g:submitButton class="save" name="saveButton" value="Save" /></p>
                            or&nbsp;
        <g:link url="http://yahoo.com" name="cancelLink" class="cancelLink">Cancel</g:link>

    </g:form>
</div>
