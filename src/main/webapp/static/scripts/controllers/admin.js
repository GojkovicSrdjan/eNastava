'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:AdminCtrl
 * @description
 * # AdminCtrl
 * Controller of the studentsClientApp
 */

myApp.controller('AdminCtrl', ['$scope', 'Restangular', '$routeParams', '$uibModal', '$log', '_', function($scope, Restangular, $routeParams, $uibModal, $log, _) {
		
	var paramID = $routeParams.id;
	$log.info(paramID);
	if(paramID!="new"){
		
		var adminID = parseInt($routeParams.id);
		$log.info('routeParams:id '+ adminID);
		
	    if (adminID) {
	    	Restangular.one("administratori").customGET(adminID).then(function (data) {
	    		$scope.admin = data;
			});
	    }
	}
	
    $scope.ok = function() {
      if ($scope.admin.adminID) {
        Restangular.all('administratori').customPUT($scope.admin);
      } else {
        if(Restangular.all('administratori').post($scope.admin)){}
        else{
        	// callback za gresku sa servera
        	$log.info('something went wrong!');
        };
      }
    };
    
}]);