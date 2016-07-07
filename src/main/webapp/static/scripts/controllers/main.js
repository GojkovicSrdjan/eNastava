'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
	.controller('MainCtrl', ['$scope', '$route', 'Restangular', 'gettextCatalog', '$uibModal', function($scope, $route, Restangular, gettextCatalog, $uibModal){
		
		  $scope.changeLanguage= function (l) {
				 gettextCatalog.currentLanguage= l;
				 $route.reload();
			};
			
		  $scope.openLoginType= function() {
		        var modalInstance = $uibModal.open({
		        	templateUrl: 'views/loginUserType.html',
		            controller: LoginCtrl,
		            scope: $scope,
//		            resolve: {
//		            	eRacun: function() {
//		                return eRacun;
//		              }
//		            }
		          });
		};

		
		var LoginCtrl=['$scope', '$cookies','$uibModalInstance', 'Restangular', '$log', '_',
		               function($scope, $cookies, $uibModalInstance, Restangular, $log, _) {
			
			$scope.cancel = function() {
		          $uibModalInstance.dismiss('cancel');
		        };
		        
		        $scope.login= function () {
//					$scope.student.korisnik=$scope.korisnik;
//					$scope.student;
					//proveri tip korisnika
					if($scope.type=="student"){
						$scope.student;
						Restangular.one('studenti').post("login", $scope.korisnik).then(function (data) {
							$cookies.put("ulogovniStudent", data);
							var ulogovaniStudent= $cookies.get('ulogovniStudent');
							$scope.nesto;
						});
					}else if($scope.type=="profesor"){
						Restangular.one('profesori').post("login", $scope.korisnik).then(function (data) {
							$scope.ulogovan=data;
						});
					}else if($scope.type=="admin"){
						Restangular.one('administratori').post("login", $scope.korisnik).then(function (data) {
							$scope.ulogovan=data;
						});
					}
					//pronadji na osnovu tipa ulogovanog
					//postavi ga u sesiju i u views postavi ogranicenja
				}
  
				$scope.checkType= function (tip) {
					$scope.type=tip;
					
			        var modalInstance = $uibModal.open({
			        	templateUrl: 'views/login.html',
			            controller: LoginCtrl,
			            scope: $scope,
//			            resolve: {
//			            	eRacun: function() {
//			                return eRacun;
//			              }
//			            }
			          });
				}; 
		}];
		
}]);