var FriendWeb = {
	reRenderFriends : function() {
		$('#friendContainer').html('');
		$.ajax({
			url : "/getFriends"
		}).done(function(result) {
			var templateHtml = $('#friendTemplate').html();
			var template = Handlebars.compile(templateHtml);
			for ( var i in result) {
				var model = result[i];
				var friendHtml = template(model);
				$('#friendContainer').append(friendHtml);
			}
		});
	},

	addFriend : function() {
		var name = $('#fname').val();
		var email = $('#email').val();
		$.ajax({
			type : "POST",
			url : "/saveFriend",
			data : {
				name : name,
				email : email
			}
		}).done(function() {
			$('#fname').val("");
			$('#email').val("");
			$('#addFriendModal').modal('hide');
			FriendWeb.reRenderFriends();
		});

	},

	showEditModal : function(friendId, name, email) {
		$('#editfid').val(friendId);
		$('#editfname').val(name);
		$('#editemail').val(email);
		$('#editFriendModal').modal('show');
	},

	editFriend : function() {

		var id = $('#editfid').val();
		var name = $('#editfname').val();
		var email = $('#editemail').val();
		$.ajax({
			type : "POST",
			url : "/saveEditFriend",
			data : {
				id : id,
				name : name,
				email : email
			}
		}).done(function() {
			$('#editFriendModal').modal('hide');
			FriendWeb.reRenderFriends();
		});
	},

	showDeleteFriendModal : function(friendId) {
		$('#delFId').val(friendId);
		$('#deleteFriendModal').modal('show');
	},

	deleteFriend : function() {
		var id = $('#delFId').val();
		$.ajax({
			type : "POST",
			url : "/deleteFriend",
			data : {
				id : id
			}
		}).done(function(result) {
			var model = result;
			$('#deleteF').remove(model);
			$('#deleteFriendModal').modal('hide');
			FriendWeb.reRenderFriends();
		});
	}
};

var search = {
	search : function(name) {
		var name = $('#search').val();
		$('#friendContainer').html('');
		$.ajax({
			type : "POST",
			url : "/searchFriend",
			data : {
				name : name
			}
		}).done(function(result) {

			var templateHtml = $('#friendTemplate').html();
			var template = Handlebars.compile(templateHtml);
			var currentLocation = window.location;
			for ( var i in result) {
				var model = result[i];
				var friendHtml = template(model);
				$('#friendContainer').append(friendHtml);
			}

		});
	}
}