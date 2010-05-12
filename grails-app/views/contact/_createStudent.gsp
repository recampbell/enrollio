<%@ page import="org.bworks.bworksdb.Course;" %>
<div>
<h3 class="reallynow">Add Student</h3>
<g:hasErrors bean="${studentInstance}">
<div class="errors">
    <g:renderErrors bean="${studentInstance}" as="list" />
</div>
</g:hasErrors>
<g:form action="saveStudent" controller="contact" method="POST" name="newStudentForm">
<table width="100%">
    <thead>
        <tr>
            <th width="20%">First</th>
            <th width="10%">Middle</th>
            <th width="20%">Last</th>
            <th width="20%">Signup Date</th>
            <th width="20%">Interests</th>
            <th width="5%"></th> <!-- column for the 'save' button -->
        </tr>
    </thead>
    <tbody>
        <input type="hidden" id="contact.id" name="contact.id"
        value="${contactInstance?.id}" />
        <tr class="dataEntry">
            <td>
                <input class="squishy" type="text" id="firstName" name="firstName"
                value="${fieldValue(bean:studentInstance,field:'firstName')}" />
            </td>
            <td>
                <input class="squishy"   type="text" id="middleName" name="middleName"
                value="${fieldValue(bean:studentInstance,field:'middleName')}" />
            </td>
            <td>
                <input  class="squishy" type="text" id="lastName" name="lastName"
                value="${fieldValue(bean:studentInstance,field:'lastName')}" />
            </td>
            <td>
                <input class="hasDatePicker" type="text" id="signupDate" name="signupDate" 
                       value="${new Date().format('MM/dd/yyyy')}" />
            </td>
            <td>
                <select name="interestInCourse" class="multiselect" multiple="multiple">
                    <g:each var="course" in="${Course.list()}">
                    <option value="${course.id}">${course.name}</option>
                    </g:each>
                </select>
            </td>
            <td>
                <g:submitButton style="float:right" class="save" name="saveButton" value="Save" />
            </td>
        </tr>
    </tbody>
</table>
</g:form>
</div>
