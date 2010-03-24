<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <meta name="tabName" content="help" />
        <title>Help - About</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="content">
                <div class="rightnow">
                    <h3 class="reallynow">2010/03/23</h3>
                    
                    <ul class="helplist">
                        <li>Renamed "Programs" to "Courses"</li>
                        <li><g:link action="list" controller="student">Search&nbsp;</g:link>by phone numbers, address, student, and  contact from one page! </li>
                        <li><b>Awesome</b> improvements when <g:link action="create" controller="contact"> creating students </g:link>
                        
                        <ul>
                        
                            <li>Users can search for existing contacts to add a student</li>
                            <li>Students are much easier to create from a 
                            
                            <g:link action="show" controller="contact" id="1">
                            
                            contact page
                            </g:link>
                            
                            </li>
                        
                            <li>Easily add Student Interests</li>
                        </ul>
                        </li>
                        </li>
                        <li>This "What's New" page
                        <img src="${resource(dir:'images/icons', file:'emoticon_grin.png')}" />
                        
                        
                        
                        
                        </li>
                        <li>Simplified site Navigation (Removed Sessions and Contacts)</li>
                        <li>Editing 
                        <g:link controller="contact" action="edit" id="1">contacts</g:link>
                        and 
                        <g:link controller="student" action="edit" id="1">students</g:link>
                        is much prettier
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="sidebar">
            <g:render template="helpMenu" />
        </div>
    </body>
</html>
