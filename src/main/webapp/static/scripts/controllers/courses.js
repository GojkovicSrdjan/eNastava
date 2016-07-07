'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:CoursesCtrl
 * @description
 * # CoursesCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp').controller('CoursesCtrl', 
		['$scope', 'Restangular', '$uibModal', '$log', '_', function($scope, Restangular, $uibModal, $log, _) {
	$scope.currentPage=0;
	
	Restangular.all("predmeti").getList().then(function(entries) {
		//ukupan broj kurseva u bazi, za izracunavanje broja stranica
		$scope.courses = entries;
		check(entries);
		//kursevi na pojedincanoj stranici koji ce biti prosledjeni modelu 
		$scope.page($scope.currentPage);
	});
	
	$scope.deleteCourse = function(id) {
		Restangular.one("predmeti", id).remove().then(function() {
			_.remove($scope.courses, {
				predmetID: id
			});
			_.remove($scope.coursesPage,{
				predmetID:id
			});
				//ukoliko nema kurseva na stranici redirektujemo se na prethodnu
				if($scope.coursesPage.length==0){
					$scope.page($scope.currentPage-1);
		        	//$scope.pages.pop();
				}
				else{
					//izvlacenje novih 5 kurseva za trenutnu stranicu
		        	$scope.page($scope.currentPage);
				}
				
				//posle svakog brisanja se ponovo izracunava broj stranica
				check($scope.courses);

		}, function() {
			$log.info("something went wrong");
		});
	};
	
    $scope.page= function page(currentPage){
    	$scope.currentPage=currentPage;
        Restangular.all("predmeti").getList({page: currentPage,size: 5 }).then(function(entries) {
            $scope.coursesPage = entries;//coursesPage
        });
    };
    
    function check(entries) {
    	
        var p=entries.length/5;
        if(p%1>0 && p%1<0.5){
      	  p++;
        };
        var pages=Math.round(p);
        $scope.pageSum=pages;
        $scope.pages= Array.from(new Array(pages), (x,i) => i);
    };

}]);
