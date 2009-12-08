<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Login</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>Log In</span>
                        <br />
                    </h3>
                    <g:form action="signIn">
                    <input type="hidden" name="targetUri" value="${targetUri}" />
                    <table>
                        <thead></thead>
                        <tbody>
                            <tr>
                                <td>Username:</td>
                                <td><input type="text" name="username" value="${username}" /></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="password" name="password" value="" /></td>
                            </tr>
                            <tr>
                                <td>Remember me?:</td>
                                <td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
                            </tr>
                            <tr>
                                <td />
                                    <td><input type="submit" value="Sign in" /></td>
                                </tr>
                            </tbody>
                        </table>
                        </g:form>
                    </div>
                </div>
                <div id="sidebar">
                    <g:render template="/common/sideMenu" />
                </div>
                <g:render template="/help/index" model="['title':'Welcome!']" />

            </div>
        </body>
    </html>
