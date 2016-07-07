angular.module('studentsClientApp')
  .controller('ProfessorsCtrl', ['$scope', 'Restangular', '$uibModal', '$log', '_', function($scope, Restangular, $uibModal, $log, _) {
	  $scope.currentPage=0;
	  Restangular.all("profesori").getList().then(function(entries) {
      $scope.professors = entries;
      check(entries);
      $scope.page($scope.currentPage);
    });
    
    $scope.deleteProfessor = function(id) {
        Restangular.one("profesori", id).remove().then(function() {
          _.remove($scope.professors, {
            profesorID: id
          });
          _.remove($scope.professorsPage,{
        	  profesorID:id
  		});
          if($scope.professorsPage.length==0){
  			$scope.page($scope.currentPage-1);
  		}
  		else{
          	$scope.page($scope.currentPage);
  		}
  		
  		check($scope.professors);
        }, function() {
          $log.info("");
        });
      };
      
      
      $scope.page= function page(currentPage){
      	$scope.currentPage=currentPage;
          Restangular.all("profesori").getList({page: currentPage,size: 5 }).then(function(entries) {
              $scope.professorsPage = entries;
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