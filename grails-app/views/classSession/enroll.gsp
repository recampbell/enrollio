<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>Moving stuff around in jQuery</title>
    <meta name="layout" content="main" />
    <script type="text/javascript" src="${resource(dir:'js', file:'jquery-1.3.2.js')}"></script>
    <script type="text/javascript" src="${resource(dir:'js', file:'jquery-ui-1.7.2.custom.min.js')}"></script>
    <script type="text/javascript" src="${resource(dir:'js', file:'dnd.js')}"></script>






</head>
<body id="foo " onload="">

<div id="status"></div>
<div style="float:left">
  <h2>Waiting List:</h2>



<ul id='availableStudents' class='studentBox'>
   <g:each in="${interestedStudents}">
       <li id='foo' class='item'>
           <h5>${it}</h5>
       </li>
    </g:each>


</ul></div>
<form action="foo">
  <h2>Enrolled:</h2>
<ul id="enrolledStudents" class="studentBox">

</ul>
<input type="submit">
</form>




</body>

