<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <head>
        <title><g:layoutTitle default="Enrollio!" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}"/>
        <link type="text/css" href="${resource(dir:'css/pepper-grinder', file:'jquery-ui-1.7.2.custom.css')}" />	
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />				
    </head>
    <body>

		<div id="page">
		
			<div >
				<g:render template="/common/topbar" />
			</div>
			


			<div id="content">				
				<g:layoutBody />		
			</div>
					
			<div id="footer">
				<g:render template="/common/footer" />
			</div>
		

    </body>	
</html>
