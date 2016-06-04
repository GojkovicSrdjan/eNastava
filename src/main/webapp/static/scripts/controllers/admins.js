angular.module('studentsClientApp')
	.controller('AdminsCtrl', ['$scope', 'Restangular', '$uibModal', '$log', '_', function($scope, Restangular, $uibModal, $log, _) {
		Restangular.all("administratori").getList().then(function (entries) {
			$scope.admins=entries;
		});
		
		$scope.deleteAdmin = function (id) {
			Restangular.one("administratori", id).remove().then(function () {
				_.remove($scope.admins,{
					adminID:id
				});
			});
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