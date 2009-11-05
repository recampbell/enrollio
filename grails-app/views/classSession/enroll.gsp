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
    <g:form>
        <input type="hidden" name="classSessionId" value="${classSession.id}" />
        <table  style="float:left;" width="50%">
            <caption>Eligible Students</caption>
            <thead>
                <th>&nbsp;</th>

                <th>Student</th>

                <th>Contact</th>

                <th>Signup Date</th>
            </thead>

            <tbody id="eligibleStudents" class="scrollContent">
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
                        <td><g:formatDate format="MM/dd/yyyy" date="${interest.dateCreated}" />
                            <td>&nbsp;</td>
                    </tr>
                </g:each>
            </tbody>
            </table>
            <table  width="50%">
            <caption>Enrolled Students</caption>
            <thead>
                <th>&nbsp;</th>

                <th>Student</th>

                <th>Contact</th>

                <th>Signup Date</th>
            </thead>

            <tbody id="enrolledStudents" class="scrollContent">
            </tbody>
        </table>
    </g:form>
</body>
