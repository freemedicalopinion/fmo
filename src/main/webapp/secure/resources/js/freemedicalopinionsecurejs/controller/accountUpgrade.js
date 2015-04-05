secureApp
		.controller(
				'accountUpgradeToDoctorController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'$route',
						'$window',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								$route, $window) {

							$scope.launch = function(which, data) {

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'alert':
									dlg = $dialogs
											.confirm('Upgrade to Doctor !!',
													'Are you sure , want to upgrade your role to doctor');
									dlg.result
											.then(
													function(btn) {

														$http
																.post(
																		'upgradetodoctor',
																		null)
																.success(
																		function(
																				data) {
																			if (data.responseStatus == 'SUCCESS') {
																				$window.location
																						.reload();
																			} else {
																				$scope.errormessage = data.errorMessage;
																			}

																		});

													}, function(btn) {

													});

									break;
								}
								; // end switch
							}; // end launch

						} ]);
// end editCategoryController

secureApp
		.controller(
				'accountUpgradeToHospitalController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						function($scope, $rootScope, $timeout, $http, $dialogs) {

							$scope.launch = function(which) {

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create(
											'getupgradetohospitalpopup',
											'upgradeHospitalpopup', {}, {
												key : false,
												back : 'static'
											});
									dlg.result
											.then(
													function(name) {
														$scope.name = name;
													},
													function() {
														$scope.name = 'You decided not to enter in your name, that makes me sad.';
													});

									break;
								}
								; // end switch
							}; // end launch

						} ])
		// end editCategoryController
		.controller(
				'upgradeHospitalpopup',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'$route',
						'$window',
						function($scope, $http, $modalInstance, $route, $window) {
							$scope.data = {};
							$scope.cancel = function() {

								$modalInstance.dismiss('canceled');
							}; // end cancel

							$scope.save = function() {
								 $scope.addData();

							}; // end save

							$scope.hitEnter = function(evt) {
								if (angular.equals(evt.keyCode, 13)
										&& !(angular.equals(
												$scope.category.name, null) || angular
												.equals($scope.category.name,
														'')))
									$scope.save();
							}; // end hitEnter

							$scope.addData = function() {
								$http
										.post('upgradetohospital',
												$scope.data.hospitalname)
										.success(
												function(data) {
													if (data.responseStatus == 'SUCCESS') {
														$window.location
																.reload();
													} else {
														$scope.errormessage = data.errorMessage;
													}

												});
							}; // end hitEnter

						} ]);
// end editcategorypopup

