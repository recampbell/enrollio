<html>
    <head>
        <title>Welcome to Bworksdb</title>
		<meta name="layout" content="main" />
    </head>
    <body>
        <h1 style="margin-left:20px;">Welcome to Bworksdb</h1>
        <h1 style="margin-left:20px;">Contacts</h1>
        <ul>
       <li> <g:link controller="contact" action="create">New Contact</g:link></li>
        <li><g:link controller="contact" action="list">Show Contacts</g:link></li>
        </ul>
        <h1 style="margin-left:20px;">Programs</h1>
        <ul>
        <g:each var="program" in="${org.bworks.bworksdb.Program.getAll()}">
            <li><g:link controller="program" action="show" id="${program.id}">${program.name}</g:link></li>
        </g:each>
        </ul>
        
        <g:link controller="program" action="create">Create Program</g:link>
    </body>
</html>
