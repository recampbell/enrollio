<div id="welcome">
<g:formatDate format="EEEE, MMMM d yyyy" date="${new Date()}"/>
</div>

<div id="menu">
        <shiro:isLoggedIn>
	Welcome, <shiro:principal/>! | <g:link controller="auth" action="signOut">Log Out</g:link>
        </shiro:isLoggedIn>
</div>
