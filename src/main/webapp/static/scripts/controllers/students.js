'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:StudentsCtrl
 * @description
 * # StudentsCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
  .controller('StudentsCtrl', ['$scope', 'Restangular', '$uibModal', '$log', '_', function($scope, Restangular, $uibModal, $log, _) {

	  $scope.currentPage=0;
    //Restangular koristimo za rest pozive
    Restangular.all("studenti").getList().then(function(entries) {
      $scope.students = entries;
      check(entries);
      $scope.page($scope.currentPage);
    });

    $scope.deleteStudent = function(id) {
      Restangular.one("studenti", id).remove().then(function() {
        // uklanjamo studenta sa zadatim id-om iz kolekcije
        _.remove($scope.students, {
          studentID: id
        });
        _.remove($scope.studentsPage,{
        	studentID:id
		});
        if($scope.studentsPage.length==0){
			$scope.page($scope.currentPage-1);
		}
		else{
        	$scope.page($scope.currentPage);
		}
		
		check($scope.students);
      }, function() {
    	//Nece se nikad prikazati
        $log.info("the student cannot be removed since they are enrolled to some courses");
      });
    };
    
    $scope.page= function page(currentPage){
    	$scope.currentPage=currentPage;
        Restangular.all("studenti").getList({page: currentPage,size: 5 }).then(function(entries) {
            $scope.studentsPage = entries;
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
