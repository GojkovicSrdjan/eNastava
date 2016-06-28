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
    //Restangular koristimo za rest pozive
    Restangular.all("studenti").getList().then(function(entries) {
      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
      $scope.students = entries;
    });

    $scope.deleteStudent = function(id) {
      Restangular.one("studenti", id).remove().then(function() {
        // uklanjamo studenta sa zadatim id-om iz kolekcije
        _.remove($scope.students, {
          studentID: id
        });
      }, function() {
        $log.info("the student cannot be removed since they are enrolled to some courses");
      });
    };

    var StudentsModalCtrl = ['$scope', '$uibModalInstance', 'student', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, student, Restangular, $log, _) {
        $scope.student = student;
        if ($scope.student.studentID) {
          Restangular.one("studenti", $scope.student.studentID).getList("predmeti").then(function(entries) {
            $scope.enrollments = entries;
          });
        }

        $scope.ok = function() {
          if ($scope.student.studentID) {
            Restangular.all('studenti').customPUT($scope.student).then(function (data) {
              var index = _.indexOf($scope.students, _.find($scope.students, {id: $scope.student.id}));
              $scope.students.splice(index, 1, data);
            });
          } else {
            Restangular.all('studenti').post($scope.student).then(function (data) {
              $scope.students.push(data);
            },
              // callback za gresku sa servera
              function() {
                $log.info('the student with such a cardNumber already exists');
              });
          }
          $uibModalInstance.close('ok');
        };

        $scope.cancel = function() {
          $uibModalInstance.dismiss('cancel');
        };
        
        var StudentViewCourseModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
        	
        	$scope.student = student;
        	if ($scope.course.predmetID) {
        		Restangular.one("predmeti", $scope.course.predmetID).getList("studenti").then(function(entries) {
        			$scope.enrollments = entries;
        		});
        		Restangular.one("predmeti", $scope.course.predmetID).getList("profesori").then(function (entries) {
        			$scope.teachings=entries;
        		});
        		Restangular.one("predmeti", $scope.course.predmetID).getList("tipobaveze").then(function (entries) {
        			$scope.tasks=entries;
        		});
        	}
        }];
        
        $scope.openModalP = function(predmet) {
        	$scope.course = predmet;
            var modalInstance = $uibModal.open({
            	templateUrl: 'views/modals/studentViewCourse.html',
            	controller: StudentViewCourseModalCtrl,
            	scope: $scope,
            	resolve: {
            		student: function() {
            			return student;
            		}
            	}
            });
        };
      }
    ];
    
    var ERacunModalCtrl =['$scope', '$uibModalInstance', 'eRacun', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, eRacun, Restangular, $log, _) {
    	$scope.eRacun=eRacun;
        Restangular.one("eracun", $scope.eRacun.eRacunID).getList("uplate").then(function(entries) {
            $scope.uplate = entries;
          });
        
        $scope.updataERacun= function () {
			if($scope.eRacun.eRacunID){
				Restangular.all("eracun").customPUT($scope.eRacun).then(function (data) {
					$scope.eRacun
					
				});
			}
		};
	    $scope.deleteUplata = function(id) {
	        Restangular.one("uplate", id).remove().then(function() {
	          _.remove($scope.uplate, {
	        	  uplataID: id
	          });
	        }, function() {
	          $log.info("the student cannot be removed since they are enrolled to some courses");
	        });
	      };
	      //uplata.eRacun
          $scope.openModalU= function (uplata) {
              var modalInstance = $uibModal.open({
                templateUrl: 'views/modals/uplata.html',
                controller: UplataModalCtrl,
                scope: $scope,
                resolve: {
                	uplata: function() {
                    return uplata;
                  }
                }
              });
		};
    	
        $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };
    }
		
	];
    
    var UplataModalCtrl =['$scope', '$uibModalInstance', 'uplata', 'Restangular', '$log', '_',
                          function($scope, $uibModalInstance, uplata, Restangular, $log, _) {
    	$scope.uplata=uplata;
    	
        $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };
          
          $scope.datum={};
          $scope.openDate = function() {
              $scope.datum.opened = true;
            };
    	
        $scope.ok = function() {
            if ($scope.uplata.uplataID) {
				Restangular.all("uplate").customPUT($scope.uplata).then(function (data) {
					$scope.uplata					
				});
            } else {
            	$scope.uplata.eRacun={"eRacunID":$scope.eRacun.eRacunID};
              Restangular.all('uplate').post($scope.uplata).then(function (data) {
                $scope.uplate.push(data);
              },
                // callback za gresku sa servera
                function() {
                  $log.info('');
                });
            }
            $uibModalInstance.close('ok');
          };


    	
    }
    ];

    $scope.openERacun= function (eRacun) {
        var modalInstance = $uibModal.open({
            templateUrl: 'views/modals/eracun.html',
            controller: ERacunModalCtrl,
            scope: $scope,
            resolve: {
            	eRacun: function() {
                return eRacun;
              }
            }
          });
	};
    
    $scope.openModal = function(student) {

      if (!student) {
        student = {
          brojIndexa: '',
          ime: '',
          prezime: '',
          jmbg:''
          
        };
      }

      var modalInstance = $uibModal.open({
        templateUrl: 'views/modals/student.html',
        controller: StudentsModalCtrl,
        scope: $scope,
        resolve: {
          student: function() {
            return student;
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
