'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
	.controller('MainCtrl', ['$scope', '$route', '$rootScope', '$cookies', '$location', 'Restangular', 'gettextCatalog', '$uibModal',
	                         function($scope, $route, $rootScope, $cookies, $location, Restangular, gettextCatalog, $uibModal){
		
		$rootScope.loggedIn=$cookies.getObject("loggedIn");
		$rootScope.studentK=$cookies.getObject("student");
		$rootScope.profesorK=$cookies.getObject("profesor");
		$rootScope.profesorPredmeti=$cookies.getObject("profesorPredmeti");
		$rootScope.adminK=$cookies.getObject("admin");
		
		  $scope.changeLanguage= function (l) {
				 gettextCatalog.currentLanguage= l;
				 $route.reload();
			};
			
		$scope.logout= function (type) {
			$cookies.remove(type);
			$cookies.remove("loggedIn");
			$route.reload();
			$location.path("/")	;
		}	
			
		  $scope.openLoginType= function() {
		        var modalInstanceP = $uibModal.open({
		        	templateUrl: 'views/loginUserType.html',
		            controller: LoginCtrl,
		            scope: $scope

		          });
		};

		
		var LoginCtrl=['$scope', '$rootScope', '$uibModalStack', '$cookies', '$location', '$uibModalInstance', 'Restangular', '$log', '_',
		               function($scope, $rootScope,  $uibModalStack, $cookies, $location, $uibModalInstance, Restangular, $log, _) {
			
			$scope.cancel = function() {
		          $uibModalInstance.dismiss('cancel');
		        };
		        
		        $scope.login= function () {
					if($scope.type=="student"){
						$scope.student;
						Restangular.one('studenti').post("login", $scope.korisnik).then(function (data) {
							$cookies.putObject("loggedIn",true);
							$cookies.putObject('student', data);
							$route.reload();
							$location.path("/")	
					        $uibModalStack.dismissAll();
						});
					}else if($scope.type=="profesor"){
						Restangular.one('profesori').post("login", $scope.korisnik).then(function (data) {
							$cookies.putObject("loggedIn",true);
							$cookies.putObject('profesor', data);
							Restangular.one("profesori", data.profesorID).getList("predmeti").then(function(entries) {
					    		$cookies.putObject('profesorPredmeti', entries);
					    	});
							$route.reload();
							$location.path("/");
					        $uibModalStack.dismissAll();	
						});
						
					}else if($scope.type=="admin"){
						Restangular.one('administratori').post("login", $scope.korisnik).then(function (data) {
							$cookies.putObject("loggedIn",true);
							$cookies.putObject('admin', data);
							$route.reload();
							$location.path("/");
					        $uibModalStack.dismissAll();
							
						});
					}
					//pronadji na osnovu tipa ulogovanog
					//postavi ga u sesiju i u views postavi ogranicenja
					
					 $uibModalInstance.dismiss('cancel');
				}
  
				$scope.checkType= function (tip) {
					$scope.type=tip;
			        var modalInstance = $uibModal.open({
			        	templateUrl: 'views/login.html',
			            controller: LoginCtrl,
			            scope: $scope
			          });
				}; 
		}];
		
}]);