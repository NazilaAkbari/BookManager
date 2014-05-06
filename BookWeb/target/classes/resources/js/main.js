var BookWeb = {
	reRenderBooks : function() {
		$('#bookContainer').html('');
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
		$.ajax({
			type : "POST",
			url : "/saveBook",
			data : {
				name : name,
				author : author
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
		$.ajax({
			type : "POST",
			url : "/saveEditBook",
			data : {
				id : id,
				name : name,
				author : author
			}
		}).done(function() {
			$('#editModal').modal('hide');
			BookWeb.reRenderBooks();
		});
	},

	showLendModal : function(bookId, owner) {
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
						$('#owner').val(owner);

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

	deleteBook : function(id) {
		$.ajax({
			type : "POST",
			url : "/deleteBook",
			data : {
				id : id
			}
		}).done(function(result) {
			var model = result;
			$('#delete').remove(model);
			BookWeb.reRenderBooks();
		})
	},

	searchBook : function(name) {
		var name = $('#search').val();
		$('#bookContainer').html('');
		$.ajax({
			type : "POST",
			url : "/searchBook",
			data : {
				name : name
			}
		}).done(function(result) {
			for ( var i in result) {
				var templateHtml = $('#bookTemplate').html();
				var template = Handlebars.compile(templateHtml);
				for ( var i in result) {
					var model = result[i];
					var bookHtml = template(model);
					$('#bookContainer').append(bookHtml);
				}
			}
		})
	}
};