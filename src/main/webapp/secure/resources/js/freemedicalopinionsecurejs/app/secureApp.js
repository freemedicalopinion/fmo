'use strict';

// angular.js main app initialization
var secureApp = angular.module('freemedicalopinionSecure',
		[ 'ui.bootstrap', 'dialogs', 'angularFileUpload' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/', {
				templateUrl : 'home',
				activetab : 'home',
				controller : SecureHomeCtrl
			}).when('/viewcategory', {
				templateUrl : 'viewcategory',
				controller : ViewCategoryCtrl,
				activetab : 'login'
			}).when('/managecontent', {
				templateUrl : 'managecontent',
				controller : ManageContentCtrl,
				activetab : 'login'
			}).when('/managecontentapproval', {
				templateUrl : 'managecontentapproval',
				controller : ManageContentApprovalCtrl,
				activetab : 'login'
			}).when('/managespecility', {
				templateUrl : 'viewspecility',
				controller : ManageSpecilityCtrl,
				activetab : 'login'
			}).when('/manageprocedure', {
				templateUrl : 'viewprocedure',
				controller : ManageProcedureCtrl,
				activetab : 'login'
			}).when('/manageaddresscountry', {
				templateUrl : 'viewcountry',
				controller : ManageCountryCtrl,
				activetab : 'login'
			}).when('/managehospitalprofile', {
				templateUrl : 'viewhospital',
				controller : ManageHospitalCtrl,
				activetab : 'login'
			}).when('/managehospitalapproval', {
				templateUrl : 'managehospitalapproval',
				controller : ManageHospitalApprovalCtrl,
				activetab : 'login'
			}).otherwise({
				redirectTo : '/'
			});
		} ]).run(
		[
				'$rootScope',
				'$http',
				'$browser',
				'$timeout',
				"$route",
				function($scope, $http, $browser, $timeout, $route) {

					$scope.$on("$routeChangeSuccess", function(scope, next,
							current) {
						$scope.part = $route.current.activetab;
					});

					$http.post('checkUserLogin', null).success(function(data) {
						$scope.loginData = data;
					});
					$http.post('getMenuForUser', null).success(function(data) {
						$scope.menus = data;
					});

				} ]);

secureApp.config([ '$locationProvider', function($location) {
	$location.hashPrefix('!');
} ]);
