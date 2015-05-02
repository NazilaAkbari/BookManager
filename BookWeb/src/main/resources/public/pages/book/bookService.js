'use strict';

function BookService($http) {
	return {
		getBooks : function() {
			return $http.get('/getBooks');
		},
		addBook : function(book) {
			return $http.post('/saveBook', book);
		}
	};

}

angular.module('bookWeb').service('BookService', ['$http',BookService]);