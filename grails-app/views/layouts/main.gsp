<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <head>
        <title>
            <g:layoutTitle default="Enrollio" />
        </title>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <!--[if IE]>
        <link rel="stylesheet" type="text/css" href="css/ie-sucks.css" />
        <![endif]-->
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'style.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme1.css')}" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
	<div id="container">
    	<div id="header">
            <shiro:isLoggedIn>
                <h2>Welcome to Enrollio, <shiro:principal/></h2>
            </shiro:isLoggedIn>
            <shiro:isNotLoggedIn>
                <h2>Welcome to Enrollio!</h2>
            </shiro:isNotLoggedIn>
    <div id="topmenu">

        <shiro:isLoggedIn>
            <span class="enr-welcome-message">
                Welcome, <shiro:principal/>! | <g:link controller="auth" action="signOut">Log Out</g:link>
            </span>
            <br />
            <div class="enr-main-menu">
                <g:link controller="program" action="list" 
                    class="enr-top-menu-item">Programs</g:link>&nbsp;|&nbsp;

                <g:link controller="contact" action="list" 
                    class="enr-top-menu-item">Contacts</g:link>&nbsp;|&nbsp;
                <g:link controller="classSession" action="list" 
                    class="enr-top-menu-item">Class Sessions</g:link>
                <shiro:hasRole name="Administrator">
                     &nbsp;|&nbsp;<g:link controller="admin"  class="enr-top-menu-item">Admin</g:link>
                </shiro:hasRole>
            </div>

            <div id="enr-main-searchbar" >
                <g:form url='[controller: "searchable", action: "index"]'
                        id="searchableForm" name="searchableForm" method="get">
                        <g:textField name="q" value="${params.q}" size="30"/>
                        <input type="submit" value="Search" />
                </g:form>  
            </div>
        </shiro:isLoggedIn>
</div>

            	<ul>
                    <li class="current"><a href="index.html">Dashboard</a></li>
                    <li><a href="#">Orders</a></li>
                	<li><a href="users.html">Users</a></li>
                    <li><a href="#">Manage</a></li>
                    <li><a href="#">CMS</a></li>
                    <li><a href="#">Statistics</a></li>
                    <li><a href="#">Settings</a></li>
              </ul>
          </div>
      </div>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <li><a href="#" class="report">Sales Report</a></li>
                    <li><a href="#" class="report_seo">SEO Report</a></li>
                    <li><a href="#" class="search">Search</a></li>
                    <li><a href="#" class="feed">RSS Feed</a></li>
                </ul>
            </div>
      </div>
        <div id="wrapper">
            <div id="content">
                <p>&nbsp;</p>
                <p>&nbsp;</p>
            </div>
            <div id="sidebar">
  				<ul>
                	<li><h3><a href="#" class="house">Dashboard</a></h3>
                        <ul>
                        	<li><a href="#" class="report">Sales Report</a></li>
                    		<li><a href="#" class="report_seo">SEO Report</a></li>
                            <li><a href="#" class="search">Search</a></li>
                        </ul>
                    </li>
                    <li><h3><a href="#" class="folder_table">Orders</a></h3>
          				<ul>
                        	<li><a href="#" class="addorder">New order</a></li>
                          <li><a href="#" class="shipping">Shipments</a></li>
                            <li><a href="#" class="invoices">Invoices</a></li>
                        </ul>
                    </li>
                    <li><h3><a href="#" class="manage">Manage</a></h3>
          				<ul>
                            <li><a href="#" class="manage_page">Pages</a></li>
                            <li><a href="#" class="cart">Products</a></li>
                            <li><a href="#" class="folder">Product categories</a></li>
            				<li><a href="#" class="promotions">Promotions</a></li>
                        </ul>
                    </li>
                  <li><h3><a href="#" class="user">Users</a></h3>
          				<ul>
                            <li><a href="#" class="useradd">Add user</a></li>
                            <li><a href="#" class="group">User groups</a></li>
            				<li><a href="#" class="search">Find user</a></li>
                            <li><a href="#" class="online">Users online</a></li>
                        </ul>
                    </li>
				</ul>       
          </div>
      </div>
        <div id="footer">
        <div id="credits">
   		Template by <a href="http://www.bloganje.com">Bloganje</a>
        </div>
        <div id="styleswitcher">
            <ul>
                <li><a href="javascript: document.cookie='theme='; window.location.reload();" title="Default" id="defswitch">d</a></li>
                <li><a href="javascript: document.cookie='theme=1'; window.location.reload();" title="Blue" id="blueswitch">b</a></li>
                <li><a href="javascript: document.cookie='theme=2'; window.location.reload();" title="Green" id="greenswitch">g</a></li>
                <li><a href="javascript: document.cookie='theme=3'; window.location.reload();" title="Brown" id="brownswitch">b</a></li>
                <li><a href="javascript: document.cookie='theme=4'; window.location.reload();" title="Mix" id="mixswitch">m</a></li>
            </ul>
        </div><br />

        </div>
</div>

        <!-- <div id="page"> -->
            <!-- <div> -->
                <!-- <g:render template="/common/topbar" /> -->
            <!-- </div> -->
            <!-- <div id="content"> -->
                <!-- <g:layoutBody /> -->
            <!-- </div> -->
        <!-- </div> -->
    </body>
</html>
