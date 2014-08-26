var User = {
	addUser : function() {
		var user = {
			name : $('#name').val(),
			username : $('#username').val(),
			password : $('#password').val(),
			confirmPassword : $('#confirmPassword').val(),
			enabled : false
		}
		console.log("before calling post: " + JSON.stringify(user));
		$.ajax({
			type : "POST",
			url : '/saveUser',
			data : JSON.stringify(user),
			success : function(data) {
				window.location = "/success";
			},
			error : function(xhr, errorType, exception) {
				if (xhr.status == 400) {

					var errorMessage = exception || xhr.statusText;
					$('#fail').show();
					$('#userExist').hide();

				} else if (xhr.status == 406) {

					var errorMessage = exception || xhr.statusText;
					$('#userExist').show();
					$('#fail').hide();
				}

			},
			contentType : "application/json"
		});

	}
}