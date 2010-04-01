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





                    
            <li>
                <g:link name='gradCertsLink' class="certificate" 
                action="certificates" id="${ classSessionInstance.id }"
                controller="classSession">&nbsp;&nbsp;Certificates</g:link>
            </li>

        </ul>
        <ul>
            <h3>Print</h3>
            <li>
                <script type="text/javascript">
                    $(document).ready(function(){
                            $('#welcomeLetterLink').click(function() {
                                $.post('${createLink(action:"printWelcomeLetter", controller:"classSession")}',
                                    $('#welcomeLetterForm').serialize());
                                });
                                
                    });
                </script>

                 <g:form name="welcomeLetterForm" class="jasperReport"
                     action="welcomeLetter">
                     <input type="hidden" name="_format" value="PDF" />
                     <!-- Name shown on top of PDF report -->
                     <input type="hidden" name="_name" value="Welcome to Bworks!" />
                     <input type="hidden" name="_file" value="welcomeLetter" />
                     <input type="hidden" name="id" value="${classSessionInstance.id}" />
                     <!-- TODO The &nbsp; is a kludge find CSS way to justify image
                                         and text so it looks o.k. -->
                 </g:form>

            
            
            <a href id="welcomeLetterLink" class="welcome_letter" action="#" name="welcomeLetterLink">Welcome Letters</a></li>
            <li>
            <g:link name='attendanceSheetLink' class="application_list" action="attendanceSheet"
            id="${classSessionInstance.id}">&#160;Attendance Sheet</g:link>
            </li>
        </ul>

        </ul>
        </li>
    </ul>
