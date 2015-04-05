secureApp
		.controller(
				'manageSpecilityController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'specilityService',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								specilityService) {

							$scope.launch = function(which, specility) {

								if (specility != null) {

									specilityService.addSpecility(specility);
								} else {
									specilityService.reSetSpecility();
									
								}

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create(
											'managespecilitypopup',
											'managespecilitypopupController', {}, {
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
				'managespecilitypopupController',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'specilityService',
						'$route',
						function($scope, $http, $modalInstance,
								specilityService, $route) {

							$scope.specility = specilityService.getSpecility();
							var specilityBck = JSON.parse(JSON
									.stringify($scope.specility));
							$scope.errorMessage = null;
							$scope.isUnique = true;
							// localStorage.setItem("categoryBck", k);

							$scope.cancel = function() {

								$scope.specility.specilityName = specilityBck.specilityName;
								$scope.specility.description = specilityBck.description;

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
										.post('managespecility',
												$scope.specility)
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





secureApp
.controller(
		'manageProcedureController',
		[
				'$scope',
				'$rootScope',
				'$timeout',
				'$http',
				'$dialogs',
				'specilityService',
				function($scope, $rootScope, $timeout, $http, $dialogs,
						specilityService) {

					$scope.launch = function(which, specility) {

						if (specility != null) {

							specilityService.addSpecility(specility);
						} else {
							specilityService.reSetSpecility();
							
						}

						var dlg = null;

						switch (which) {

						// Create Your Own Dialog
						case 'create':
							dlg = $dialogs.create(
									'manageprocedurepopup',
									'manageprocedurepopupController', {}, {
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
		'manageprocedurepopupController',
		[
				'$scope',
				'$http',
				'$modalInstance',
				'specilityService',
				'$route',
				function($scope, $http, $modalInstance,
						specilityService, $route) {

					$scope.procedure = specilityService.getSpecility();
					var specilityBck = JSON.parse(JSON
							.stringify($scope.procedure));
					$scope.errorMessage = null;
					$scope.isUnique = true;
					// localStorage.setItem("categoryBck", k);

					$scope.cancel = function() {

						$scope.procedure.procedureName = specilityBck.procedureName;
						$scope.procedure.description = specilityBck.description;

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
								.post('manageprocedure',
										$scope.procedure)
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
//end editcategorypopup
