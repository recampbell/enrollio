<%@ page import="org.bworks.bworksdb.Course;" %>
<div id="newStudentDiv" class="box">
    <h3 id="adduser">New Student</h3>
    <g:hasErrors bean="${studentInstance}">
        <div class="errors">
            <g:renderErrors bean="${studentInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form action="saveStudent" method="POST"
            name="newStudentForm">
<table width="100%">

    <thead>
        <tr>
            <th width="20%">First</th>
            <th width="20%">Middle</th>
            <th width="30%">Last</th>
            <th>
                <a href="#" id="interestsSelector" >Interests</a>
            </th>
        </tr>
    </thead>



    <tbody>

        <input type="hidden" id="contact.id" 
        name="contact.id" 
        value="${contactInstance?.id}"/>
        <tr>
             <td>
            <input class="foo" type="text" id="firstName" name="firstName" value="${fieldValue(bean:studentInstance,field:'firstName')}"/>
        </td>
             <td>
            <input class="foo"  type="text" id="middleName" name="middleName" value="${fieldValue(bean:studentInstance,field:'middleName')}"/>
        </td>
             <td>
            <input class="foo" type="text" id="lastName" name="lastName" value="${fieldValue(bean:studentInstance,field:'lastName')}"/>
        </td>
            


        <td>

        </td>



        </tr>
    </tbody>
        <g:submitButton class="save" name="saveButton" value="Save" />
                            or&nbsp;
        <a href="#" id="cancelSaveStudentLink" name="cancelSaveStudentLink" 
            class="cancelLink">Cancel</a>
        <div style="display:none" id="tableplug">
        

                <g:each var="p" in="${Course.list()}">

			
                
                
                
                <label for="interestInCourse_${p.id}">  ${p.name}
					</label>
						
		 <input type="checkbox" name="interestInCourse" id="bad" value="bad" class="checkbox"
                 id="interestInCourse_${p.id}" 
                 
                    value="${p.id}" />  
                 
						
                
                </g:each>

        
        </div>
    </g:form>
</table>














    
<!--
        <fieldset id="studentInterests">
            <legend>Interests</legend>
            <g:each var="p" in="${Course.list()}">
                <label for="interestInCourse_${p.id}">
                    <input class="checkbox" 
                    id="interestInCourse_${p.id}" 
                    name="interestInCourse" 
                    type="checkbox" 
                    value="${p.id}" />${p.name}
                </label>
            </g:each>

        </fieldset>
        <label for="saveButton">&nbsp;</label>
            -->

</div>
