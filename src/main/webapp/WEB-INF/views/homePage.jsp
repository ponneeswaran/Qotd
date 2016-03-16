<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="google-signin-client_id" content="${clientId}">
	<title>Java - aQuizaDay</title>
	
	<link rel="icon" href="<%=request.getContextPath()%>/resources/images/java_logo7.png">
	
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://code.jquery.com/jquery-2.2.1.js" integrity="sha256-eNcUzO3jsv0XlJLveFEkbB8bA7/CroNpNVk3XpmnwHc=" crossorigin="anonymous"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/common.js"></script>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
	
	<script>
		$( document ).ready(function() {
			qotd.init();
		});
		function onSignIn(googleUser) {
		  var profile = googleUser.getBasicProfile();
		  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
		  console.log('Name: ' + profile.getName());
		  console.log('Image URL: ' + profile.getImageUrl());
		  console.log('Email: ' + profile.getEmail());
		}
	    function onFailure(error) {
	      console.log(error);
	    }
	    function renderButton() {
	      gapi.signin2.render('my-signin2', {
	        'scope': 'profile email',
	        'width': 240,
	        'height': 50,
	        'longtitle': true,
	        'theme': 'dark',
	        'onsuccess': onSignIn,
	        'onfailure': onFailure
	      });
	    }
	</script>
</head>
<body>
	<div class="qotd-container top">
		<a href="homePage" class="qotd-logo"><label>Java</label> <label style="color: brown;">a</label><label style="color: blue;">Quiz</label><label style="color: brown;">a</label><label style="color: blue;">Day</label></a>
		<div class="qotd-right" style="font-style: italic; margin-top: 17px;">The greatest enemy of knowledge is not ignorance, rather illusion of knowledge - <b>Stephen Hawking</b></div>
	</div>
	<button type="button" class="btn btn-default btn-lg" id="myBtn">Login</button>
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="padding:35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
				</div>
				<div class="modal-body" style="padding:40px 50px;">
		    		<form role="form">
						<div class="form-group">
		        			<label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
		        			<input type="text" class="form-control" id="usrname" placeholder="Enter email">
		      			</div>
			      		<div class="form-group">
			        		<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
			        		<input type="text" class="form-control" id="psw" placeholder="Enter password">
			      		</div>
			      		<div class="checkbox">
			        		<label><input type="checkbox" value="" checked>Remember me</label>
			      		</div>
		        		<button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
		    		</form>
		  		</div>
				<div class="modal-footer">
		    		<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
		    		<p>Not a member? <a href="#">Sign Up</a></p>
		    		<p>Forgot <a href="#">Password?</a></p>
		  		</div>
			</div>
		</div>
	</div>
</body>
</html>