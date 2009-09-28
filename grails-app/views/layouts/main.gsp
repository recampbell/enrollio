<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <head>
        <title><g:layoutTitle default="Enrollio!" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}"/>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />				
    </head>
    <body>

		<div id="page">
		
			<div id="topbar">
				<g:render template="/common/topbar" />
			</div>
			

                            <div id="header">
                            <img src="${resource(dir:'images',file:'bworks_logo_matthews.png')}"
                                 alt="Byteworks Enrollio" style="float: left;"/> <div class="searchbox"><g:form url='[controller: "searchable", action: "index"]' id="searchableForm" name="searchableForm" method="get">
        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Search" />
    </g:form>  </div>
			</div>

			<div id="content">				
				<g:layoutBody />		
			</div>
			<div id='navbar'>
				<g:render template="/common/navbar" />
			</div>
					
			<div id="footer">
				<g:render template="/common/footer" />
			</div>
		
		</div> 			

    </body>	
</html>
