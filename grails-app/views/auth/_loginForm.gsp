<!-- Params: targetUri
             username
             rememerMe -->
<div class="rightnow">
    <h3 class="reallynow">
        <span>Log In</span>
        <br />
    </h3>
    <g:form controller="auth" action="signIn">
        <input type="hidden" name="targetUri" value="${targetUri}" />
        <label for="username">Username:</label>
        <input type="text" name="username" value="${username}" />
        <label for="username">Password:</label>
        <input type="password" name="password" value="" />
        <label for="remember">Remember me?:</label>
        <g:checkBox name="rememberMe" value="${rememberMe}" />
        <input type="submit" value="Sign in" />
    </g:form>
</div>
