<h3 class="reallynow">Search Students/Contacts</h3>
<g:form url='[controller: "contact", action: "list"]' id="contactSearchForm"
       name="searchableForm" method="get">
    <g:textField name="q" value="${params.q}" size="20" />
    <input id="contactSearchButton" type="submit" value="Search" />
</g:form>
