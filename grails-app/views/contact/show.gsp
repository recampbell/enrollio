
<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />    
        <title>Show Contact</title>
  <g:javascript library="prototype" />
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
            <h1>Show Contact</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address1:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'address1')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address2:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'address2')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email Address:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'emailAddress')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">First Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'firstName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'lastName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Phone Numbers:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${contactInstance.phoneNumbers}">
                                    <li><g:link controller="phoneNumber" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                
	                    <tr class="prop">
	                        <td valign="top" class="name">City:</td>
                        
	                        <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'city')}</td>
                        
	                    </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">State:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'state')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Students:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="s" in="${contactInstance.students}">
                                    <li><g:link controller="student" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Zip Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'zipCode')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>

            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${contactInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:link controller="student" action="create" params="['contact.id':contactInstance.id]" />
                </g:form>
            </div>
            <g:form action="saveContactAndStudents">

              <br><br><h2>Contact's Students:</h2>
              <input type="hidden" name="id" value="${contactInstance.id}"/>
              <table id="studentList" style="width:99%;table-layout:fixed;padding:2px;margin:2px;">
                <g:render template='studentList' />
              </table>
              <div class="buttons">
                <span class="button"><g:submitButton class="save" name="Save"/></span>
                <span class="button"><g:actionSubmit class="delete"
                        onclick="return confirm('Are you sure?');" action="delete" value="Delete contact"/></span>
                <span class="button"><g:actionSubmit class="edit" action="list"
                        value="Return to contact List"/></span>
              </div>
            </g:form>
        </div>
        </div>
        </div><div id="sidebar">
            <g:render template="/common/sideMenu" />
        </div>
    </body>
</html>
