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
    'lodash',
    "gettext",
    "ngCookies"
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
      .when('/students/:id', {
        templateUrl: 'views/student.html',
        controller: 'StudentCtrl',
        controllerAs: 'student'
      })
      .when('/courses', {
    	  resolve:{
    		  "check":function($location, $cookies){
    			  var s= $cookies.getObject("student");
    			  if(s!=null){
    				  $location.path("/")
    			  }
    		  }
    	  },
        templateUrl: 'views/courses.html',
        controller: 'CoursesCtrl',
        controllerAs: 'courses'
    	  
      })
      .when('/courses/:id', {
        templateUrl: 'views/course.html',
        controller: 'CourseCtrl',
        controllerAs: 'course'
      })
      .when('/professors', {
    	  resolve:{
    		  "check":function($location, $cookies){
    			  var s= $cookies.getObject("student");
    			  if(s!=null){
    				  $location.path("/")
    			  }
    		  }
    	  },
    	templateUrl: 'views/professors.html',
        controller: 'ProfessorsCtrl',
        controllerAs: 'professors' 
      })
//      .when('/login',{
//    	  templateUrl: 'views/login.html',
//    	  controller: 'LoginCtrl',
//    	  controllerAs: 'main'
//      })
      .when('/professors/:id', {
    	  resolve:{
    		  "check":function($location, $cookies){
    			  var s= $cookies.getObject("student");
    			  if(s!=null){
    				  $location.path("/")
    			  }
    		  }
    	  },
    	templateUrl: 'views/professor.html',
        controller: 'ProfessorCtrl',
        controllerAs: 'professor' 
      })
      .when('/admins',{
    	  resolve:{
    		  "check":function($location, $cookies){
    			  var s= $cookies.getObject("student");
    			  if(s!=null){
    				  $location.path("/")
    			  }
    		  }
    	  },
      	templateUrl: 'views/admins.html',
        controller: 'AdminsCtrl',
        controllerAs: 'admins'   
      })
      .when('/admins/:id',{
    	  resolve:{
    		  "check":function($location, $cookies){
    			  var s= $cookies.getObject("student");
    			  if(s!=null){
    				  $location.path("/")
    			  }
    		  }
    	  },
      	templateUrl: 'views/admin.html',
        controller: 'AdminCtrl',
        controllerAs: 'admin'   
      })
      .otherwise({
        redirectTo: '/'
      });
  }])
  // run se izvrsava pre svega ostalog
  .run(['Restangular', '$rootScope', 'gettextCatalog', '$location', '$log', function(Restangular, $rootScope, gettextCatalog, $location, $log) {
	  gettextCatalog.currentLanguage='sr';
	  gettextCatalog.debug=true;
	//postavljamo $rootScope radi zaobilaska unsafe:url...
	//primer ng-href="{{protocol}}{{baseURLHref}}{{baseURLPort}}routingPathName"
	$rootScope.protocol = 'http://';
    $rootScope.baseURLHref = $location.host();
    $rootScope.baseURLPort = ':' + $location.port() + '/';
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
