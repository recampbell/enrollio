<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>Moving stuff around in jQuery</title>
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${resource(dir:'css/pepper-grinder',file:'jquery-ui-1.7.2.custom.css')}" />
    <!-- <link rel="stylesheet" href="${resource(dir:'css',file:'enrollments.css')}"/> -->
    <!-- <link rel="stylesheet" href="${resource(dir:'css',file:'bookstore.css')}"/> -->
    <!-- <link rel="stylesheet" href="${resource(dir:'css',file:'forms.css')}"/> -->
    <!-- <link rel="stylesheet" href="${resource(dir:'css',file:'rotators.css')}"/> -->
    <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}">
    </script>
    <script type="text/javascript" src="${resource(dir:'js', file:'jquery-ui-1.7.2.custom.min.js')}"></script>
    <script type="text/javascript" src="${resource(dir:'js', file:'dnd.js')}"></script>
    <!-- <script src="${resource(dir:'js', file:'jquery.js')}" type="text/javascript"></script> -->
    <!-- <script src="${resource(dir:'js', file:'bookstore.js')}" type="text/javascript"></script> -->
    <!-- <script src="${resource(dir:'js', file:'tables.js')}" type="text/javascript"></script> -->
    <!-- <script src="${resource(dir:'js', file:'forms.js')}" type="text/javascript"></script> -->
    <!-- <script src="${resource(dir:'js', file:'rotators.js')}" type="text/javascript"></script> -->
</head>
<body>
    <g:form>
        <input type="hidden" name="classSessionId" value="${classSession.id}" />
        <table border="0" cellpadding="0" cellspacing="0" width="100%"
        class="sortable paginated scrollTable">
            <thead class="fixedHeader">
                <tr>
                    <th class="sort-numeric">Enroll?</th>
                    <th class="sort-alpha">Student</th>
                    <th class="sort-alpha">Contact</th>
                    <th class="sort-date">Signup Date</th>
                </tr>
            </thead>
            <tbody class="scrollContent">
                <g:each var="interest" in="${interests}">
                    <tr>
                        <td class="addButtonIconExample">
                            <div class="ui-state-default ui-corner-all" 
                                title="Enroll this Student">
                                <span class="ui-icon ui-icon-plus"></span>
                            </div>
                            <input type="hidden" name="interestedStudent[]"
                            value="${interest.student.id}"></input>
                        </td>
                        <td>${interest.student}</td>
                        <td>${interest.student.contact}</td>
                        <td>${interest.dateCreated}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <span class="button">
            <g:actionSubmit class="save" action="saveEnrollments" value="Save" />
        </span>
    </g:form>
</body>
