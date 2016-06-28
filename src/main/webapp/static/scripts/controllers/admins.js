angular.module('studentsClientApp')
	.controller('AdminsCtrl', ['$scope', 'Restangular', '$uibModal', '$log', '_', function($scope, Restangular, $uibModal, $log, _) {
		$scope.currentPage=0;
		Restangular.all("administratori").getList().then(function (entries) {
			$scope.admins=entries;
			check(entries);
			$scope.page($scope.currentPage);
		});
		
		$scope.deleteAdmin = function (id) {
			Restangular.one("administratori", id).remove().then(function () {
				_.remove($scope.admins,{
					adminID:id
				});
				_.remove($scope.adminsPage,{
					adminID:id
				});
		        if($scope.adminsPage.length==0){
					$scope.page($scope.currentPage-1);
				}
				else{
		        	$scope.page($scope.currentPage);
				}
				
				check($scope.admins);
			});
		};
		
	    $scope.page= function page(currentPage){
	    	$scope.currentPage=currentPage;
	        Restangular.all("administratori").getList({page: currentPage,size: 5 }).then(function(entries) {
	            $scope.adminsPage = entries;
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
		
	    var AdminModalCtrl = ['$scope', '$uibModalInstance', 'admin', 'Restangular', '$log', '_',
          function($scope, $uibModalInstance, admin, Restangular, $log, _) {
	       $scope.admin= admin;

	       $scope.ok = function() {
	    	   if ($scope.admin.adminID) {
	    		   Restangular.all('administratori').customPUT($scope.admin).then(function (data) {
	    			   var index = _.indexOf($scope.admins, _.find($scope.admins, {id: $scope.admin.id}));
	    			   $scope.admin.splice(index, 1, data);
	    		   });
	    	   } else {
	    		   Restangular.all('administratori').post($scope.admin).then(function (data) {
	    			   $scope.admins.push(data);
	    	      		$scope.adminsPage.push(data);
	    	      		check($scope.admins);
	    	              if($scope.adminsPage.length>5){
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

	    $scope.openModal = function(admin) {

//	    	if (!admin) {
//	    		admin = {
//	    				brojIndexa: '' 
//	    		};
//	    	}

	    	var modalInstance = $uibModal.open({
	    		templateUrl: 'views/modals/admin.html',
	    		controller: AdminModalCtrl,
	    		scope: $scope,
	    		resolve: {
	    			admin: function() {
	    				return admin;
	    			}
	    		}
	    	});
	    }
}]);