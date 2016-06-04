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
    
	Restangular.all("predmeti").getList().then(function(entries) {
		$scope.courses = entries;
	});
	
	$scope.deleteCourse = function(id) {
		Restangular.one("predmeti", id).remove().then(function() {
			_.remove($scope.courses, {
				predmetID: id
			});
		}, function() {
			$log.info("something went wrong");
		});
	};
	
	// START CourseModalCtrl
    var CourseModalCtrl = ['$scope', '$uibModalInstance', 'course', 'Restangular', '$log', '_',
                           function($scope, $uibModalInstance, course, Restangular, $log, _) {
    	
    	$scope.course = course;
    	if ($scope.course.predmetID) {
    		Restangular.one("predmeti", $scope.course.predmetID).getList("studenti").then(function(entries) {
    			$scope.enrollments = entries;
    		});
    		Restangular.one("predmeti", $scope.course.predmetID).getList("profesori").then(function (entries) {
    			$scope.teachings=entries;
    		});
    	}

    	$scope.ok = function() {
    		if ($scope.course.predmetID) {
    			Restangular.all('predmeti').customPUT($scope.course).then(function (data) {
    				var index = _.indexOf($scope.courses, _.find($scope.courses, {id: $scope.course.id}));
    				$scope.courses.splice(index, 1, data);
    			});
            } else {
            	Restangular.all('predmeti').post($scope.course).then(function (data) {
            		$scope.courses.push(data);
            	},
            	// callback za gresku sa servera
                function() {
            		$log.info('something went wrong!');
                });
            }
            $uibModalInstance.close('ok');
    	};

    	$scope.cancel = function() {
    		$uibModalInstance.dismiss('cancel');
    	};

        $scope.deleteEnrollment = function (id) {
        	Restangular.one("pohadja", id).remove().then(function() {
        		_.remove($scope.enrollments, {
        			pohadjaID: id
        		});
        	}, function() {
        		$log.info("something went wrong");
        	});
        };
        ////
        $scope.deleteTeaching = function (id) {
        	Restangular.one("predaje", id).remove().then(function() {
        		_.remove($scope.teachings, {
        			predajeID: id
        		});
        	}, function() {
        		$log.info("something went wrong");
        	});
        };
        
        // deleteTaskType(task.tipObavezeID)
        // prilikom brisanja tipa obaveze obrisati i sve obaveze koje su dodeljene studentima koji su na predmetu
        $scope.deleteTaskType = function (id) {
        	Restangular.one("predaje", id).remove().then(function() {
        		_.remove($scope.teachings, {
        			predajeID: id
        		});
        	}, function() {
        		$log.info("something went wrong");
        	});
        };
            
        var StudentEnrollmentModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
            var enrolledStudentIds = _.map($scope.enrollments,function (value) {
            	return value.student.studentID;
            });
            Restangular.all('studenti').getList().then(function (data) {
            	$scope.students = data;
            	_.remove($scope.students, function (student) {
            		return _.contains(enrolledStudentIds, student.studentID);
            	});
            });

            $scope.startDate={};
            $scope.openStartDate = function() {
            	$scope.startDate.opened = true;
            };

            $scope.endDate={};
            $scope.openEndDate = function() {
                $scope.endDate.opened = true;
            };

            $scope.ok = function() {
            	$scope.enrollment.student={"studentID":$scope.student.studentID};
            	Restangular.all('pohadja').post($scope.enrollment).then(function (data) {
            		$scope.enrollments.push(data);
            	});
            	$uibModalInstance.close('ok');
            };

            $scope.cancel = function() {
              $uibModalInstance.dismiss('cancel');
            };
        }];
          
        var ProfessorTeachingModalCtrl=['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
        	var teachingProfessorIds=_.map($scope.teachings, function(value){
        		return value.profesor.profesorID; 
        	});
        	Restangular.all('profesori').getList().then(function (data) {
        		$scope.professors=data;
        		_.remove($scope.professors, function (professor) {
        			return _.contains(teachingProfessorIds, professor.profesorID);
        		});
        	});
        	 
        	$scope.ok = function() {
        		$scope.teaching.profesor={"profesorID":$scope.professor.profesorID}; //!!!!!!!!!!!!!
        		Restangular.all('predaje').post($scope.teaching).then(function (data) {
        			$scope.teachings.push(data);
        		});
        		$uibModalInstance.close('ok');
        	};
        	
        	$scope.cancel = function() {
        		$uibModalInstance.dismiss('cancel');
        	};
        }];
        
        
        /*
         * Obaveze na kursu
         * Prikazati samo tip obaveze
         * moze se menjati tip obaveze, uklanjati i dodavati
         * Kada se kreira tip obaveze, automatski se dodeljuje studentima koji pohadjaju predmet
         */
        var TaskTypeModalCtrl=['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
        	var taskTypeIds=_.map($scope.tasks, function(value){
        		return value.tipObaveze.tipObavezeID; 
        	});
        	Restangular.all('tipobaveze').getList().then(function (data) {
        		$scope.tasks=data;
        		_.remove($scope.tasks, function (task) {
        			return _.contains(taskTypeIds, task.tipObavezeID);
        		});
        	});
        	 /*
        	$scope.ok = function() {
        		$scope.teaching.profesor={"profesorID":$scope.professor.profesorID}; //!!!!!!!!!!!!!
        		Restangular.all('predaje').post($scope.teaching).then(function (data) {
        			$scope.teachings.push(data);
        		});
        		$uibModalInstance.close('ok');
        	};

        	$scope.cancel = function() {
        		$uibModalInstance.dismiss('cancel');
        	};*/
        }];
          
        /////////
        $scope.openModalO= function () {
        	$scope.teaching = {"predmet":{"predmetID":$scope.course.predmetID}};
        	var modalInstance = $uibModal.open({
        		templateUrl: 'views/modals/professorTeaching.html', // napravi html za tip obaveze
        		controller: TaskTypeModalCtrl,
        		scope: $scope,
        		resolve: {
        			course: function() {
        				return course;
        			}
        		}
        	});
        };
        
        $scope.openModalT= function () {
        	$scope.teaching = {"predmet":{"predmetID":$scope.course.predmetID}};
        	var modalInstance = $uibModal.open({
        		templateUrl: 'views/modals/professorTeaching.html',
        		controller: ProfessorTeachingModalCtrl,
        		scope: $scope,
        		resolve: {
        			course: function() {
        				return course;
        			}
        		}
        	});
        };
          
        $scope.openModalS = function() {
        	$scope.enrollment = {"predmet":{"predmetID":$scope.course.predmetID}};
            var modalInstance = $uibModal.open({
            	templateUrl: 'views/modals/studentEnrollment.html',
            	controller: StudentEnrollmentModalCtrl,
            	scope: $scope,
            	resolve: {
            		course: function() {
            			return course;
            		}
            	}
            });
            modalInstance.result.then(function(value) {
            	$log.info('Modal finished its job at: ' + new Date() + ' with value: ' + value);
            }, function(value) {
            	$log.info('Modal dismissed at: ' + new Date() + ' with value: ' + value);
            });
        };

    }];
    // END CourseModalCtrl


    $scope.openModal = function(course) {
      if (!course) {
        course = {
          name: ''
        };
      }
      var modalInstance = $uibModal.open({
        templateUrl: 'views/modals/course.html',
        controller: CourseModalCtrl,
        scope: $scope,
        resolve: {
          course: function() {
            return course;
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
