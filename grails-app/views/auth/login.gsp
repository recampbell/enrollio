<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="login" />
        <title>Login</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <g:if test="${flash.message}">
                    <div class="errors">${flash.message}</div>
                </g:if>
                <div class="rightnow">
                        <h3 class="reallynow"><span>Log In</span><br /></h3>
                        <div class="youhave">
                    <g:form name="loginForm" class="loginForm" action="signIn">
                        <input type="hidden" name="targetUri" value="${targetUri}" />
                            <div>
                                <label for="username">Username:</label>
                                <input type="text" name="username"
                                value="${username}" />
                            </div>
                            <div>
                                <label for="password">Password:</label>
                                <input type="password" name="password"
                                value="" />
                            </div>
                            <div>
                                <label for="rememberMe">Remember me?:</label>
                                <g:checkBox name="rememberMe" value="${rememberMe}" />
                            </div>
                            <div>
                                <input id="login" type="submit" value="Sign in" />
                            </div>
                    </g:form>
                        </div>
                </div>
            </div>
            <shiro:isLoggedIn>
                <div id="sidebar"><g:render template="/common/sideMenu"/></div>
            </shiro:isLoggedIn>
        </div>
    </body>
</html>
