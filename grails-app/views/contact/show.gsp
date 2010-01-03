
<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />    
        <meta name="tabName" content="contact" />    
        <title>Show Contact</title>
  <g:javascript library="prototype" />
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <span>Contact</span>
                    
                        <br />
                    </h3>
                </div>
                <g:render template="contactSearchForm" />
                <div class="infowrap">
                    <div class="infobox">
                        <h3 class="reallynow"><span>${contactInstance}</span></h3>
                <table>
                    <tbody>

                    
                        <tr>
                            <td>Address:</td>
                            <td valign="top" class="value">${contactInstance.fullAddress()}</td>
                            
                        </tr>
                    
                    
                        <tr>
                            <td>Email Address:</td>
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'emailAddress')}</td>
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
                            <td valign="top" class="name">Zip Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:contactInstance, field:'zipCode')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
        </div>
        </div>
        <div id="box">
                        <h3 id="adduser"><span>Students</span></h3>
</div>
                <div class="infowrap">
                    <div class="infobox margin-left">
                        <h3 class="reallynow"><span>Students</span></h3>
                <table>
                    <tbody>

                                <g:each var="s" in="${contactInstance.students}">
                        <tr class="prop">
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                    <g:link controller="student" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link>
                            </td>
                            
                        </tr>
                                </g:each>
                    </tbody>
                </table>
        </div>
        </div>

            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${contactInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:link controller="student" action="create" params="['contact.id':contactInstance.id]" /></span>
                </g:form>
            </div>
            <g:form action="saveContactAndStudents">

              <h2>Contact's Students:</h2>
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
        </div><div id="sidebar">
            <g:render template="/common/sideMenu" />
        </div>
    </body>
</html>
