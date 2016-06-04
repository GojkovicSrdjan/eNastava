angular.module('studentsClientApp')
  .controller('ProfessorsCtrl', ['$scope', 'Restangular', '$uibModal', '$log', '_', function($scope, Restangular, $uibModal, $log, _) {
    Restangular.all("profesori").getList().then(function(entries) {
      $scope.professors = entries;
    });
    
    $scope.deleteProfessor = function(id) {
        Restangular.one("profesori", id).remove().then(function() {
          _.remove($scope.professors, {
            profesorID: id
          });
        }, function() {
          $log.info("");
        });
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