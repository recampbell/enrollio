
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
                    <th>Contact Name:</th>
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
                    <td>Student Name</td>
                    <td>Grade</td>
                    <td>Gender</td>
                    <td>Birtddate</td>
                    <td>Interests (Circle to Enroll)</td>
                </tr>
                <tr>
                    <td>Buchholz, Francisco</td>
                    <td>4</td>
                    <td>M</td>
                    <td>10/23/1009</td>
                    <td>Basketball, idiocy, stupidity</td>
                </tr>
                </table>
                </g:each>
            </div>
        </div>
    </body>
</html>
