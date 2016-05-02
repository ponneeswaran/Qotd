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
	<script src="<%=request.getContextPath()%>/resources/javascript/instructorDashboard.js"></script>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/instructorDashboard.css">
	
	<script>
		$( document ).ready(function() {
			iDash.globalVar.context="<%=request.getContextPath()%>";
			iDash.globalVar.user="<%= session.getAttribute("user")%>";
			iDash.init();
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
      			<a class="navbar-brand" href="#"><%=session.getAttribute("user") %></a>
    		</div>
    		<div>
      			<div class="collapse navbar-collapse" id="myNavbar">
        			<ul class="nav navbar-nav">
          				<li><a href="#dashboard">Dashboard</a></li>
          				<li><a href="#schedule">Schedule Quiz</a></li>
          				<li><a href="#perf">Performance</a></li>
          				<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
        			</ul>
      			</div>
    		</div>
  		</div>
	</nav>    

	<div id="dashboard" class="container-fluid">
		<h2>Scheduled Quizzes </h2><label class="errMsg" id="qErrMsg"> </label>
		<div class="row">
			<div class="col-xs-2">        
				<table class="table table-hover table-striped" id="quizList">
					<thead>
				    	<tr>
				        	<th>Quiz Name</th>
				        	<th># of Subscriptions</th>
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
				        	<th>Scheduled Date (YYYY-MM-DD)</th>
				        	<th># of Submissions</th>
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
	</div>
	<div id="schedule" class="container-fluid">
  		<h2>Schedule Quiz </h2><label class="errMsg" id="q2ErrMsg"> </label><br/><br/>
  		<div class="row">
	  		<div class="col-xs-10" style="border-right: 1px solid black;">
	  			<div class="row">
	  				<div class="col-xs-2">
	  					<b>Select Quiz : </b></b><select id="quizListOpt"></select>
	  				</div>
	  				<div class="col-xs-1">
	  					<b>(or)</b>
	  				</div>
	  				<div class="col-xs-9">
	  					<b>Create Quiz : </b><input id="quizName" name="quizName" />
	  				</div>
	  			</div>
	  			<div class="row">
	  				<div class="col-xs-12">
	  					*(If quiz is selected and new quiz name is given, existing quiz willbe selected).<br/><br/>
	  				</div>
	  			</div>
	  			<div class="line-separator"></div>
	  			<div class="row">
	  				<div class="col-xs-12">
	  					<b>Select Java Topic : </b><select id="javaTopics"></select><br/><br/>
	  					<b>Available Questions in this topic :</b>
	  				</div>
	  			</div>
	  			<div class="row">
	  				<div class="col-xs-12">
	  					<div class="list-group" id="questionGroup">
	  						<li class="list-group-item">Select a topic to populate the question list.</li>
						</div>
	  				</div>
	  			</div>
	  			<div class="line-separator"></div>
	  			<div class="row">
	  				<div class="col-xs-12">
	  					<textarea class="form-control" rows="5" id="questionDisp" placeholder="Select a question from the list or enter new question."></textarea>
	  				</div>
	  			</div>
	  			<div class="row">
	  				<div class="col-xs-8">
	  					<input type="text" name='qtagList' placeholder='Tag List with weight' class="form-control" disabled="disabled"/>
	  				</div>
	  				<div class="col-xs-4">
	  					<select id="qTag"></select>
						<select id="qWgt">
							<option value="-10">-10</option>
							<option value="-9">-9</option>
							<option value="-8">-8</option>
							<option value="-7">-7</option>
							<option value="-6">-6</option>
							<option value="-5">-5</option>
							<option value="-4">-4</option>
							<option value="-3">-3</option>
							<option value="-2">-2</option>
							<option value="-1">-1</option>
							<option value="0" selected="selected">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">8</option>
							<option value="10">10</option>
						</select>
						<a id='add_qtag' class="btn btn-default" href="javascript:iDash.addqTag();"> <span class="glyphicon glyphicon-plus"></span></a>
	  				</div>
	  			</div>
	  			<div class="line-separator"></div>
	  			<div class="row">
					<div class="col-md-12 column">
						<table class="table table-bordered table-hover" id="getOption">
							<thead>
								<tr >
									<th class="text-center">
										#
									</th>
									<th class="text-center">
										Option Text
									</th>
									<th class="text-center">
										Correctness
									</th>
									<th class="text-center">
										Tags (Ex: tag1|wt,tag2|wt)
									</th>
									<th>
										Add Tag
									</th>
								</tr>
							</thead>
							<tbody>
								<tr id='opt0'>
									<td>
										1
									</td>
									<td>
										<input type="text" name='option0'  placeholder='Option' class="form-control"/>
									</td>
									<td>
										<input type="text" name='correctness0' placeholder='Correctness' class="form-control" value="0"/>
									</td>
									<td>
										<input type="text" name='tag0' placeholder='Tag List with weight' class="form-control" disabled="disabled"/>
									</td>
									<td>
										<select id="optTag0"></select>
										<select id="optWgt0">
											<option value="-10">-10</option>
											<option value="-9">-9</option>
											<option value="-8">-8</option>
											<option value="-7">-7</option>
											<option value="-6">-6</option>
											<option value="-5">-5</option>
											<option value="-4">-4</option>
											<option value="-3">-3</option>
											<option value="-2">-2</option>
											<option value="-1">-1</option>
											<option value="0" selected="selected">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">8</option>
											<option value="10">10</option>
										</select>
										<a id='add_tag0' class="btn btn-default" href="javascript:iDash.addTag(0);"> <span class="glyphicon glyphicon-plus"></span></a>
									</td>
								</tr>
			                    <tr id='opt1'></tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<a id="add_row" class="btn btn-default pull-left">Add Row</a><a id='delete_row' class="pull-right btn btn-default">Delete Row</a>
					</div>
				</div>
	  		</div>
  			<div class="col-xs-2">
				<div id="scheduleDatePicker"></div>
				<input type="hidden" id="scheduleDate" name="scheduleDate" />
				<input type="hidden" id="expiryDate" name="expiryDate" />
				<button type="submit" class="btn btn-success btn-block" onclick="javascipt:iDash.scheduleQuiz();" style="width:150px;"><span class="glyphicon glyphicon-off"></span> Schedule</button>
			</div>
  		</div>
	</div>
	<div id="perf" class="container-fluid">
  		<h1>Section 3</h1>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  		<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
	</div>
</body>
</html>