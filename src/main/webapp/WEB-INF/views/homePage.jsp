<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Java - aQuizaDay</title>
	
	<link rel="icon" href="<%=request.getContextPath()%>/resources/images/java_logo7.png">
	
	<script src="https://code.jquery.com/jquery-2.2.1.js" integrity="sha256-eNcUzO3jsv0XlJLveFEkbB8bA7/CroNpNVk3XpmnwHc=" crossorigin="anonymous"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/common.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/homePage.js"></script>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
	
	<script>
		$( document ).ready(function() {
			hPage.init();
		});
	</script>
</head>
<body>
	<div class="qotd-container top">
		<a href="homePage" class="qotd-logo"><label>Java</label> <label style="color: brown;">a</label><label style="color: blue;">Quiz</label><label style="color: brown;">a</label><label style="color: blue;">Day</label></a>
		<div class="qotd-right" style="font-style: italic; margin-top: 17px;">The greatest enemy of knowledge is not ignorance, rather illusion of knowledge - <b>Stephen Hawking</b></div>
	</div>
	<div class="row">
		<div class="col-xs-9">
		</div>
		<div class="col-xs-3" style="border-left: 1px solid black;">
			<div style="padding:40px 50px;">
	    		<form role="form" >
					<div class="form-group">
	        			<label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
	        			<input type="text" class="form-control" id="usrname" placeholder="Enter username" name="user">
	      			</div>
		      		<div class="form-group">
		        		<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
		        		<input type="password" class="form-control" id="psw" placeholder="Enter password" name="pwd">
		      		</div>
		      		<div class="checkbox">
		        		<label><input type="checkbox" value="" checked>Remember me</label>
		      		</div>
	        		<button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
	    		</form>
	    		<p>Not a member? <a href="#">Sign Up</a></p>
	    		<p>Forgot <a href="#">Password?</a></p>
	  		</div>
		</div>
	</div>
</body>
</html>