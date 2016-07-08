'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:ProfessorCtrl
 * @description
 * # ProfessorCtrl
 * Controller of the studentsClientApp
 */

myApp.controller('ProfessorCtrl', ['$scope', 'Restangular', '$routeParams', '$uibModal', '$log', '_', function($scope, Restangular, $routeParams, $uibModal, $log, _) {

	
	var paramID = $routeParams.id;
	$log.info(paramID);
	if(paramID!="new"){
		
		var profesorID = parseInt($routeParams.id);
		$log.info('routeParams:id '+ profesorID);
		
	    if (profesorID) {
	    	Restangular.one("profesori").customGET(profesorID).then(function (data) {
	    		$scope.professor = data;
			});
	    	
	    	Restangular.one("profesori", profesorID).getList("predmeti").then(function(entries) {
	    		$scope.teachings = entries;
	    	});
	    }
	}
	
    $scope.ok = function() {
      if ($scope.professor.profesorID) {
        Restangular.all('profesori').customPUT($scope.professor);
      } else {
        if(Restangular.all('profesori').post($scope.professor)){}
        else{
        	// callback za gresku sa servera
            $log.info('the student with such a cardNumber already exists');
        };
      }
    };

}]);