<div class='navbarBox'>

	<div class="navcontainer1">
			<h1 class="panelHeader">				
				Contacts
			</h1>
			<ul class="navlist">
				<li>
					<g:link controller="contact" action="list">
                                            List
					</g:link>
				</li>				
				<li>
					<g:link controller="contact" action="create">
                                            Add
					</g:link>		
				</li>	
			</ul>						
	</div> 	
	
</div>

<div class="navbarBox">

	<div class="navcontainer1">
			<h1 class="panelHeader">				
				Programs
			</h1>
			<ul class="navlist">
                        <li>
                            <g:link controller="program" action="list">
                                List
                            </g:link>		
                            <g:link controller="program" action="create">
                                Create
                            </g:link>
                            </li>
			</ul>						
	</div> 	
	
</div>

<div class='navbarBox'>

	<div class="navcontainer1">
			<h1 class="panelHeader">				
				Users
			</h1>
			<ul class="navlist">
                            <li>
                                <g:link controller="shiroUser" action="list">
                                    List Users
                                </g:link>
                            </li>
                            <shiro:hasRole name="Administrator">
                            <li>
                                <g:link controller="shiroUser" action="create">
                                    Create User
                                </g:link>
                            </li>
                            </shiro:hasRole>
                        </ul>						
	</div> 	
	
</div>
<shiro:hasRole name="Administrator">
<div class='navbarBox'>

	<div class="navcontainer1">
			<h1 class="panelHeader">				
                        Admin
			</h1>
			<ul class="navlist">
                            <li>
                                <g:link controller="configSetting" action="list">
                                    Settings
                                </g:link>
                            </li>
                        </ul>						
	</div> 	
	
</div>
</shiro:hasRole>
