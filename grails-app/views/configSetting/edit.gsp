
<%@ page import="org.bworks.bworksdb.ConfigSetting" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ConfigSetting</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="box">
                    <h3>Edit Setting: ${configSettingInstance}</h3>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${configSettingInstance}">
            <div class="errors">
                <g:renderErrors bean="${configSettingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="update" name="editSettingForm" method="post" >
                <input type="hidden" name="id" value="${configSettingInstance?.id}" />
                <input type="hidden" name="version" value="${configSettingInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="configKey">Key:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:configSettingInstance,field:'configKey','errors')}">
                                    <input type="text" id="configKey" name="configKey" value="${fieldValue(bean:configSettingInstance,field:'configKey')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="value">Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:configSettingInstance,field:'value','errors')}">
                                    <input class="a_bit_wider" type="text" id="value" name="value" value="${fieldValue(bean:configSettingInstance,field:'value')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:configSettingInstance,field:'description','errors')}">
                                    <g:textArea name="description" 
                                        value="${fieldValue(bean:configSettingInstance,field:'description')}"
                                        rows="3" cols="30"/>
                                </td>                       
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
                        
                        
                        </tbody>
                    </table>
            </div>
                    <label for="saveButton"></label>
                        <g:submitButton class="save" name="saveButton" value="Update" />
                            or&nbsp;
                        <g:link name="cancelLink" class="cancelLink" action="show" id="${configSettingInstance.id}" >Cancel</g:link>

            </g:form>
        </div>
        </div>
        </div>
        <div id="sidebar">
            <g:render template="/admin/adminMenu" />
        </div>
    </body>
</html>
