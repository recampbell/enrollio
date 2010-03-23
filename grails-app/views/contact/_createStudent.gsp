<%@ page import="org.bworks.bworksdb.Course;" %>
<div id="newStudentDiv" class="box">
    <h3 id="adduser">New Student</h3>
    <g:hasErrors bean="${studentInstance}">
        <div class="errors">
            <g:renderErrors bean="${studentInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form action="saveStudent" method="POST" name="newStudentForm">
            <div style="display:none" id="newStudentInterests">
                <g:each var="c" in="${Course.list()}">
                <div class="col">
                
                <label for="interestInCourse_${c.id}">
                        
                    <input class="checkbox" type="checkbox" name="interestInCourse" 
                    courseName="${c.name}"
                    id="interestInCourse_${c.id}"
                    class="checkbox" onClick="updateInterests();" 
                    value="${c.id}" />
                    ${c.name}
                </label>
                </div>
                </g:each>
                
            </div>
    <table width="100%">
        <thead>
            <tr>
                <th width="20%">First</th>
                <th width="15%">Middle</th>
                <th width="25%">Last</th>
                <th>
                    Interests
                    <a href="#" id="interestsSelector">(edit)</a>
                </th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <input type="hidden" id="contact.id" name="contact.id"
            value="${contactInstance?.id}" />
            <tr class="dataEntry">
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
                <td>
                    <input readonly="readonly" id="interestNames" />
                </td>
                <td>
                
    <g:submitButton style="float:right" class="save" name="saveButton" value="Save" />
                </td>
            </tr>
            
            
        </tbody>
    </table>
    </g:form>
</div>
