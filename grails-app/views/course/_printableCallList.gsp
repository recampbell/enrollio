<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'style.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme1.css')}" />
        <title>Contact List</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>Call List for ${courseInstance}</span>
                        <br />
                    </h3>
                    <table>
                        <g:each var="con" in="${contactInstanceList}">
                            <g:render template="printableInterestedContact" 
                            model="[
                            courseInstance  : courseInstance,
                            contactInstance : con,
                            callListContacts : callListContacts ]" />
                        </g:each>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
