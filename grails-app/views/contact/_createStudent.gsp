<%@ page import="org.bworks.bworksdb.Course;" %>
<div id="newStudentDiv" class="box">
    <h3 id="adduser">New Student</h3>
    <g:hasErrors bean="${studentInstance}">
        <div class="errors">
            <g:renderErrors bean="${studentInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form action="saveStudent" method="POST" name="newStudentForm">
    <table width="100%">
        <thead>
            <tr>
                <th width="20%">First</th>
                <th width="15%">Middle</th>
                <th width="25%">Last</th>
                <th>
                    Interests
                </th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <input type="hidden" id="contact.id" name="contact.id"
            value="${contactInstance?.id}" />
            <tr class="dataEntry">
                <td>
                    <input  type="text" id="firstName" name="firstName"
                    value="${fieldValue(bean:studentInstance,field:'firstName')}" />
                </td>
                <td>
                    <input  type="text" id="middleName" name="middleName"
                    value="${fieldValue(bean:studentInstance,field:'middleName')}" />
                </td>
                <td>
                    <input  type="text" id="lastName" name="lastName"
                    value="${fieldValue(bean:studentInstance,field:'lastName')}" />
                </td>
                <td>
                    <td>
		<select name="example1" class="multiselect" multiple="multiple" size="5">
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
