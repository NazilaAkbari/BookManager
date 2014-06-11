var BookWeb = {
	reRenderBooks : function() {
		$('#bookContainer').html('');
		Handlebars.registerHelper('ifEqual', function(s1, s2, options) {
			if (s1 == s2) {
				return options.fn(this)
			} else {
				return options.inverse(this);
			}
		});
		$.ajax({
			url : "/getBooks"
		}).done(function(result) {
			var templateHtml = $('#bookTemplate').html();
			var template = Handlebars.compile(templateHtml);
			for ( var i in result) {
				var model = result[i];
				var bookHtml = template(model);
				$('#bookContainer').append(bookHtml);
			}
		});
	},

	addBook : function() {
		var name = $('#name').val();
		var author = $('#author').val();
		var readStatus = $('#readStatus').val();
		$.ajax({
			type : "POST",
			url : "/saveBook",
			data : {
				name : name,
				author : author,
				readStatus : readStatus
			}
		}).done(function() {
			$('#addModal').modal('hide');
			BookWeb.reRenderBooks();
		});

	},

	showEditModal : function(bookId, name, author) {
		$('#eid').val(bookId);
		$('#ename').val(name);
		$('#eauthor').val(author);
		$('#editModal').modal('show');
	},

	editBook : function() {

		var id = $('#eid').val();
		var name = $('#ename').val();
		var author = $('#eauthor').val();
		var readStatus = $('#editReadStatus').val();
		$.ajax({
			type : "POST",
			url : "/saveEditBook",
			data : {
				id : id,
				name : name,
				author : author,
				readStatus : readStatus
			}
		}).done(function() {
			$('#editModal').modal('hide');
			BookWeb.reRenderBooks();
		});
	},

	showLendModal : function(bookId) {
		$('#bookId').val(bookId);
		$.ajax({
			url : "/getFriends"
		}).done(
				function(result) {
					$('#owner').html('');
					for ( var i in result) {
						var model = result[i];
						$('#owner').append(
								'<option value="' + model.id + '" >'
										+ model.name + '</option>');
					}
				});
		$('#lendModal').modal('show');

	},

	lendBook : function() {
		var id = $('#bookId').val();
		var owner = $('#owner').val();
		$.ajax({
			type : "POST",
			url : "/saveLendBook",
			data : {
				bookid : id,
				friendid : owner
			}
		}).done(function() {
			$('#lendModal').modal('hide');
			BookWeb.reRenderBooks();
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
			BookWeb.reRenderBooks();
		})
	},

	showDeleteModal : function(bookId) {
		$('#delId').val(bookId);
		$('#deleteModal').modal('show');
	},

	deleteBook : function() {
		var id = $('#delId').val();
		$.ajax({
			type : "POST",
			url : "/deleteBook",
			data : {
				id : id
			}
		}).done(function(result) {
			var model = result;
			$('#delete').remove(model);
			$('#deleteModal').modal('hide');
			BookWeb.reRenderBooks();
		})
	},

	reRenderReadBooks : function() {
		$('#bookContainer').html('');
		Handlebars.registerHelper('ifEqual', function(s1, s2, options) {
			if (s1 == s2) {
				return options.fn(this)
			} else {
				return options.inverse(this);
			}
		});
		$.ajax({
			url : "/getBooks"
		}).done(function(result) {
			var templateHtml = $('#bookTemplate').html();
			var template = Handlebars.compile(templateHtml);
			for ( var i in result) {
				var model = result[i];
				var bookHtml = template(model);
				$('#bookContainer').append(bookHtml);
			}
		});
	}
};

var search = {
	search : function(name) {
		var name = $('#search').val();
		$('#bookContainer').html('');
		$.ajax({
			type : "POST",
			url : "/searchBook",
			data : {
				name : name
			}
		}).done(function(result) {

			var templateHtml = $('#bookTemplate').html();
			var template = Handlebars.compile(templateHtml);
			var currentLocation = window.location;
			for ( var i in result) {
				var model = result[i];
				var bookHtml = template(model);
				$('#bookContainer').append(bookHtml);
			}

		})
	}
}