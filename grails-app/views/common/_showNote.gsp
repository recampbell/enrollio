<tr>
    <td>
        ${noteInstance.body}
    </td>
    <td>
        <g:if test="${noteInstance.lastUpdate == noteInstance.dateCreated}">
            Posted by ${noteInstance.poster} on
        </g:if>
        <g:else>
            Updated by ${noteInstance.poster} on
        </g:else>
        <enrollio:formatDate date="${noteInstance.lastUpdated}" />
    </td>
</tr>
