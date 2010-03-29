<table>
<g:each var="con" in="${contactInstanceList}">
    <g:render template="interestedContact" model="[contactInstance : con ]" />
</g:each>
</table>
