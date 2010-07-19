<script type="text/javascript">
    $(document).ready(function() {
        $('.statusSwitcher').click(function() {
            $.post('${createLink(controller:"attendance",
                    action:"updateStatus")}',
                { 'status' : $(this).attr('attendanceStatus'),
                'id'     : $(this).attr('attendanceId') });
        });
        $('#selectAll').click(function() { 
            $(".statusSwitcher[value='present']").each(function() {
                $(this).click();
            });
        });
        $('#selectNone').click(function() { 
            $(".statusSwitcher[value='absent']").each(function() {
                $(this).click();
            });
        });
    });
</script>
<h3 class="reallynow">${lessonDateInstance}


    <a href="#" id="selectNone" class="delete">None</a>
    <a href="#" id="selectAll" class="tick">All</a>

</h3>
<table>
    <tbody>
        <g:each var="attendance" in="${lessonDateInstance.attendees}">
        <tr>
            <td width="40%">
                <g:link controller="student" action="show" 
                id="${attendance.student.id}">${attendance.student}</g:link>

            </td>
            <td width="60%">
                <g:radio class="statusSwitcher" 
                attendanceId="${attendance.id}" 
                attendanceStatus="present" 
                name="status${attendance.id}" 
                value="present" 
                checked="${attendance.status == 'present'}"/>&nbsp;Present
                <g:radio class="statusSwitcher" 
                attendanceId="${attendance.id}" attendanceStatus="absent" 
                name="status${attendance.id}" 
                value="absent" 
                checked="${attendance.status == 'absent'}" />&nbsp;Absent
                <g:radio class="statusSwitcher" 
                attendanceId="${attendance.id}" 
                attendanceStatus="late" 
                name="status${attendance.id}" 
                value="late" 
                checked="${attendance.status == 'late'}"/>&nbsp;Late
            </td>
        </tr>
        </g:each>
        <g:if test="${!lessonDateInstance.attendees}" /
        <tr>
            <td>
                No students are enrolled.
            </td>
        </tr>
        </g:if>
    </tbody>
</table>
