<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="google-signin-client_id" content="321152491452-m5uv0imoma9m07i4pub2la7ufg1bvd1d.apps.googleusercontent.com">
	<title>Java - aQuizaDay</title>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://code.jquery.com/jquery-2.2.1.js" integrity="sha256-eNcUzO3jsv0XlJLveFEkbB8bA7/CroNpNVk3XpmnwHc=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/resources/javascript/common.js"></script>
	<script>
		$( document ).ready(function() {
			myApp.test();
		});
	</script>
</head>
<body>
	<center>
		<h2>Hello World</h2>
		<h2>
			${message}
		</h2>
		<h2>
			<%=request.getContextPath()%>
		</h2>
	</center>
</body>
</html>