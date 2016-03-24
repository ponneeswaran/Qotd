var hPage = {
		init : function(){
			hPage.initFunction();
		},
		initFunction : function(){
			$("#signUp").click(function(){
				$("#signUpUsrname").val("");
				$("#signUpPsw").val("");
				$("#signUpPsw2").val("");
				$("#signUpErrMsg").text("");
		        $("#signUpModal").modal();
		    });
			$("#signUpPsw2").on('keyup',function(){
				if($("#signUpPsw2").val()!=$("#signUpPsw").val()){
					$("#signUpErrMsg").text("passwords does not match");
				}
				else{
					$("#signUpErrMsg").text("");
				}
			});
		},
		globalVariables : function(){
			
		}
};