secureApp
		.controller(
				'manageAddressCityController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'countryService',
						'$route',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								countryService, $route) {

							$scope.launch = function(which, countryId, stateId,
									city) {

								if (city != null) {

									countryService.addCityData(countryId,
											stateId, city);
								} else {
									countryService.reSetCountry();
									
									countryService.addCityData(countryId,
											stateId, {});
								}

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create('manageCityPopup',
											'managecitypopupcontroller', {}, {
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
				'managecitypopupcontroller',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'countryService',
						'$route',
						function($scope, $http, $modalInstance, countryService,
								$route) {
							$scope.country = countryService.getCountry();
							$scope.errorMessage = null;
							$scope.isUnique = true;
							var categoryBck = JSON.parse(JSON
									.stringify($scope.country));

							$scope.cancel = function() {

								$scope.country.states[0].cities[0].cityName = categoryBck.states[0].cities[0].cityName;
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
										.post('manageCity', $scope.country)
										.success(
												function(data) {

													if (data.responseStatus == 'SUCCESS') {
														if (data.unique == false) {
															$scope.isUnique = false;
															$scope.errorMessage = data.message;
														} else {
															$modalInstance
																	.close();
															$route.reload();
														}

													} else {
														$scope.errorMessage = data.errorMessage;
													}

												});

							}; // end hitEnter

						} ]);
// end editcategorypopup
