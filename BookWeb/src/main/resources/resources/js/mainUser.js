var User = {
	addUser : function() {
		var user = {
			name : $('#name').val(),
			username : $('#username').val(),
			password : $('#password').val(),
			confirmPassword : $('#confirmPassword').val(),
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