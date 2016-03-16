var qotd = {
		init : function(){
			console.log("Hi");
			$("#myBtn").click(function(){
				console.log("Hi");
		        $("#myModal").modal();
		    });
		},
		signOut : function(){
			var auth2 = gapi.auth2.getAuthInstance();
		    auth2.signOut().then(function () {
		      console.log('User signed out.');
		    });
		}
};