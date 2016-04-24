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
			hPage.globalVar.context="<%=request.getContextPath()%>";
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
	    		<form role="form" action="javascript:hPage.onLogin();">
					<div class="form-group">
	        			<label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label><label class="errMsg" id="usrErrMsg"> </label>
	        			<input type="text" class="form-control" id="usrname" placeholder="Enter username" name="user">
	      			</div>
		      		<div class="form-group">
		        		<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label><label class="errMsg" id="pswErrMsg"> </label>
		        		<input type="password" class="form-control" id="psw" placeholder="Enter password" name="pwd">
		      		</div>
		      		<div class="checkbox">
		        		<label><input type="checkbox" value="" checked>Remember me</label>
		      		</div>
	        		<button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
	    		</form>
	    		<p>Not a member? <a href="#" id="signUp">Sign Up</a></p>
	    		<p>Forgot <a href="#" id="fgtPsw">Password?</a></p>
	  		</div>
		</div>
	</div>
	<!-- Sign Up Modal -->
	<div class="modal fade" id="signUpModal" role="dialog">
  		<div class="modal-dialog">

    		<!-- Modal content-->
			<div class="modal-content">
  				<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal">&times;</button>
    				<h4 style="color:red;"><span class="glyphicon glyphicon-lock"></span> Sign Up</h4>
      			</div>
      			<div class="modal-body">
        			<form role="form" action="javascript:hPage.onSignUp();">
          				<div class="form-group">
            				<label for="signUpUsrname"><span class="glyphicon glyphicon-user"></span> Username (*)</label><label class="errMsg" id="signUpUsrErrMsg"> </label>
            				<input type="text" class="form-control" id="signUpUsrname" placeholder="Enter email">
          				</div>
          				<div class="form-group">
            				<label for="signUpPsw"><span class="glyphicon glyphicon-eye-open"></span> Password (*)</label><label class="errMsg" id="signUpPswErrMsg"> </label>
            				<input type="password" class="form-control" id="signUpPsw" placeholder="Enter password">
          				</div>
          				<div class="form-group">
            				<label for="signUpPsw2"><span class="glyphicon glyphicon-eye-open"></span> Re-enter Password (*)</label><label class="errMsg" id="signUpPsw2ErrMsg"> </label>
            				<input type="password" class="form-control" id="signUpPsw2" placeholder="Re-Enter password">
          				</div>
          				<button type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> SignUp</button>
        			</form>
      			</div>
    		</div>
  		</div>		
	</div>
	<!-- Forgot Passowrd Modal -->
	<div class="modal fade" id="fgtPswModal" role="dialog">
  		<div class="modal-dialog">

    		<!-- Modal content-->
			<div class="modal-content">
  				<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal">&times;</button>
    				<h4 style="color:red;"><span class="glyphicon glyphicon-lock"></span> Forgot Password</h4>
      			</div>
      			<div class="modal-body">
        			<form role="form">
          				<div class="form-group">
            				<label for="fgtPswUsrname"><span class="glyphicon glyphicon-user"></span> Username</label>
            				<input type="text" class="form-control" id="fgtPswUsrname" placeholder="Enter email">
          				</div>
          				<button type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Submit</button>
        			</form>
      			</div>
    		</div>
  		</div>		
	</div>
</body>
</html>