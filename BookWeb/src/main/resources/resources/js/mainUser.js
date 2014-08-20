var User = {
	addUser : function() {
		var user = {
			username : $('#username').val(),
			password : $('#password').val(),
			enabled : true
		}
		console.log("before calling post: " + JSON.stringify(user));
		$.ajax({
			type : "POST",
			url : '/saveUser',
			data : JSON.stringify(user),
			success : function(data) {
				console.log(data);
			},
			contentType : "application/json"
		});
	}
}