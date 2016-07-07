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
      
    var ProfessorsModalCtrl = ['$scope', '$uibModalInstance', 'professor', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, professor, Restangular, $log, _) {
        $scope.professor = professor;
        if ($scope.professor.profesorID) {
        	Restangular.one("profesori", $scope.professor.profesorID).getList("predmeti").then(function(entries) {
        		$scope.teachings = entries;
        	});
        }

        $scope.ok = function() {
        	if ($scope.professor.profesorID) {
        		Restangular.all('profesori').customPUT($scope.professor).then(function (data) {
        			var index = _.indexOf($scope.professors, _.find($scope.professors, {id: $scope.professor.id}));
        			$scope.professors.splice(index, 1, data);
        		});
        	} else {
        		Restangular.all('profesori').post($scope.professor).then(function (data) {
        			$scope.professors.push(data);
              		$scope.professorsPage.push(data);
              		check($scope.professors);
                      if($scope.professorsPage.length>5){
                        	$scope.page($scope.currentPage+1);
                        };
        		},            
        		function() {
        			$log.info('');
        		});
        	}
        	$uibModalInstance.close('ok');
        };

        $scope.cancel = function() {
        	$uibModalInstance.dismiss('cancel');
        };
    }
    ];  
    
    $scope.openModal = function(professor) {

        if (!professor) {
        professor = {
        	zvanje: ''
            
          };
        }

        var modalInstance = $uibModal.open({
          templateUrl: 'views/modals/professor.html',
          controller: ProfessorsModalCtrl,
          scope: $scope,
          resolve: {
        	 professor: function() {
              return professor;
            }
          }
        });

        modalInstance.result.then(function(value) {
          $log.info('Modal finished its job at: ' + new Date() + ' with value: ' + value);
        }, function(value) {
          $log.info('Modal dismissed at: ' + new Date() + ' with value: ' + value);
        });
      };

  }]);