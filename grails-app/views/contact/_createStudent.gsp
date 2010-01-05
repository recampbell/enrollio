<%@ page import="org.bworks.bworksdb.Program;" %>
<div id="newStudentDiv" class="box" ${studentInstance ? '' : 'style="display:none"'}>
    <h3 id="adduser">New Student</h3>
    <g:hasErrors bean="${studentInstance}">
        <div class="errors">
            <g:renderErrors bean="${studentInstance}" as="list" />
        </div>
    </g:hasErrors>
    
    <g:form action="saveStudent" method="POST"
            name="newStudentForm">
        <label for="firstName">First Name : </label> 

        <input type="hidden" id="contact.id" 
        name="contact.id" 
        value="${contactInstance?.id}"/>

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

        <fieldset id="studentInterests">
            <legend>Interests</legend>
            <g:each var="p" in="${Program.list()}">
                <label for="program_${p.id}">
                    <input class="checkbox" 
                    id="program_${p.id}" 
                    name="program_${p.id}" 
                    type="checkbox" 
                    value="${p.id}" />${p.name}
                </label>
            </g:each>

        </fieldset>
        <label for="saveButton">&nbsp;</label>
        <g:submitButton class="save" name="saveButton" value="Save" />
                            or&nbsp;
        <a href="#" id="cancelSaveStudentLink" name="cancelSaveStudentLink" 
            class="cancelLink">Cancel</a>
    </g:form>
</div>
