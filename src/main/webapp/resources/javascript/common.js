var qotd = {
		init : function(){
			
		},
		toPascalCase : function(str) {
		    var arr = str.split(/\s|_/);
		    for(var i=0,l=arr.length; i<l; i++) {
		        arr[i] = arr[i].substr(0,1).toUpperCase() + 
		                 (arr[i].length > 1 ? arr[i].substr(1).toLowerCase() : "");
		    }
		    return arr.join(" ");
		}
};