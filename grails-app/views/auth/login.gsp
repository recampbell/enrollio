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
                                    <td>
                                        <input type="text" name="username"
                                        value="${username}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td>
                                        <input type="password" name="password"
                                        value="" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Remember me?:</td>
                                    <td>
                                        <g:checkBox name="rememberMe"
                                        value="${rememberMe}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td />
                                    <td>
                                        <input type="submit" value="Sign in" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </g:form>
                </div>
            </div>
            <div id="sidebar">
                <g:render template="/common/sideMenu" />
            </div>
        </div>
    </body>
</html>
