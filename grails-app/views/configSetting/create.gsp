
<%@ page import="org.bworks.bworksdb.ConfigSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ConfigSetting</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ConfigSetting List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ConfigSetting</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${configSettingInstance}">
            <div class="errors">
                <g:renderErrors bean="${configSettingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="value">Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:configSettingInstance,field:'value','errors')}">
                                    <input type="text" id="value" name="value" value="${fieldValue(bean:configSettingInstance,field:'value')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:configSettingInstance,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:configSettingInstance,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isDefault">Is Default:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:configSettingInstance,field:'isDefault','errors')}">
                                    <g:checkBox name="isDefault" value="${configSettingInstance?.isDefault}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="key">Key:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:configSettingInstance,field:'key','errors')}">
                                    <input type="text" id="key" name="key" value="${fieldValue(bean:configSettingInstance,field:'key')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
