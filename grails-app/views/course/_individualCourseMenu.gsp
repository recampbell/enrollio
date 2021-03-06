<ul>
    <li>
        <h3>
            <g:link class="module" controller="course" 
                   action="show"
                   id="${courseInstance.id}">${courseInstance}
            </g:link>
        </h3>
        <ul>
            <li>
                <g:link class="module_edit" name="editCourseLink" controller="course"
                action="edit" id="${courseInstance.id}">Edit</g:link>
            </li>
        </ul>
        <h3>
            <g:link class="waiting_list" action="interestedStudents" 
               controller="course" id="${courseInstance.id}">
                Waiting List
            </g:link>
        </h3>
        <ul>
            <li>
            <g:link class="waiting_list" action="interestedStudents" 
               controller="course" id="${courseInstance.id}">
                Waiting List
            </g:link>
            </li>
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
        </ul>
    </li>
    <li>
        <h3>
            <a href="" class="calendar">Sessions</a>
        </h3>
            <ul>
                <li>
                <g:link controller="classSession" 
                action="create" params="['course.id':courseInstance.id]" 
                class="calendar_add">New Session</g:link>
                </li>
            </ul>
    </li>
    <li>
        <h3>
            <g:link class="book_open" controller="course" action="lessons"
            id="${courseInstance.id}">Lessons</g:link>
        </h3>
        <ul>
            <g:if test="${courseInstance.lessons}">
                <li>
                    <g:link class="number_list" 
                            name="sortLessonsLink" 
                            action="sortLessons" 
                            controller="course" 
                            params="[ 'id' : courseInstance.id ]">Sort Lessons</g:link>
                </li>
            </g:if>
            <li>
                <g:link class="book_next" name="newLessonLink" action="create" controller="lesson" 
                                        params="[ 'course.id' : courseInstance.id ]">New Lesson</g:link>
            </li>
            <g:if test="${lessonInstance}">
                <li>
                    <g:link class="book_edit" controller="lesson" action="edit"
                    id="${lessonInstance.id}">Edit Lesson</g:link>
                </li>
            </g:if>
        </ul>
    </li>
</ul>
