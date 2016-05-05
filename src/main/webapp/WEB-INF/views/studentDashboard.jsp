<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
	response.addHeader("Pragma", "no-cache"); 
	response.addDateHeader ("Expires", 0);
%>
<% 	String data=(String)session.getAttribute( "user" );
	if(data==null){
		response.sendRedirect(request.getContextPath() + "/homePage");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>aQaD Dashboard</title>
	
	<link rel="icon" href="<%=request.getContextPath()%>/resources/images/java_logo7.png">
	
	<script src="https://code.jquery.com/jquery-2.2.1.js" integrity="sha256-eNcUzO3jsv0XlJLveFEkbB8bA7/CroNpNVk3XpmnwHc=" crossorigin="anonymous"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/common.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/studentDashboard.js"></script>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/dashboard.css">
	
	<script>
		$( document ).ready(function() {
			sDash.globalVar.context="<%=request.getContextPath()%>";
			sDash.globalVar.user="<%= session.getAttribute("user")%>";
			sDash.init();
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
      			<a class="navbar-brand"><%=session.getAttribute("user") %></a>
    		</div>
    		<div>
      			<div class="collapse navbar-collapse" id="myNavbar">
        			<ul class="nav navbar-nav">
          				<li><a href="#dashboard">Dashboard</a></li>
          				<!-- <li><a href="#perf">Performance</a></li> -->
          				<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
        			</ul>
      			</div>
    		</div>
  		</div>
	</nav>    

	<div id="dashboard" class="container-fluid">
  		<h1>Dashboard</h1><label class="errMsg" id="qErrMsg"> </label>
  		<div class="row">
			<div class="col-xs-2">
				<b>Quizzes Subscribed</b>
				<table class="table table-hover table-striped" id="quizList" style="border-top: 1px solid black;">
					<thead>
				    	<tr>
				        	<th>Quiz Name</th>
				        	<th># of Questions Answered</th>
				      	</tr>
				    </thead>
				    <tbody>
				    </tbody>
				</table>
			</div>
			<div class="col-xs-8" style="border-left: 1px solid black;">
				<table class="table table-hover table-striped" id="quesList">
					<thead>
				    	<tr>
				        	<th>Question</th>
				        	<th>Publish Date</th>
				        	<th>Attempted</th>
				        	<th>Marks Obtained</th>
				      	</tr>
				    </thead>
				    <tbody>
				    </tbody>
				</table>
			</div>
			<div class="col-xs-2" style="border-left: 1px solid black;">
				<div id="datepicker"></div>
				<input type="hidden" id="selectedDate" name="selectedDate" />
			</div>
		</div>
		<div class="row">
			<div class="row">
				<div class="col-xs-12">
					<b>Subscribe to New Quiz : </b>(Click on quiz name to subscribe) 
				</div>
			</div>
			<div class="col-xs-12">
				<table class="table table-hover table-striped" id="subsList">
					<thead>
				    	<tr>
				        	<th>Quiz Name</th>
				        	<th>Instructor Email</th>
				        	<th># of Subscriptions</th>
				      	</tr>
				    </thead>
				    <tbody>
				    	<tr>
				    		<td>No Quiz Available</td>
				    		<td></td>
				    		<td></td>
				    	</tr>
				    </tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- <div id="perf" class="container-fluid">
  		<h1>Section 2</h1>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
	</div> -->
</body>
</html>