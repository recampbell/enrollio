<tr>
    <td>
        ${noteInstance.body}
    </td>
    <td>
        <g:if test="${noteInstance.lastUpdated == noteInstance.dateCreated}">
            Posted by ${noteInstance.poster} on
        </g:if>
        <g:else>
            Updated by ${noteInstance.poster} on
        </g:else>
        <enrollio:formatDate date="${noteInstance.lastUpdated}" />
        <a href="" class="editNote">Edit</a>&nbsp;
        <a href="" class="saveNote" style="display:none">Save</a>&nbsp;
        <a href="" class="cancelNote" style="display:none">Cancel</a>
    </td>
</tr>
