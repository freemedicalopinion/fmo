'use strict';

// angular.js main app initialization
var app = angular.module('freemedicalopinion', []).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/', {
				templateUrl : 'index',
				activetab : 'home',
				controller : HomeCtrl
			}).when('/login', {
				templateUrl : 'login',
				controller : LoginCtrl,
				activetab : 'login'
			}).when('/articles', {
				templateUrl : function(params) {
					return 'articles';
				},
				controller : ArticleHomeCtrl,
				activetab : 'article'
			}).when('/articles/:categoryName/:categoryId', {
				templateUrl : function(params) {

					return 'articles/category';
				},
				controller : CategoryCtrl,
				activetab : 'article'

			}).when('/articles/:categoryName/:articleTitle/:articleId', {
				templateUrl : function(params) {
					return 'articles/category/article';
				},
				controller : articleCtrl,
				activetab : 'article'
			}).when('/searchcontent/:searchKeyword', {
				templateUrl : function(params) {
					return 'searchcontent';
				},
				controller : contentsearchController,
				activetab : 'article'
			}).when('/doctors', {
				templateUrl : 'doctors',
				controller : DoctorHomeCtrl,
				activetab : 'doctors'
			}).when('/hospitals', {
				templateUrl : 'hospitals',
				controller : HospitalHomeCtrl,
				activetab : 'hospitals'
			}).when('/hospital/:hospitalId', {
				templateUrl : 'hospital',
				controller : HospitalCtrl,
				activetab : 'hospitals'
			}).otherwise({
				redirectTo : '/'
			});
		} ])
		.run(
				[
						'$rootScope',
						'$http',
						'$browser',
						'$timeout',
						"$route",
						"$rootScope",
						function($scope, $http, $browser, $timeout, $route,
								$rootScope) {

							$rootScope.$on("$locationChangeStart", function(
									event, next, current) {
								$http.post('checkUserLogin', null).success(
										function(data) {
											$scope.loginData = data;
										});
							});

							$scope.$on("$routeChangeSuccess", function(scope,
									next, current) {
								$scope.part = $route.current.activetab;
								$http.post('checkUserLogin', null).success(
										function(data) {
											$scope.loginData = data;
										});
							});

						} ]);

app.config([ '$locationProvider', function($location) {
	$location.hashPrefix('!');
} ]);


