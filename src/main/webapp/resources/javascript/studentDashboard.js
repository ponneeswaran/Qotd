var sDash = {
		globalVar : {
			context : "",
			user : "",
			quizList : {}
		},
		init : function(){
			sDash.initFunction();
			sDash.getSubscriptions();
			sDash.getNonSubscriptions();
		},
		initFunction : function(){
			$("#datepicker")
			.datepicker({
				todayHighlight: true,
				dateFormat: 'yyyy-mm-dd'
			})
			.on('changeDate', function(e) {
	            // Set the value for the date input
	            $("#selectedDate").val(e.date.getFullYear()+"-"+(e.date.getMonth()<10?"0"+(e.date.getMonth()+1):(e.date.getMonth()+1))+"-"+(e.date.getDate()<10?"0"+e.date.getDate():e.date.getDate()));
	            iDash.filterQuiz();
			});
		},
		getSubscriptions : function(){
			var userData = {"emailId": sDash.globalVar.user};
			
			$.ajax({
				type: 'POST',
				url: sDash.globalVar.context+'/getSubscriptions',
				data: JSON.stringify(userData),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					var tableBody = "";
					if(data.errorStatus==false){
						$("#qErrMsg").text(data.errorMessage);
						return;
					}
					sDash.globalVar.quizList=data;
					
					for(var i=0;i<data.qDetails.length;i++){
						tableBody = tableBody + "<tr>";
						tableBody = tableBody + '<td><a href="javascript:sDash.getQuestionsStud('+data.qDetails[i].quiz_id+')">' + qotd.toPascalCase(data.qDetails[i].quiz) + "</td>";
						tableBody = tableBody + "<td>" + data.qDetails[i].questions + "</td>";
						tableBody = tableBody + "</tr>";
					}
					
					$("#quizList > tbody").html(tableBody);
				},
				failure: function(failure){
					$("#qErrMsg").text("Network Error!");
				}
			});
		},
		getNonSubscriptions : function(){
			var userData = {"emailId": sDash.globalVar.user};
			
			$.ajax({
				type: 'POST',
				url: sDash.globalVar.context+'/getNonSubscriptions',
				data: JSON.stringify(userData),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					var tableBody = "";
					if(data.errorStatus==false){
						$("#qErrMsg").text(data.errorMessage);
						return;
					}
					sDash.globalVar.quizList=data;
					
					for(var i=0;i<data.qDetails.length;i++){
						tableBody = tableBody + "<tr>";
						tableBody = tableBody + '<td><a href="javascript:sDash.quizSubscribe('+data.qDetails[i].quiz_id+')">' + qotd.toPascalCase(data.qDetails[i].quiz) + "</td>";
						tableBody = tableBody + "<td>" + data.qDetails[i].emailId + "</td>";
						tableBody = tableBody + "<td>" + data.qDetails[i].subscriptions + "</td>";
						tableBody = tableBody + "</tr>";
					}
					
					if(tableBody!=""){
						$("#subsList > tbody").html(tableBody);
					}
				},
				failure: function(failure){
					$("#qErrMsg").text("Network Error!");
				}
			});
		},
		getQuestionsStud : function(quizId){
			var userData = {"quiz_id": quizId,"emailId" : sDash.globalVar.user};
			
			$.ajax({
				type: 'POST',
				url: sDash.globalVar.context+'/getQuestionsStud',
				data: JSON.stringify(userData),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					var tableBody = "";
					if(data.errorStatus==false){
						$("#qErrMsg").text(data.errorMessage);
						return;
					}
					
					sDash.globalVar.quizQuestions=data;
					for(var i=0;i<data.quesDetails.length;i++){
						tableBody = tableBody + "<tr>";
						tableBody = tableBody + "<td>" + data.quesDetails[i].question_text + "</td>";
						tableBody = tableBody + "<td>" + data.quesDetails[i].created_date + "</td>";
						tableBody = tableBody + "<td>" + (data.quesDetails[i].isAnswered==0?"No":"Yes") + "</td>";
						tableBody = tableBody + "<td>" + data.quesDetails[i].submissions + "</td>";
						tableBody = tableBody + "</tr>";
					}
					
					$("#quesList > tbody").html(tableBody);
					
				},
				failure: function(failure){
					$("#qErrMsg").text("Network Error!");
				}
			});
		},
		quizSubscribe : function(quizId){
			var userData = {"quiz_id": quizId,"emailId" : sDash.globalVar.user};
			
			$.ajax({
				type: 'POST',
				url: sDash.globalVar.context+'/quizSubscribe',
				data: JSON.stringify(userData),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					if(data.errorStatus==false){
						$("#qErrMsg").text(data.errorMessage);
						return;
					}
					sDash.getSubscriptions();
					sDash.getNonSubscriptions();
				},
				failure: function(failure){
					$("#qErrMsg").text("Network Error!");
				}
			});
		}
}