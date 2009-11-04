<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Attendance</title>
    </head>
    <body>
        <shiro:hasRole name="Administrator">
            <h1 class="">Admin</h1>
            <h2>Users</h2>
            <ul class="navlist">
                <li>
                    <g:link controller="shiroUser" action="list">List Users</g:link>
                </li>
                <li>
                    <g:link controller="shiroUser" action="create">Create User</g:link>
                </li>
            </ul>
            <h2>Miscellaneous</h2>
            <ul class="navlist">
                <li>
                    <g:link controller="configSetting" action="list">Settings</g:link>
                </li>
                <li>
                    <g:link controller="configSetting" action="testDataRequest">Load Test
                    Data</g:link>
                </li>
            </ul>
        </shiro:hasRole>
    </body>
</html>
