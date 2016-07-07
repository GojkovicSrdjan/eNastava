'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:CourseCtrl
 * @description
 * # CourseCtrl
 * Controller of the studentsClientApp
 */
myApp.controller('CourseCtrl', ['$scope', 'Restangular', '$routeParams', '$uibModal', '$log', '_', function($scope, Restangular, $routeParams, $uibModal, $log, _) {

	var paramID = $routeParams.id;
	$log.info(paramID);
	if(paramID!="new"){
		
		var predmetID = parseInt($routeParams.id);
		$log.info('routeParams:id '+ predmetID);
		
	    if (predmetID) {
	    	Restangular.one("predmeti").customGET(predmetID).then(function (data) {
	    		$scope.course = data;
			});
	    	
	    	Restangular.one("predmeti", predmetID).getList("studenti").then(function(entries) {
				$scope.enrollments = entries;
			});
			Restangular.one("predmeti", predmetID).getList("profesori").then(function (entries) {
				$scope.teachings=entries;
			});
			Restangular.one("predmeti", predmetID).getList("tipobaveze").then(function (entries) {
				$scope.tasks=entries;
			});
	    }
	}
	
    $scope.ok = function() {
      if ($scope.course.predmetID) {
        Restangular.all('predmeti').customPUT($scope.course);
      } else {
        if(Restangular.all('predmeti').post($scope.course)){}
        else{
        	// callback za gresku sa servera
            $log.info('something went');
        };
      }
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
    
    /**
     * Prilikom brisanja tipa obaveze, brisu se i sve obaveze po tipom(u kontroleru)
     */
    $scope.deleteTaskType = function (id) {
    	Restangular.one("tipobaveze", id).remove().then(function() {
    		_.remove($scope.tasks, {
    			tipObavezeID: id
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
    
    
    /**
     * Prilikom kreiranja tipaObaveze kreiraju se i obaveze za svakog studenta na kursu
     */
    var TaskTypeModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
    	var taskTypeIds=_.map($scope.tasks, function(value){
    		return value.tipObavezeID; 
    	});
    	
    	$scope.date={};
        $scope.openDate = function() {
        	$scope.date.opened = true;
        };
    	
    	$scope.ok = function() {
    		
    		$scope.task.predmet = $scope.course;
    		Restangular.all('tipobaveze').post($scope.task).then(function (data) {
        		$scope.tasks.push(data);
        		var tipObaveze = data;
        		
        		//kreiranje obaveza
        		var enrolledStudent = _.map($scope.enrollments,function (value) {
                	return value.student;
                });
        		Restangular.all('studenti').getList().then(function (data) {
        			$scope.students = data;
                 	_.remove($scope.students, function (student) {
                 		return _.contains(enrolledStudent, student);
                 	});
        		});
        		
                for (var i in enrolledStudent){
                	var obaveza = {"student": enrolledStudent[i],
                					"tipObaveze": tipObaveze};
                	Restangular.all('obaveze').post(obaveza);
                }
    		});
    		
    		$uibModalInstance.close('ok');
    	};

    	$scope.cancel = function() {
    		$uibModalInstance.dismiss('cancel');
    	};
    }];
    
    /////////
    $scope.openModalO= function (course) {
    	$scope.graded = {"predmet":{"predmetID":$scope.course.predmetID}};
    	var modalInstance = $uibModal.open({
    		templateUrl: 'views/modals/taskType.html',
    		controller: TaskTypeModalCtrl,
    		scope: $scope,
    		resolve: {
    			course: function() {
    				return course;
    			}
    		}
    	});
    };
    
    $scope.openModalT= function (course) {
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
      
    $scope.openModalS = function(course) {
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


}]);
