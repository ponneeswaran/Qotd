var hPage = {
	globalVar : {
		pattern : /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i,
		context : ""
	},
	init : function(){
		hPage.initFunction();
	},
	initFunction : function(){
		$("#signUp").click(function(){
			$("#signUpUsrname").val("");
			$("#signUpPsw").val("");
			$("#signUpPsw2").val("");
			$("#signUpUsrErrMsg").text("");
			$("#signUpPswErrMsg").text("");
			$("#signUpPsw2ErrMsg").text("");
	        $("#signUpModal").modal();
	    });
		$("#signUpUsrname").on('change',function(){
			if(!hPage.globalVar.pattern.test($("#signUpUsrname").val())){
				$("#signUpUsrErrMsg").text("Invalid email address format");
			}
			else{
				$("#signUpUsrErrMsg").text("");
			}
		});
		$("#signUpPsw").on('keyup',function(){
			$("#signUpPsw2ErrMsg").text("");
		});
		$("#signUpPsw2").on('keyup',function(){
			if($("#signUpPsw2").val()!=$("#signUpPsw").val()){
				$("#signUpPsw2ErrMsg").text("passwords does not match");
			}
			else{
				$("#signUpPsw2ErrMsg").text("");
			}
		});
		$("#fgtPsw").click(function(){
			$("#fgtPswUsrname").val("");
	        $("#fgtPswModal").modal();
	    });
	},
	onSignUp : function(){
		var usrFlag = true;
		var pswFlag = true;
		var psw2Flag = true;
		if(!hPage.validation.textFieldReq("signUpUsrname")){
			$("#signUpUsrErrMsg").text("is required");
			usrFlag = false;
		}
		if(!hPage.validation.textFieldReq("signUpPsw")){
			$("#signUpPswErrMsg").text("is required");
			pswFlag = false;
		}
		if(!hPage.validation.textFieldReq("signUpPsw2")){
			$("#signUpPsw2ErrMsg").text("is required");
			psw2Flag = false;
		}
		if(!usrFlag && !pswFlag && !psw2Flag){
			return false;
		}
		else{
			$("#signUpUsrErrMsg").text("");
			$("#signUpPswErrMsg").text("");
			$("#signUpPsw2ErrMsg").text("");
		}
		
		var emailId = $("#signUpUsrname").val();
		var password = $("#signUpPsw").val();

		var userData = {"emailId": emailId,"password": password};
		
		$.ajax({
			type: 'POST',
			url: hPage.globalVar.context+'/userSignUp',
			data: JSON.stringify(userData),
			contentType: "application/json",
			dataType: "json",
			success: function(data){
				if(data.errorStatus==false){
					$("#signUpUsrErrMsg").text(data.errorMessage);
				}
			},
			failure: function(failure){
				$("#signUpUsrErrMsg").text("Network Error!");
			}
		});
	},
	onLogin: function(){
		var usrFlag = true;
		var pswFlag = true;
		if(!hPage.validation.textFieldReq("usrname")){
			$("#usrErrMsg").text("is required");
			usrFlag = false;
		}
		if(!hPage.validation.textFieldReq("psw")){
			$("#pswErrMsg").text("is required");
			pswFlag = false;
		}
		if(!usrFlag && !pswFlag){
			return false;
		}
		else{
			$("#usrErrMsg").text("");
			$("#pswErrMsg").text("");
		}
		
		var emailId = $("#usrname").val();
		var password = $("#psw").val();

		var userData = {"emailId": emailId,"password": password};
		
		$.ajax({
			type: 'POST',
			url: hPage.globalVar.context+'/userLogin',
			data: JSON.stringify(userData),
			contentType: "application/json",
			dataType: "json",
			success: function(data){
				if(data.errorStatus==false){
					$("#usrErrMsg").text(data.errorMessage);
					return false;
				}else{
					if(data.roleId==2){
						$(location).attr('href',hPage.globalVar.context+'/instructorDashboard');
					}
					else{
						$(location).attr('href',hPage.globalVar.context+'/studentDashboard');
					}
				}
			},
			failure: function(failure){
				$("#usrErrMsg").text("Network Error!");
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