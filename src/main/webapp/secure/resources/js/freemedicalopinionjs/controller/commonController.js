'use strict';

// optional controllers
function HomeCtrl($rootScope, $location, $scope, $http) {

	/*
	 * $rootScope.$on("$locationChangeStart", function(event, next, current) {
	 * alert(next); });
	 * 
	 * $rootScope.$on("$routeChangeStart", function (event, next, current) {
	 * 
	 * });
	 */

	$rootScope.header = "home";
	$scope.tab = 1;
}

function CategoryCtrl($scope, $http, $routeParams) {

	$http.post('articles/category', $routeParams.categoryId).success(
			function(data) {

				$scope.contents = data.contents;
			});

	$http.post('getallcategories', null).success(function(data) {
		$scope.categories = data;
	});

}

function articleCtrl($scope, $http, $routeParams) {

	$scope.contentId = null;
	$http.post('articles/article', $routeParams.articleId).success(
			function(data) {
				$scope.contents = data.contents;
				if ($scope.contents.length > 0) {
					$scope.contentId = $scope.contents[0].contentId;
				}
			});

	$http.post('getallcategories', null).success(function(data) {
		$scope.categories = data;
	});

	$scope.related = true;
	$scope.relatedCount = 1;

	$http.post('articles/category', $routeParams.categoryName).success(
			function(data) {
				$scope.relatedContents = data.contents;
				if ($scope.relatedContents.length == 1) {
					$scope.related = false;
					$scope.relatedTotal = $scope.relatedContents.length;
				}
			});

	$http.post('getAllCommentForContent', $routeParams.articleId).success(
			function(data) {
				$scope.comments = data;

			});

}

function LoginCtrl($rootScope, $scope, $http, $location, $window, $timeout) {
	if ($scope.loginData.login == true) {
		$window.location.href = 'secure';
	}

}

function ArticleHomeCtrl($routeParams, $scope, $http, $timeout, $rootScope) {
	
}

function DoctorHomeCtrl($scope, $http, $timeout) {
}

function HospitalHomeCtrl($scope, $http, $timeout, $location, $anchorScroll) {
	$scope.start = 0;
	$scope.tab = 2;
	$scope.scrollTo = function(id) {
		$location.hash(id);
		$anchorScroll();
	}

	$http.post('hospitalForhospitalHomepage', null).success(function(data) {
		$scope.hospitals = data;
	});

}

function HospitalCtrl($scope, $http, $timeout, $location, $anchorScroll,
		$routeParams , $route) {
	$scope.rating = 0;
	$scope.review={};
	$scope.review.staff = 0;
	$scope.review.cleanliness = 0;
	$scope.review.service = 0;
	$scope.review.cost = 0
	$scope.review.hospitalId=$routeParams.hospitalId;

	$scope.scrollTo = function(id) {
		$location.hash(id);
		$anchorScroll();
	}

	$http.post('approvedHospital', $routeParams.hospitalId).success(
			function(data) {
				$scope.hospital = data;
			});

	$http.post('getallprocedures', null).success(function(data) {
		$scope.listOfProcedure = data;

	});

	$http.post('getallcountries', null).success(function(data) {
		$scope.listofcountry = data;

	});

	$http.post('getreviews', $routeParams.hospitalId).success(function(data) {
		$scope.listofreviews = data;

	});

	$scope.error = "";
	$scope.errorflag = false;
	$scope.addreview = function() {
		
		

		if ($scope.loginData.login == false) {
			$scope.errorflag = true;
			$scope.error = "Please login to add review.";
		}else if($scope.review.staff === 0){
			$scope.error = "Please select staff rating";
			$scope.errorflag = true;
		}else if($scope.review.service== 0){
			$scope.error = "Please select service rating";
			$scope.errorflag = true;
		}else if($scope.review.cleanliness==0){
			$scope.error = "Please select cleanliness rating";
			$scope.errorflag = true;
		}else if($scope.review.cost==0){
			$scope.error = "Please select value rating";
			$scope.errorflag = true;
		} else {
			$http.post('addreview', $scope.review).success(function(data) {
				 $route.reload();
			});
		}

	}

}

function contentsearchController($scope, $http, $timeout, $routeParams) {

	$http.post('getSearchedContent', $routeParams.searchKeyword).success(
			function(data) {
				$scope.contents = data.allContent;
			});
	$http.post('getallcategories', null).success(function(data) {
		$scope.categories = data;
	});

}