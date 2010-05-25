<ul>
    <li>
        <h3>
            <g:link class="help" controller="help">Help</g:link>
        </h3>
        <ul>
            <li>
                <g:link class="grin" controller="help" action="thanks">Credits</g:link>
                <g:link class="whatsnew" controller="help" action="whatsnew">What's New</g:link>
            </li>
        </ul>
    </li>
    <shiro:isLoggedIn>
    <li>
        <h3>
            <g:link class="app_form" controller="userSetting" 
                   action="list">User Settings</g:link>
        </h3>
    </li>
    </shiro:isLoggedIn>
</ul>
