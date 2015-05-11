'use strict';

function BookCtrl(BookService) {

	var vm = this;

	function loadBooks() {
		BookService.getBooks().success(function(result) {
			vm.books = result;
		});
	}

	loadBooks();

	vm.addBook = function() {
		BookService.addBook(vm.newBook).success(function() {
			loadBooks();
		});
	};
	
	vm.showModal=false;
	
	vm.showModal = function(){
		alert("hi");
		vm.showModal=true;
		alert(vm.showModal);
	}

}

angular.module('bookWeb').controller('BookCtrl', [ 'BookService', BookCtrl ]);
