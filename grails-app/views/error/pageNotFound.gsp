<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'style.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme1.css')}" />
        <title>Page Not Found</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <h2 style="display:inline">Page Not Found</h2>
                <g:mascotIcon style="vertical-align:middle" />
                <div id="topmenu">
                    <ul>
                        <li class="enr-top-menu-item">
                            <g:link controller="program" action="list">Courses</g:link>
                        </li>
                        <li class="enr-top-menu-item">
                            <g:link controller="classSession" action="list"
                            class="enr-top-menu-item">Class Sessions</g:link>
                        </li>
                        <li class="enr-top-menu-item">
                            <g:link controller="contact" action="list">Contacts</g:link>
                        </li>
                        <li class="enr-top-menu-item">
                            <g:link controller="student" action="list"
                            class="enr-top-menu-item">Students</g:link>
                        </li>
                        <li class="enr-top-menu-item">
                            <g:link controller="help" action="index"
                            class="enr-top-menu-item">Help</g:link>
                        </li>
                            <li class="logintab">
                                <g:link controller="auth" action="login">Login</g:link>
                            </li>
                    </ul>
                </div>
            </div>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>Page Not Found</span>
                    
                        <br />
                    </h3>
                    <p class="youhave">Enrollio would really like you to graduate, but can't find the page you're looking for.</p>
                    <p class="youhave">You might try the <g:link name="helpLink" controller="help">Help Page</g:link>, or</p>
                    <p class="youhave">Send an e-mail to <a href="mailto:nathan.neff@gmail.com" >nathan.neff@gmail.com</a> for help</p>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
