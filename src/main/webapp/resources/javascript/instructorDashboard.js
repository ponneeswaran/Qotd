var iDash = {
		globalVar : {
			context : "",
			user : "",
			quizList : {},
			quizQuestions : {},
			javaTopics : {},
			optionCount : 1
		},
		init : function(){
			iDash.getJavaTopics();
			iDash.initFunction();
			iDash.scheduledQuizzes();
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
			$("#scheduleDatePicker")
				.datepicker({
					todayHighlight: true,
					dateFormat: 'yyyy-mm-dd'
				})
				.on('changeDate', function(e) {
		            // Set the value for the date input
		            $("#selectedDate").val(e.date.getFullYear()+"-"+(e.date.getMonth()<10?"0"+(e.date.getMonth()+1):(e.date.getMonth()+1))+"-"+(e.date.getDate()<10?"0"+e.date.getDate():e.date.getDate()));
				});
			var today = new Date();
			$("#selectedDate").val(today.getFullYear()+"-"+(today.getMonth()<10?"0"+(today.getMonth()+1):(today.getMonth()+1))+"-"+(today.getDate()<10?"0"+today.getDate():today.getDate()));
			today.setDate(today.getDate()+1);
			$("#expiryDate").val(today.getFullYear()+"-"+(today.getMonth()<10?"0"+(today.getMonth()+1):(today.getMonth()+1))+"-"+(today.getDate()<10?"0"+today.getDate():today.getDate()));
			
			$("#javaTopics").on('change',function(){
				if(this.value != "0"){
					iDash.getQuestionsonTopic(this.value);
				}else{
					$("#questionGroup").html("");
				}
			});
			$("#add_row").click(function(){
				$('#opt'+iDash.globalVar.optionCount).html("<td>"+ (iDash.globalVar.optionCount+1) +"</td><td><input name='option"+iDash.globalVar.optionCount+"' type='text' placeholder='Option' class='form-control input-md'  /> </td><td><input  name='correctness"+iDash.globalVar.optionCount+"' type='text' placeholder='Correctness'  class='form-control input-md'></td><td><input  name='tag"+iDash.globalVar.optionCount+"' type='text' placeholder='Tag List with Weight'  class='form-control input-md' disabled='disabled'></td><td><select id='optTag"+iDash.globalVar.optionCount+"'></select><select id='optWgt"+iDash.globalVar.optionCount+"'></select><a id='add_tag"+iDash.globalVar.optionCount+"' class='btn btn-default'  href='javascript:iDash.addTag("+iDash.globalVar.optionCount+");'> <span class='glyphicon glyphicon-plus'></span></a></td>");
				$("#optTag"+iDash.globalVar.optionCount).append('<option value="0">- SELECT -</option>');
				$.each(iDash.globalVar.javaTopics.list_obj ,function(index, value){
					$('#optTag'+iDash.globalVar.optionCount).append('<option value="'+value+'">'+qotd.toPascalCase(value)+'</option>');
				});
				for(var i=-10;i<11;i++){
					if(i!=0){
						$('#optWgt'+iDash.globalVar.optionCount).append("<option value="+i+">"+i+"</option>");
					}else{
						$('#optWgt'+iDash.globalVar.optionCount).append("<option value="+i+" selected='selected'>"+i+"</option>");
					}
				}
				$('#getOption').append('<tr id="opt'+(iDash.globalVar.optionCount+1)+'"></tr>');
				iDash.globalVar.optionCount++; 
			});
			$("#delete_row").click(function(){
				if(iDash.globalVar.optionCount>1){
					$("#opt"+(iDash.globalVar.optionCount-1)).html('');
					iDash.globalVar.optionCount--;
				}
			});
		},
		scheduledQuizzes : function(){
			var userData = {"emailId": iDash.globalVar.user};
			
			$.ajax({
				type: 'POST',
				url: iDash.globalVar.context+'/scheduledQuizzes',
				data: JSON.stringify(userData),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					var tableBody = "";
					if(data.errorStatus==false){
						$("#qErrMsg").text(data.errorMessage);
						return;
					}
					iDash.globalVar.quizList=data;
					
					for(var i=0;i<data.qDetails.length;i++){
						tableBody = tableBody + "<tr>";
						tableBody = tableBody + '<td><a href="javascript:iDash.getQuestions('+data.qDetails[i].quiz_id+')">' + qotd.toPascalCase(data.qDetails[i].quiz) + "</td>";
						tableBody = tableBody + "<td>" + data.qDetails[i].subscriptions + "</td>";
						tableBody = tableBody + "</tr>";
					}
					
					$("#quizList > tbody").html(tableBody);
					
					$("#quizListOpt").append('<option value="0">- SELECT -</option>');
    	        	$.each(data.qDetails ,function(index, value){
    	        		$("#quizListOpt").append('<option value="'+value.quiz_id+'">'+qotd.toPascalCase(value.quiz)+'</option>');
    	        	});
				},
				failure: function(failure){
					$("#qErrMsg").text("Network Error!");
				}
			});
		},
		getQuestions : function(quizId){
			var userData = {"quiz_id": quizId};
			
			$.ajax({
				type: 'POST',
				url: iDash.globalVar.context+'/getQuestions',
				data: JSON.stringify(userData),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					var tableBody = "";
					if(data.errorStatus==false){
						$("#qErrMsg").text(data.errorMessage);
						return;
					}
					
					iDash.globalVar.quizQuestions=data;
					if(!iDash.globalVar.fromSchedule){
						for(var i=0;i<data.quesDetails.length;i++){
							tableBody = tableBody + "<tr>";
							tableBody = tableBody + "<td>" + data.quesDetails[i].question_text + "</td>";
							tableBody = tableBody + "<td>" + data.quesDetails[i].created_date + "</td>";
							tableBody = tableBody + "<td>" + data.quesDetails[i].submissions + "</td>";
							tableBody = tableBody + "</tr>";
						}
						
						$("#quesList > tbody").html(tableBody);
					}
					else{
						$.each(data.quesDetails ,function(index, value){
							tableBody = tableBody + '<a href="javascript:iDash.selectQuestion('+value.question_id+');" class="list-group-item">'+value.question_text+'</a>';
						});
						$("#questionGroup").html(tableBody);
						iDash.globalVar.fromSchedule=false;
					}
				},
				failure: function(failure){
					$("#qErrMsg").text("Network Error!");
				}
			});
		},
		filterQuiz : function(){
			var tableBody = "";
			for(var i=0;i<iDash.globalVar.quizQuestions.quesDetails.length;i++){
				if(iDash.globalVar.quizQuestions.quesDetails[i].created_date==$("#selectedDate").val()){
					tableBody = tableBody + "<tr>";
					tableBody = tableBody + "<td>" + iDash.globalVar.quizQuestions.quesDetails[i].question_text + "</td>";
					tableBody = tableBody + "<td>" + iDash.globalVar.quizQuestions.quesDetails[i].created_date + "</td>";
					tableBody = tableBody + "<td>" + iDash.globalVar.quizQuestions.quesDetails[i].submissions + "</td>";
					tableBody = tableBody + "</tr>";
				}
			}
			$("#quesList > tbody").html(tableBody);
		},
		getJavaTopics : function(){
			$.ajax({
				type: 'GET',
				url: iDash.globalVar.context+'/getJavaTopics',
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					var listBody = "";
					iDash.globalVar.javaTopics = data;
					$("#javaTopics").append('<option value="0">- SELECT -</option>');
    	        	$.each(data.list_obj ,function(index, value){
    	        		$("#javaTopics").append('<option value="'+value+'">'+qotd.toPascalCase(value)+'</option>');
    	        	});
    	        	$("#optTag0").append('<option value="0">- SELECT -</option>');
    	        	$.each(iDash.globalVar.javaTopics.list_obj ,function(index, value){
    					$('#optTag0').append('<option value="'+value+'">'+qotd.toPascalCase(value)+'</option>');
    				});
    	        	$("#qTag").append('<option value="0">- SELECT -</option>');
    	        	$.each(iDash.globalVar.javaTopics.list_obj ,function(index, value){
    					$('#qTag').append('<option value="'+value+'">'+qotd.toPascalCase(value)+'</option>');
    				});
				},
				failure: function(failure){
					$("#q2ErrMsg").text("Network Error!");
				}
			});
		},
		getQuestionsonTopic : function(tag){
			var userData = {"tagName": tag};
			
			$.ajax({
				type: 'POST',
				url: iDash.globalVar.context+'/getQuestionsonTopic',
				data: JSON.stringify(userData),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					var tableBody = "";
					if(data.errorStatus==false){
						$("#q2ErrMsg").text(data.errorMessage);
						return;
					}
					
					iDash.globalVar.quizQuestions=data;
					
					$.each(data.quesDetails ,function(index, value){
						tableBody = tableBody + '<a href="javascript:iDash.selectQuestion('+value.question_id+');" class="list-group-item">'+value.question_text+'</a>';
					});
					$("#questionGroup").html(tableBody);
				},
				failure: function(failure){
					$("#q2ErrMsg").text("Network Error!");
				}
			});
		},
		selectQuestion : function(question_id){
			$.each(iDash.globalVar.quizQuestions.quesDetails ,function(index, value){
				if(value.question_id==question_id){
					$("#questionDisp").val(value.question_text);
				}
			});
		},
		addTag : function(num){
			var tagVal = $("input[name=tag"+num+"]").val();
			if(tagVal!=""){
				tagVal += ",";
			}
			tagVal += $("#optTag"+num).val()+"|"+$("#optWgt"+num).val();
			$("input[name=tag"+num+"]").val(tagVal);
		},
		addqTag : function(num){
			var tagVal = $("input[name=qtagList]").val();
			if(tagVal!=""){
				tagVal += ",";
			}
			tagVal += $("#qTag").val()+"|"+$("#qWgt").val();
			$("input[name=qtagList]").val(tagVal);
		},
		scheduleQuiz : function(){
			var quiz_id = $("#quizListOpt").val();
			var quiz = $("#quizName").text();
			if(!iDash.validation.textFieldReq("quizName") && quiz_id == 0){
				$("#q2ErrMsg").text("Please select a quiz or create new quiz");
				return;
			}
			if(quiz_id != 0){
				$.each(iDash.globalVar.quizList.qDetails ,function(index, value){
					if(value.quiz_id==quiz_id){
						quiz = value.quiz;
					}
				});
			}
			var java_topic = $("#javaTopics").val();
			if(java_topic==0){
				$("#q2ErrMsg").text("Please select a Java Topic.");
				return;
			}
			var question = $("#questionDisp").val();
			var questionTag = $("input[name=qtagList]").val();
			var optionList = [];
			var createdDate = $("#selectedDate").val();
			var expiryDate = $("#expiryDate").val();
			
			for(var i=0;i<iDash.globalVar.optionCount;i++){
				var optionText = $("input[name=option"+i+"]").val();
				var correctness = $("input[name=correctness"+i+"]").val();
				var optTag = $("input[name=tag"+i+"]").val();
				optionList.push({"optionText":optionText,"correctness":correctness,"optTag":optTag});
			}
			var data = {"emailId": iDash.globalVar.user,
						"quiz_id": quiz_id,
						"quiz": quiz,
						"question":question,
						"questionTag":questionTag,
						"createdDate":createdDate,
						"expiryDate":expiryDate,
						"options":optionList};
			
			$.ajax({
				type: 'POST',
				url: iDash.globalVar.context+'/scheduleQuiz',
				data: JSON.stringify(data),
				contentType: "application/json",
				dataType: "json",
				success: function(data){
					if(data.errorStatus==false){
						$("#q2ErrMsg").text(data.errorMessage);
						return;
					}
				},
				failure: function(failure){
					$("#q2ErrMsg").text("Network Error!");
				}
			});
		},
		validation : {
			textFieldReq : function(id){
				if($('#'+id).val()==""){
					return false;
				}
				else{
					return true;
				}
			}
		}
};