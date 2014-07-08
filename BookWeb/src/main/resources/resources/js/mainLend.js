var LendBookWeb = {
	reRenderBooks : function() {
		$('#lendBookContainer').html('');
		$.ajax({
			url : "/lendBooks"
		}).done(function(result) {
			var templateHtml = $('#lendTemplate').html();
			var template = Handlebars.compile(templateHtml);
			for ( var i in result) {
				var model = result[i];
				var bookHtml = template(model);
				$('#lendBookContainer').append(bookHtml);
			}
		});
	},

	returnBook : function(bookId) {
		$.ajax({
			type : "POST",
			url : "/returnBook",
			data : {
				id : bookId
			}
		}).done(function() {
			LendBookWeb.reRenderBooks();
		})
	}

};

var search = {
	search : function(name) {
		var name = $('#search').val();
		$('#lendBookContainer').html('');
		$.ajax({
			type : "POST",
			url : "/searchBook",
			data : {
				name : name
			}
		}).done(function(result) {

			var templateHtml = $('#lendTemplate').html();
			var template = Handlebars.compile(templateHtml);
			var currentLocation = window.location;
			for ( var i in result) {
				var model = result[i];
				var bookHtml = template(model);
				$('#lendBookContainer').append(bookHtml);
			}

		})
	}
}