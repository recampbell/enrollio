
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
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <div class="rightnow">

                    <h3 class="reallynow">Setting: ${configSettingInstance}</h3>
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Key:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'configKey')}</td>
                            
                        </tr>
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'description')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'value')}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Default?</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:configSettingInstance, field:'isDefault')}</td>
                            
                        </tr>
                    
                    
                    </tbody>
                </table>
        </div>
        </div>
        </div>
        <div id="sidebar">
            <g:render template="/admin/settingsMenu" />
        </div>
    </body>
</html>
