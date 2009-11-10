<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Dashboard - Admin Template</title>
        <!--[if IE]>
        <link rel="stylesheet" type="text/css" href="css/ie-sucks.css" />
        <![endif]-->
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'theme3.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css', file:'style.css')}" />
</head>

<body>
	<div id="container">
        <div id="wrapper">
            <div id="content">
       			<div id="rightnow">
                    <h3 class="reallynow">
                        <span>Class Session: ${classSessionInstance.name}</span>

                        <a href="#" class="add">Add New Product</a>
                        <a href="#" class="app_add">Print Graduation Certificates</a>
                        <br />
                    </h3>
                    	    <p class="youhave">TODO Put message here</p>
                    <table>
						<thead>
						</thead>
						<tbody>
							<tr>
                            	<td><a href="#">Program:</a></td>
                                <td>${classSessionInstance.program.name}</td>
                            </tr>
							<tr>
                            	<td><a href="#">Start Date</a></td>
                            	<td>${classSessionInstance.startDate}</td>
                            </tr>
						</tbody>
					</table>
			  </div>
              <div id="infowrap">
                  <div id="infobox">
                    <h3>Lesson Dates</h3>
                    <table>
                        <tbody>
                            <g:each var="lessonDate" in="${classSessionInstance.lessonDates}">
                            </g:each>
                        </tbody>
							
                    </table>            
                  </div>
                  <div id="infobox" class="margin-left">
                      <h3 class="reallynow">
                          <span>Enrollments</span>
                        <a href="#" class="app_add">(Add Enrollments)</a>
                        <br />
                    </h3>
                    <table>
			<tbody>
                            <g:each var="enr" in="${classSessionInstance.enrollments}">
                                <tr>
                                <td><a href="#">${enr.student}</a></td>
                                <td>${enr.status}</td>
                                </tr>
                            </g:each>
				
			</tbody>
					</table>
                  </div>
              </div>
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
</div>
</body>
</html>
