<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="course" />
        <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.4.2.min.js')}"></script>
        <script type="text/javascript">
             $(document).ready(function(){
                $('.enrollStudent').click(function() {
                    $.post('${createLink(controller:"classSession",
                                            action:"enrollStudent")}',
                            { 'enroll' : $(this).attr('checked'), 
                            'studentId' : $(this).attr("studentId"),
                            'classSessionId' : $(this).attr("classSessionId") },
                            function(data) {
                            // update student count w/results from enrollStudent action
                                $('#studentCount').html(data);
                            });
                    });
                // When user clicks any entry that has a class of "reserveContact", then
                // submit a POST to the app, with the contactId and classSessionId, and then
                // the contact will be "reserved" for the current user.  meow.
                $('.reserveContact').click(function() {
                    $(this).load('${createLink(controller:"classSession",
                                            action:"reserveContact")}',
                            { 'contactId' : $(this).attr("contactId"),
                              'classSessionId' : $(this).attr("classSessionId") });
                    });
            });
        </script>
        <title></title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">
                        <g:if test="${classSessionInstance}">
                            <span>${classSessionInstance}</span>
                        </g:if>
                        <g:else>
                            <span>${courseInstance}</span>
                        </g:else>
                        <br />
                    </h3>
                        <g:if test="${classSessionInstance}">
                        <p id="studentCount" class="youhave"><b>${classSessionInstance.enrollments?.size()}</b> students enrolled</p>
                        </g:if>
                    <table>
                        <thead>
                            <th colspan="1">Reserve</th>
                            <th colspan="2">Contact</th>
                        <th>Students</th>
                        </thead>
                        <g:each var="con" status="placeInList" in="${contactInstanceList}">
                        <g:render template="interestedContact" 
                            model="[placeInList : placeInList, 
                            users : users,
                            contactInstance : con,
                            callListContacts : callListContacts,
                            classSessionInstance : classSessionInstance ]" />
                        </g:each>
                    </table>
                </div>
            </div>
                <g:if test="${classSessionInstance}">
                <div id="sidebar">
                    <g:render template="/classSession/individualClassSessionMenu" 
                    model="[classSessionInstance:classSessionInstance]" />
                </div>
                </g:if>
        </div>
    </body>
</html>
