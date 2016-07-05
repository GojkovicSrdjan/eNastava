'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
	.controller('MainCtrl', ['$scope', '$route',  'gettextCatalog', function($scope, $route, gettextCatalog){

		  $scope.changeLanguage= function (l) {
				 gettextCatalog.currentLanguage= l;
				 $route.reload();
			};
}]);

