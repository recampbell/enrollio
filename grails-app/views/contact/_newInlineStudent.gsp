<g:render template="studentEdit" model="[student:student, idx:idx, availPrograms:availPrograms]" />

<tr>
<td>
<g:remoteLink action="cancelInlineStudent" update="newStudentDiv"
        params="['studentstudentidx':studentstudentidx]" onLoading="showSpinner();" on404="alert('Not found');">
  Cancel
</g:remoteLink>
</td>
</tr>
