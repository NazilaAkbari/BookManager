'use strict';

function Config($routeProvider) {
	$routeProvider.when('/friend', {
		templateUrl : 'pages/friend/friend.html',
		controller : 'FriendCtrl'
	}).otherwise({
		templateUrl : 'pages/book/book.html',
		controller : 'BookCtrl'
	});
}

angular.module('bookWeb', [ 'ngRoute' ]).config(Config);
