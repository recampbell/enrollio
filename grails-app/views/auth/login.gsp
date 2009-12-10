<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Login</title>
    </head>
    <body>
            <g:render template="/help/index" model="['title':'Welcome!']" />
            <div id="sidebar">
                    <g:render template="loginForm"
                    model="[targetUri:targetUri, rememberMe:rememberMe, username:username]" />
            </div>
        </div>
    </body>
</html>
