<script type="text/javascript">function submit_callList(link) {
    link.parentNode._format.value = link.title; link.parentNode.submit();
    return false; }
</script>
<g:form name="callList" action="callList" controller="course">
    <input type="hidden" name="_format" value="PDF" />
    <!-- Name shown on top of PDF report -->
    <input type="hidden" name="_name" value="Call List" />
    <input type="hidden" name="_file" value="callList" />
    <input type="hidden" name="id" value="${courseInstance.id}" />
    <!-- TODO The &nbsp; is a kludge find CSS way to justify image
    and text so it looks o.k. -->
    <a href="#" name="callListLink" class="printer" title="PDF"
        onClick="return submit_callList(this)">&#160;Printable (PDF)</a>
</g:form>
