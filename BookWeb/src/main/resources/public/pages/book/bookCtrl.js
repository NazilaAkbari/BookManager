'use strict';

function BookCtrl($scope, BookService) {

	function loadBooks() {
		BookService.getBooks().success(function(result) {
			$scope.books = result;
		});
	}

	loadBooks();

	$scope.addBook = function() {
		BookService.addBook($scope.newBook).success(function() {
			loadBooks();
		});
	};

}

angular.module('bookWeb').controller('BookCtrl',
		[ '$scope', 'BookService', BookCtrl ]);