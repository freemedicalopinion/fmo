secureApp
		.controller(
				'manageAddressCountryController',
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

							$scope.launch = function(which, data) {

								if (data != null) {

									countryService.addCountryData(data);
								} else {
									countryService.reSetCountry();
								}

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create('manageCountryPopup',
											'managecountrypopupcontroller', {},
											{
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
				'managecountrypopupcontroller',
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
								$scope.country.countryName = categoryBck.countryName;
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
										.post('manageCountry', $scope.country)
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
