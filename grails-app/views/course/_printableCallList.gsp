<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'style.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme1.css')}" />
        <title>Contact List</title>
    </head>
    <body style="background:none">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>Call List for ${courseInstance}</span>
                        <span style="float:right">Printed: <enrollio:formatDate date="${new Date()}"/></span>
                        <br />
                    </h3>
                        <g:each var="con" in="${contactInstanceList}">
                            <g:render template="printableInterestedContact" 
                            model="[
                            courseInstance  : courseInstance,
                            contactInstance : con,
                            callListContacts : callListContacts ]" />
                        </g:each>
                </div>
    </body>
</html>
