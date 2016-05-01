<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>aQaD Dashboard</title>
	
	<link rel="icon" href="<%=request.getContextPath()%>/resources/images/java_logo7.png">
	
	<script src="https://code.jquery.com/jquery-2.2.1.js" integrity="sha256-eNcUzO3jsv0XlJLveFEkbB8bA7/CroNpNVk3XpmnwHc=" crossorigin="anonymous"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/common.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/instructorDashboard.js"></script>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/instructorDashboard.css">
	
	<script>
		$( document ).ready(function() {
			iDash.init();
			iDash.globalVar.context="<%=request.getContextPath()%>";
		});
	</script>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">

	<div class="container-fluid qotd-container top">
		<a href="homePage" class="qotd-logo"><label>Java</label> <label style="color: brown;">a</label><label style="color: blue;">Quiz</label><label style="color: brown;">a</label><label style="color: blue;">Day</label></a>
		<div class="qotd-right" style="font-style: italic; margin-top: 17px;">Any fool can know. The point is to understand. - <b>Albert Einstein</b></div>
	</div>

	<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
  		<div class="container-fluid">
    		<div class="navbar-header">
        		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          			<span class="icon-bar"></span>
          			<span class="icon-bar"></span>
          			<span class="icon-bar"></span>                        
      			</button>
      			<a class="navbar-brand" href="#">WebSiteName</a>
    		</div>
    		<div>
      			<div class="collapse navbar-collapse" id="myNavbar">
        			<ul class="nav navbar-nav">
          				<li><a href="#section1">Section 1</a></li>
          				<li><a href="#section2">Section 2</a></li>
          				<li><a href="#section3">Section 3</a></li>
          				<li><a href="#section4">Section 4</a></li>
        			</ul>
      			</div>
    		</div>
  		</div>
	</nav>    

	<div id="section1" class="container-fluid">
  		<h1>Section 1</h1>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
	</div>
	<div id="section2" class="container-fluid">
  		<h1>Section 2</h1>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
	</div>
	<div id="section3" class="container-fluid">
  		<h1>Section 3</h1>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
	</div>
	<div id="section4" class="container-fluid">
  		<h1>Section 4</h1>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
	</div>
</body>
</html>