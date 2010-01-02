<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>Edit Enrollments - ${classSession}</title>
    <meta name="layout" content="main" />
    <meta name="tabName" content="classSession" />
    <link rel="stylesheet"
    href="${resource(dir:'css/pepper-grinder',file:'jquery-ui-1.7.2.custom.css')}" />
    <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}">
    </script>
    <script type="text/javascript"
    src="${resource(dir:'js', file:'jquery-ui-1.7.2.custom.min.js')}"></script>
    <script type="text/javascript" src="${resource(dir:'js', file:'dnd.js')}"></script>
    <script type="text/javascript">
        $(document).ready(function(){
                $('.addButtonIconExample').toggle(function() {
                    $(this).parent('tr').appendTo('#enrolledStudents');
                    var btn = $(this).find('span');
                    btn.toggleClass('ui-icon-plus');
                    btn.toggleClass('ui-icon-minus');
                    $(this).find('div').attr('title', 'Un-enroll this student');
                },
                function() {
                    $(this).parent('tr').appendTo('#eligibleStudents');
                    var btn = $(this).find('span');
                    btn.toggleClass('ui-icon-plus');
                    btn.toggleClass('ui-icon-minus');
                    $(this).find('div').attr('title', 'Enroll this student');
                }
                );

        });
    </script>
</head>
<body>
    <div id="container">
        <div id="wrapper">
            <div id="content">
                <div class="infowrap">
                    <div class="infobox">
                        <h3>Eligible Students</h3>
                        <table>
                            <thead>
                                <th>&#160;</th>
                                <th>Student</th>
                                <th>Contact</th>
                                <th>Signup Date</th>
                            </thead>
                            <tbody id="eligibleStudents" class="scrollContent"
                            style="min-height: 50px;">
                                <g:each var="interest" in="${interests}">
                                    <tr>
                                        <td class="addButtonIconExample">
                                            <div class="ui-state-default ui-corner-all"
                                            title="Enroll this Student">
                                                <span class="ui-icon ui-icon-plus">
                                                </span>
                                            </div>
                                            <input type="hidden"
                                            name="interestedStudents"
                                            value="${interest.student.id}"></input>
                                        </td>
                                        <td>${interest.student}</td>
                                        <td>${interest.student.contact}</td>
                                        <td>
                                        <g:formatDate format="MM/dd/yyyy"
                                        date="${interest.dateCreated}" />&#160;</td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                    <div class="infobox margin-left">
                    <g:form class="sideForm">
                        <h3 class="reallynow">
                            <span>Enrolled Students</span>
                        </h3>
                        <table width="50%">
                                <input type="hidden" name="classSessionId"
                                value="${classSession.id}" />
                                <thead>
                                    <th>&#160;</th>
                                    <th>Student</th>
                                    <th>Contact</th>
                                    <th>Signup Date</th>
                                </thead>
                                <tbody id="enrolledStudents" class="scrollContent">
                                </tbody>
                                <g:actionSubmit style="float:right;"
                                    value="Save Enrollments" />
                        </table>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
