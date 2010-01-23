
<%@ page import="org.bworks.bworksdb.ConfigSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Setting: ${configSettingInstance.configKey}</title>
    </head>
    <body>

        <div id="wrapper">
            <div id="content">
                <div class="rightnow">

            <h1>Show ConfigSetting</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'value')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'description')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Is Default:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'isDefault')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Key:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'configKey')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        </div>
        <div id="sidebar">
            <g:render template="/admin/settingsMenu" />
        </div>
    </body>
</html>
