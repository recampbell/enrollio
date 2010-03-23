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
                <th width="20%">Middle</th>
                <th width="30%">Last</th>
                <th>
                    Interests
                    <a href="#" id="interestsSelector">(edit)</a>
                </th>
            </tr>
        </thead>
        <tbody>
            <input type="hidden" id="contact.id" name="contact.id"
            value="${contactInstance?.id}" />
            <tr>
                <td>
                    <input class="foo" type="text" id="firstName" name="firstName"
                    value="${fieldValue(bean:studentInstance,field:'firstName')}" />
                </td>
                <td>
                    <input class="foo" type="text" id="middleName" name="middleName"
                    value="${fieldValue(bean:studentInstance,field:'middleName')}" />
                </td>
                <td>
                    <input class="foo" type="text" id="lastName" name="lastName"
                    value="${fieldValue(bean:studentInstance,field:'lastName')}" />
                </td>
                <td id="interestNames">

                
                </td>
            </tr>
            <div style="display:none" id="newStudentInterests">
                <g:each var="c" in="${Course.list()}">
                    <label for="interestInCourse_${c.id}">${c.name}</label>
                    <input type="checkbox" name="interestInCourse" 
                    id="interestInCourse_${c.id}"
                    class="checkbox" onClick="updateInterests();" id="interestInCourse_${c.id}" value="${c.id}" />
                </g:each>
            </div>
        </tbody>
    </table>
    <g:submitButton class="save" name="saveButton" value="Save" />or&#160; 
    <a href="#" id="cancelSaveStudentLink" name="cancelSaveStudentLink"
    class="cancelLink">Clear</a></g:form>
</div>
