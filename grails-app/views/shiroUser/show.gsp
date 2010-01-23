

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show User: ${shiroUserInstance.username}</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="box">
            <h3>Show User</h3>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Username:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:shiroUserInstance, field:'username')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">First Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:shiroUserInstance, field:'firstName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:shiroUserInstance, field:'lastName')}</td>
                            
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        <div id="sidebar">
            <g:render template="/admin/adminMenu" />
        </div>
    </body>
</html>
