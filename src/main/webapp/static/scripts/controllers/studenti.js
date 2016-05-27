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
          id: id
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
      }
    ];

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
