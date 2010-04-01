<ul>
    <li>
        <h3>
            <g:link class="calendar" controller="classSession" action="show" id="$classSessionInstance.id">
            ${classSessionInstance.name}
            </g:link>
            
        </h3>
        <ul>
            <li>
                <g:link name="editEnrollmentsLink" class="groupadd"
                controller="course"
                action="interestedStudents" id="${classSessionInstance.course.id}"
                params="[ classSessionId :classSessionInstance.id ]">&nbsp;&nbsp;Enroll</g:link>
            </li>
            <li>
                <g:link name='gradCertsLink' class="attendance" 
                action="attendance" id="${ classSessionInstance.id }"
                controller="classSession">&nbsp;&nbsp;Attendance</g:link>
            </li>
            <!-- TODO Refactor this into a taglib or template -->
            <li>
                <g:link name='graduationLink' class="graduation_cap" action="graduation"
                id="${ classSessionInstance.id }" controller="classSession">&nbsp;&nbsp;Graduation</g:link>
            </li>
            <li>
            <script type="text/javascript">
                $(document).ready(function(){
                });
            </script>
                    
            <li>
                <g:link name='gradCertsLink' class="certificate" 
                action="certificates" id="${ classSessionInstance.id }"
                controller="classSession">&nbsp;&nbsp;Certificates</g:link>
            </li>

        </ul>
        <ul>
            <h3>Print</h3>
            <li><a href class="welcome_letter" action="#" name="welcomeLetterLink">Welcome Letters</a></li>
            <li>
            <g:link name='attendanceSheetLink' class="application_list" action="attendanceSheet"
            id="${classSessionInstance.id}">&#160;Attendance Sheet</g:link>
            </li>
        </ul>

        </ul>
        </li>
    </ul>
