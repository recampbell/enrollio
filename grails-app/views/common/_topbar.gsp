<div class="ui-widget-content">
    <h1 class="enr-app-title">Enrollio</h1>

    <shiro:isLoggedIn>
        <span class="enr-welcome-message">
            Welcome, <shiro:principal/>! | <g:link controller="auth" action="signOut">Log Out</g:link>
        </span>
        <br />
        <div class="enr-main-menu">
            <shiro:hasRole name="Administrator">
                <g:link controller="admin" action="show" class="enr-top-menu-item"> Admin </g:link>&nbsp;|&nbsp;
            </shiro:hasRole>
            <g:link controller="program" action="list" 
                class="enr-top-menu-item">Programs </g:link>&nbsp;|&nbsp;

            <g:link controller="contact" action="list" 
                class="enr-top-menu-item">Contacts</g:link>&nbsp;|&nbsp;
            <g:link controller="classSession" action="list" 
                class="enr-top-menu-item">Class Sessions</g:link>
        </div>

        <div >
            <g:form url='[controller: "searchable", action: "index"]'
                    id="searchableForm" name="searchableForm" method="get">
                    <g:textField name="q" value="${params.q}" size="30"/>
                    <input type="submit" value="Search" />
    </g:form>  </div>
    </shiro:isLoggedIn>
</div>

