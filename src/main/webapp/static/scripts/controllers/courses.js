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
    		Restangular.one("predmeti", $scope.course.predmetID).getList("tipobaveze").then(function (entries) {
    			$scope.tasks=entries;
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
            		$scope.coursesPage.push(data);
            		//posle dodavanja izracunava se broj stranica
            		check($scope.courses);
                    if($scope.coursesPage.length>5){
                    	$scope.page($scope.currentPage+1);
//                    	var last=$scope.pages[$scope.pages.length-1]
//                    	$scope.pages.push(last+1);
//                    	
                    };
                    
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
        $scope.openModalO= function () {
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
