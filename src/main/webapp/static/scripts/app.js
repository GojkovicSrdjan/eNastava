'use strict';

/**
 * @ngdoc overview
 * @name studentsClientApp
 * @description
 * # studentsClientApp
 *
 * Main module of the application.
 */
var myApp = angular
  .module('studentsClientApp', [
    'ngResource',
    'ngRoute',
    'restangular',
    'ui.bootstrap',
    'lodash'
  ])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/students', {
        templateUrl: 'views/students.html',
        controller: 'StudentsCtrl',
        controllerAs: 'students'
      })
      .when('/courses', {
        templateUrl: 'views/courses.html',
        controller: 'CoursesCtrl',
        controllerAs: 'courses'
      })
      .when('/professors', {
    	templateUrl: 'views/professors.html',
        controller: 'ProfessorsCtrl',
        controllerAs: 'professors' 
      })
      .when('/admins',{
      	templateUrl: 'views/admins.html',
        controller: 'AdminsCtrl',
        controllerAs: 'admins'   
      })
      .otherwise({
        redirectTo: '/'
      });
  }])
  // run se izvrsava pre svega ostalog
  .run(['Restangular', '$log', function(Restangular, $log) {
    // postavimo base url za Restangular da ne bismo morali da ga
    // navodimo svaki put kada se obracamo back endu
    // poziv vrsimo na http://localhost:8080/api/
    Restangular.setBaseUrl("api");
    Restangular.setErrorInterceptor(function(response) {
      if (response.status === 500) {
        $log.info("internal server error");
        return true; // greska je obradjena
      }
      return true; // greska nije obradjena
    });
  }]);
