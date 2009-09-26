
<%@ page import="org.bworks.bworksdb.Contact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}"/>
        <title>Contact List</title>
    </head>
    <body>
        <div class="body">
            <h1>Call List for Program: DUH!</h1>
            <div class="list">
                    <g:each in="${contactInstanceList}" status="i" var="contactInstance">
                <table>
                <tr>
                    <th>Contact:</th>
                    <td>
                        ${contactInstance}
                    </td>
                    <th>Address:</th>
                    <td>
                        ${contactInstance.fullAddress()}
                    
                    </td>
                </tr>
                <tr>
                    <th>Phone(s)</th>
                    <td>
                        ${contactInstance.allPhoneNumbers()}
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>
                <tr><th>Notes:</th>
                    <td></td>
                    </tr>

                <tr>
                    <th>Student</th>
                    <th>Grade</th>
                    <th>Gender</th>
                    <th>Birth Date</th>
                    <th>Interests</th>
                </tr>
                <g:each var="studentInstance" in="${contactInstance.students}">
                    <tr>
                        <td>${studentInstance}</td>
                        <td>${studentInstance.grade}</td>
                        <td>${studentInstance.gender}</td>
                        <td>${studentInstance.birthDate}</td>
                        <td>${studentInstance.activeInterestsSummary()}</td>
                    </tr>
                </g:each>
                </table>
                </g:each>
            </div>
        </div>
    </body>
</html>
