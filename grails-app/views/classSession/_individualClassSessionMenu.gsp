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

        </ul>
        <ul>
            <h3>Print</h3>
            <li>

                <script type="text/javascript">
                    // The form below is submitted by this javascript
                    // the browser will stay on the same page
                    function submit_welcomeLetter(link) {
                        link.parentNode.submit(); 
                        return false;
                    }
                </script>
                <g:form name="callList" class="jasperReport" action="printWelcomeLetter">
                    <input type="hidden" name="_format" value="PDF" />
                    <!-- _name is the title shown on top of the "Save As" popup -->
                    <input type="hidden" name="_name" value="WelcomeLetters" />
                    <input type="hidden" name="_file" value="welcomeLetter" />
                    <input type="hidden" name="id" value="${classSessionInstance.id}" />
                    <a href="#" name="callListLink" class="welcome_letter" title="PDF"
                        onClick="return submit_welcomeLetter(this)">&#160;&#160;Welcome Letters</a>
                </g:form>
            </li>
            
            <li>
                <g:link name='attendanceSheetLink' class="application_list" action="attendanceSheet"
                id="${classSessionInstance.id}">&#160;Attendance Sheet</g:link>
            </li>
        </ul>

        </li>
    </ul>
