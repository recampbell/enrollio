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

        <fieldset id="studentInterests">
            <legend>Interests</legend>
            <g:each var="p" in="${Program.list()}">
                <label for="interestInProgram_${p.id}">
                    <input class="checkbox" 
                    id="interestInProgram_${p.id}" 
                    name="interestInProgram" 
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
