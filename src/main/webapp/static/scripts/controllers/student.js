'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:StudentCtrl
 * @description
 * # StudentCtrl
 * Controller of the studentsClientApp
 */

myApp.controller('StudentCtrl', ['$scope', 'Restangular', '$routeParams', '$uibModal', '$log', '_', function($scope, Restangular, $routeParams, $uibModal, $log, _) {

	var paramID = $routeParams.id;
	$log.info(paramID);
	if(paramID!="new"){
		
		var studentID = parseInt($routeParams.id);
		$log.info('routeParams:id '+ studentID);
		
	    if (studentID) {
	    	Restangular.one("studenti").customGET(studentID).then(function (data) {
	    		$scope.student = data;
			});
	    }
	}
	
    $scope.ok = function() {
      if ($scope.student.studentID) {
        Restangular.all('studenti').customPUT($scope.student);
      } else {
        if(Restangular.all('studenti').post($scope.student)){}
        else{
        	// callback za gresku sa servera
            $log.info('the student with such a cardNumber already exists');
        };
      }
    };

    var StudentViewCourseModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
    	
    	$scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
        };
    	
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
    	if ($scope.student.studentID){
        	Restangular.one("studenti", $scope.student.studentID).getList("obaveze").then(function (entries) {
    			$scope.subtasks=entries;
    		});
    	}
    	
    	var StudentViewObavezaModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
    		
    		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	        };

	        //U $scope.subtasks nadji subtask(obavezu) gde je tipObavezeID == $scope.task.tipObavezeID
    		//postavu u scope > $scope.subtask
	        //Da li moze jednostavnije?
    		for (var int = 0; int < $scope.subtasks.length; int++) {
    			if($scope.subtasks[int].tipObaveze.tipObavezeID == $scope.task.tipObavezeID){
    				$scope.subtask = $scope.subtasks[int];
    			}
			}
    	}];
    	//END StudentViewObavezaModalCtrl
            
    	
    	$scope.openModalO = function(tipObaveze) {
        	$scope.task = tipObaveze;
        	
            var modalInstance = $uibModal.open({
            	templateUrl: 'views/modals/studentViewObaveza.html',
            	controller: StudentViewObavezaModalCtrl,
            	scope: $scope,
            	resolve: {
            		tipObaveze: function() {
            			return tipObaveze;
            		}
            	}
            });
        };
        
    }];
    //END StudentViewCourseModalCtrl
    
    var StudentDocumentsModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
		
		$scope.cancel = function() {
          $uibModalInstance.dismiss('cancel');
        };
        
        $scope.ok = function() {
	          $uibModalInstance.dismiss('cancel');
	    };
	    
	    $scope.deleteDocument = function(id) {
	    	Restangular.one("dokumenti", id).remove().then(function() {
	          // uklanjamo studenta sa zadatim id-om iz kolekcije
	          _.remove($scope.documents, {
	            dokumentID: id
	          });
	          /*_.remove($scope.documentsPage,{
	          	dokumentID:id
	  		  });
	          if($scope.documentssPage.length==0){
	  			$scope.page($scope.currentPage-1);
	  		  }
	  		  else{
	          	$scope.page($scope.currentPage);
	  		  }*/
	  		  check($scope.documents);
	        });
	    };
        
        if ($scope.student.studentID){
        	Restangular.one("studenti", $scope.student.studentID).getList("dokumenti").then(function (entries) {
    			$scope.documents=entries;
    		});
    	}
        
        var StudentUploadDocumentModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance){
    		
    		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	        };
	        
	        $scope.ok = function() {
		        var funPUT = function(urlPut,formData) {
	        		Restangular.one(urlPut)
		    		.withHttpConfig({transformRequest: angular.identity})
		    		.customPUT(fd, '', undefined, {'Content-Type': undefined})
		    		.then(function (data) {
	    				var index = _.indexOf($scope.documents, _.find($scope.documents, {id: $scope.document.dokumentID}));
		    			$scope.documents.splice(index, 1, data);
		    		});
	        	}
	        	var funPOST = function(formData) {
	        		Restangular.one('dokumenti')
		    		.withHttpConfig({transformRequest: angular.identity})
		    		.customPOST(fd, '', undefined, {'Content-Type': undefined})
		    		.then(function (data) {
		    			$scope.documents.push(data);
		    		});
	        	}
	        	
	        	var fd = new FormData();
	        	fd.append('nazivDokumenta', $scope.document.naziv);
	    		fd.append('tipDokumenta', $scope.document.file.type);
	    		
	    		//Selektovan fajl
	        	if($scope.document.file.size != null){
	        		var t = $scope.document.file.type;
	        		if(t == 'application/kswps' || t == 'image/jpg' || t == 'image/jpeg'){
	        			if($scope.document.file.size < 5242880){
				        	fd.append('file', $scope.document.file);
				        	//Update
				    		if($scope.document.dokumentID != null){
				    			fd.append('dokumentID', $scope.document.dokumentID);
				    			funPUT('dokumenti/upload',fd);
				    			$uibModalInstance.close('ok');
				        	//Create
				    		}else{
				    			fd.append('studentID', $scope.student.studentID);
				    			funPOST(fd);
				    			$uibModalInstance.close('ok');
				    		}
				    	}else{
				    		alert('Error! File is to big. Max aproved size is 5MB');
		        			$log.info('Error! File is to big. Max aproved size is 5MB');
			        	}
	        		}else{
	        			alert('Error! Invalid document type. Allowed formats are .pdf .jpg .jpeg');
	        			$log.info('Error! Invalid document type. Allowed formats are .pdf .jpg .jpeg');
	        		}
	        	//Nije selektovan fajl
	        	}else{
		    		//Update
		    		if($scope.document.dokumentID != null){
		    			fd.append('dokumentID', $scope.document.dokumentID);
		    			funPUT('dokumenti',fd);
		    			$uibModalInstance.close('ok');
		        	//Create
		    		}else{
		    			alert('Error! To create Document u must select File to upload.');
		    			$log.info('Error! To create Document u must select File to upload.');
		    		}
	        	}
	        }
	        
        
        }];
        //END StudentUploadDocumentModalCtrl
        
        /////
        $scope.openModalUploadDocument = function(document) {
        	
        	if (!document) {
                document = {
                  naziv: '',
                  tip: '',
                  putanjaDoDokumenta: ''                
                };
            }
        	$scope.document = document;
        	$scope.document.file = {};
        	$scope.document.file.type = document.tip;
        	
            var modalInstance = $uibModal.open({
            	templateUrl: 'views/modals/document.html',
            	controller: StudentUploadDocumentModalCtrl,
            	
            	scope: $scope,
            	resolve: {
            		document: function() {
            			return document;
            		}
            	}
            });
        };
        
	}];
    //END StudentDocumentsModalCtrl
    
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
	    
	    $scope.cancel = function() {
	        $uibModalInstance.dismiss('cancel');
	    };
	    
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
						$scope.uplata;					
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
	            $scope.eRacun.stanjeNaERacunu=$scope.eRacun.stanjeNaERacunu+parseInt($scope.uplata.iznos);
	            $uibModalInstance.close('ok');
	          };	
	    }];
	    //END UplataModalCtrl
	    
	    /////
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
	}];
	
	//////
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

    $scope.openModalP = function(predmet) {
    	$scope.course = predmet;
        var modalInstance = $uibModal.open({
        	templateUrl: 'views/modals/studentViewCourse.html',
        	controller: StudentViewCourseModalCtrl,
        	scope: $scope,
        	resolve: {
        		predmet: function() {
        			return predmet;
        		}
        	}
        });
    };
    
    $scope.openModalDokumenta = function(student) {
    	var modalInstance = $uibModal.open({
        	templateUrl: 'views/modals/documents.html',
        	controller: StudentDocumentsModalCtrl,
        	scope: $scope,
        	resolve: {
        		student: function() {
        			return student;
        		}
        	}
        });
    }

  }]);
