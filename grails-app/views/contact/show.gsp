<%@ page import="org.bworks.bworksdb.Contact" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <meta name="tabName" content="contact" />
    <title>Contact: ${contactInstance}</title>
</head>
<html>
    <div id="wrapper">
        <div id="content">
            <div id="box">
                <g:render template="contactSearchForm" />
            </div>
            <br />
            <div id="box">
                <h3>Contact: ${contactInstance}</h3>
                <table>
                    <tbody>
                        <tr>
                            <td>Address:</td>
                            <td>${contactInstance.address1} 
                            <br />
                            <g:if test="${contactInstance.address2}">
                            ${contactInstance.address2} 
                            <br /></g:if>
                            <g:if test="${contactInstance.city}">
                            ${contactInstance.city},&#160;&#160;</g:if>
                            <g:if test="${contactInstance.state}">
                            ${contactInstance.state} 
                            <br /></g:if>
                            <g:if test="${contactInstance.zipCode}">
                            ${contactInstance.zipCode}&#160;&#160;</g:if>
                            <br /></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>${contactInstance.emailAddress}</td>
                        </tr>
                        <g:each var="phone" in="${contactInstance.phoneNumbers}">
                            <tr>
                                <td>${phone.label}</td>
                                <td>${phone.phoneNumber}</td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
                <br />
            </div>
            <div id="box">
                <h3 id="adduser">Students</h3>
            </div>
        </div>
        <div id="sidebar">
            <g:render template="contactMenu" />
        </div>
    </div>
</html>
