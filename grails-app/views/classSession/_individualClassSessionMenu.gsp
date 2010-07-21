<ul>
    <li>
        <h3>
            <g:link class="module" controller="course" 
                   action="show"
                   id="${classSessionInstance.course.id}">${classSessionInstance.course}
            </g:link>
        </h3>
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
                params="[ classSessionId :classSessionInstance.id ]">&nbsp;&nbsp;Enroll / Call List</g:link>
            </li>
            <li>
                <g:link name='attendanceLink' class="attendance" 
                action="attendance" id="${ classSessionInstance.id }"
                controller="classSession">&nbsp;&nbsp;Attendance</g:link>
            </li>
            <!-- TODO Refactor this into a taglib or template -->
            <li>
                <g:link name='graduationLink' class="graduation_cap" action="graduation"
                id="${ classSessionInstance.id }" controller="classSession">&nbsp;&nbsp;Graduation</g:link>
            </li>
            <li>
                <g:link name='certificatesLink' class="certificate" 
                action="certificates" id="${ classSessionInstance.id }"
                controller="classSession">&nbsp;&nbsp;Certificates</g:link>
            </li>
            <li>
            <g:link name='editClassSessionLink' class="calendar_edit" 
                action="edit" controller="classSession" id="${ classSessionInstance.id }">&nbsp;&nbsp;Edit</g:link>
            </li>
        </ul>
            <h3 class="printer"><a href="#" class="printer">Print</a></h3>
        <ul>
            <li>
            <g:link name='attendanceSheetLink' class="application_list" 
            action="attendanceSheet" controller="classSession"
                id="${classSessionInstance.id}">&#160;Attendance Sheet</g:link>
            </li>
            <li>

                <g:link name='welcomeLettersLink' 
                class="welcome_letter" 
                controller="classSession"
                action="welcomeLetters"
                id="${classSessionInstance.id}">&#160;Welcome Letters</g:link>
            </li>
            <li>
                <g:link name='welcomeLettersLink' 
                class="email" 
                controller="classSession"
                action="envelopes"
                id="${classSessionInstance.id}">&#160;Envelopes (CSV)</g:link>
            
            </li>
            
        </ul>

        </li>
    </ul>
