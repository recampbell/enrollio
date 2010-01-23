<ul>
    <li>
        <h3>
            <g:link class="wrench" controller="admin">Admin</g:link>
        </h3>
        <h3>
            <a href="#" class="user">Users</a>
        </h3>
        <ul>
            <li>
                <g:link class="group" controller="shiroUser" action="list">List
                Users</g:link>
            </li>
            <li>
                <g:link class="useradd" controller="shiroUser" action="create">Create
                User</g:link>
            </li>
        </ul>
    </li>
    <li>
        <h3>
            <a href="#" class="manage">Misc</a>
        </h3>
        <ul class="navlist">
            <li>
                <g:link class="invoices" controller="configSetting" action="list">
                Settings</g:link>
            </li>
        </ul>
    </li>
</ul>
