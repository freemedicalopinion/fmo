'use strict';
function SecureHomeCtrl($scope, $http, $timeout, $location) {

	$scope.contextPath = $location.absUrl().substr(0,
			$location.absUrl().lastIndexOf("secure"));
}

function ViewCategoryCtrl($scope, $http, $timeout) {
	$http.post('getallcategories', null).success(function(data) {
		$scope.categories = data;
		$scope.activeCategory = 0;
	});
}

function ManageContentCtrl($scope, $http, $timeout) {
	$http.post('getallcategories', null).success(function(data) {
		$scope.categories = data;
		var item = {};
		item.categoryId = null;
		item.name = 'No Category';
		$scope.categories.push(item);

		if ($scope.categories.length > 0) {
			$scope.activeCategory = $scope.categories[0].categoryId;
		}

	});

	$http.post('getallcontent', null).success(function(data) {
		$scope.listOfContent = data.allContent;

	});
}

function ManageContentApprovalCtrl($scope, $http, $timeout, $window) {

	$http.post('getallcategories', null).success(function(data) {
		$scope.categories = data;

	});

	$http.post('getallcontentforapproval', null).success(function(data) {
		$scope.listOfContent = data.allContent;
		$scope.listOfStatuses = data.allStatuses;
		if ($scope.listOfStatuses.length > 0) {
			$scope.activeStatus = $scope.listOfStatuses[0];
		}

	});
}

function ManageSpecilityCtrl($scope, $http, $timeout, $window) {

	$http.post('getallspecilities', null).success(function(data) {
		$scope.listOfSpecility = data;

	});
}

function ManageProcedureCtrl($scope, $http, $timeout, $window) {

	$http.post('getallprocedures', null).success(function(data) {
		$scope.listOfProcedure = data;

	});
}

function ManageCountryCtrl($scope, $http, $timeout, $window) {

	$http
			.post('getallcountries', null)
			.success(
					function(data) {
						$scope.listOfCountries = data;
						if ($scope.listOfCountries != null
								&& $scope.listOfCountries.length > 0) {
							$scope.activeCountry = $scope.listOfCountries[0].countryId;
							$scope.activeCountryObj = $scope.listOfCountries[0];
						}
						if ($scope.activeCountry != null
								&& $scope.activeCountryObj.states != null
								&& $scope.activeCountryObj.states.length > 0) {
							$scope.activeState = $scope.activeCountryObj.states[0].stateId;
							$scope.activeStateObj = $scope.activeCountryObj.states[0];
							if ($scope.activeCountryObj.states[0].cities != null) {
								$scope.activeCity = $scope.activeCountryObj.states[0].cities[0].cityId;
								$scope.activeCityObj = $scope.activeCountryObj.states[0].cities[0];
							}

						}

					});

}

function ManageHospitalCtrl($scope, $http, $timeout, $window) {

	$http.post('gethospitalInfoOfLoggedIn', null).success(function(data) {
		$scope.hospital = data;

	});

	$http.post('getallprocedures', null).success(function(data) {
		$scope.listOfProcedure = data;

	});

	$http.post('getallcountries', null).success(function(data) {
		$scope.listofcountry = data;

	});
}

function ManageHospitalApprovalCtrl($scope, $http, $timeout, $window) {

	$http.post('getallHospitalForApproval', null).success(function(data) {
		$scope.hospitals = data;

	});

	
	$http.post('getAllStatuses', null).success(function(data) {
		$scope.listOfStatuses = data;
		if ($scope.listOfStatuses.length > 0) {
			$scope.activeStatus = $scope.listOfStatuses[0];
		}

	});

	$http.post('getallprocedures', null).success(function(data) {
		$scope.listOfProcedure = data;

	});

	$http.post('getallcountries', null).success(function(data) {
		$scope.listofcountry = data;

	});
}
