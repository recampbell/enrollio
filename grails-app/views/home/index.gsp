<html>
    <head>
        <title>Welcome to Bworksdb</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <li>
                        <a href="#" class="report">Sales Report</a>
                    </li>
                    <li>
                        <a href="#" class="report_seo">SEO Report</a>
                    </li>
                    <li>
                        <a href="#" class="search">Search</a>
                    </li>
                    <li>
                        <a href="#" class="feed">RSS Feed</a>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrapper">
            <div id="content">&#160;</div>
            <div id="sidebar">
                <g:render template="/common/sideMenu" />
                
            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
