<html>
    <head>
        <title>Welcome to Bworksdb</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div id="wrapper">
            <div id="content">&#160;</div>
            <div id="sidebar">
                <g:render template="/common/sideMenu" />
                
            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
