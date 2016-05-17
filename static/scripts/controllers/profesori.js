angular.module('professorsClientApp')
  .controller('ProfessorsCtrl', ['$scope', 'Restangular', '$uibModal', '$log', '_', function($scope, Restangular, $uibModal, $log, _) {
    //Restangular koristimo za rest pozive
    Restangular.all("professors").getList().then(function(entries) {
      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
      $scope.professors = entries;
    });
    
  }]);