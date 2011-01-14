<ul class="menuList">
    <li>
      <g:link class="module_edit" name="editCourseLink" controller="course" action="edit" id="${courseInstance.id}">Edit</g:link>
    </li>
            <li>
            <g:link class="waiting_list" action="interestedStudents" 
               controller="course" id="${courseInstance.id}">
                Waiting List
            </g:link></li>
        
            <!--
            <li>
            <g:link class="group_gear" name="manageCallListLink" 
                controller="course"
                action="manageCallList" id="${courseInstance.id}">Manage Waiting List</g:link>
            </li>
            <li>
                <g:link class="printer" name="printableCallListLink" 
                    controller="course"
                    action="printableCallList" 
                    id="${courseInstance.id}">Printable call list (preview)</g:link>
                
            </li>
            -->
<li>
                <g:link class="printer" name="printableCallListLink" 
                    controller="course"
                    action="printableCallList" 
                    id="${courseInstance.id}" 
                    params="[ pdf:true,
                              reservedForUser : reservedForUserId,
                              q : q ]">Call list (PDF)
                    
                    <g:if test="${reservedForUserId || q }">(filter)</g:if>
                    
                    
                    </g:link>
                
            </li>
        
    </li>
    <li>
            
                <g:link controller="classSession" 
                action="create" params="['course.id':courseInstance.id]" 
                class="calendar_add">New Session</g:link>
            
    </li>
</ul>
