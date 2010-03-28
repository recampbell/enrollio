<%@ page import="org.bworks.bworksdb.ConfigSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Import File Data</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">Load Data</h3>
                </div>
                <div class="infobox">
                    <p><b>Important</b>This will add data </p>
                    <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                    </g:if>
                    <div>
                        <g:form action="loadDataFromFile" controller="configSetting" method="post">
                        <div class="dialog">
                            <h1>Load Test Class Sessions</h1>
                        </div>
                        <div class="buttons">
                            <span class="button">
                                <input class="save" type="submit" value="Go!" />
                            </span>
                        </div>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
