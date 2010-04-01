<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="course" />
        <title>Oops!</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>Oops!</span>
                    
                        <br />
                    </h3>
                    <p class="youhave">You're here because we don't know where to send you.</p>
                    <p class="youhave">Hopefully, there's an error message above that describes the problem.</p>
                </div>
            </div>
        </div>
    </body>
</html>
