secureApp
		.controller(
				'manageHospitalController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'specilityService',
						'$route',
						'countryService',
						'hospitalService',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								specilityService, $route, countryService,
								hospitalService) {

							$scope.launch = function(which, data) {

								specilityService
										.addSpecility($scope.listOfProcedure);
								countryService
										.addCountryData($scope.listofcountry);

								hospitalService.addHospital($scope.hospital);

								var dlg = null;

								switch (which) {

								// Error Dialog
								case 'error':
									dlg = $dialogs.error($scope.errormessage);
									break;

								// Confirm Dialog
								case 'publish':
									dlg = $dialogs
											.confirm(
													'Publish this Hospital Information',
													'Are you sure , want to publish this hospital. Accepting this will change the staus of hospital  to pending approval . After approval of Admin this hospital will be online . ');
									dlg.result
											.then(
													function(btn) {

														$http
																.post(
																		'publishHospital',
																		null)
																.success(
																		function(
																				data) {
																			if (data.responseStatus == 'FAIL') {
																				$scope.errormessage = data.errorMessage;
																				$scope
																						.launch('error');
																			} else {
																				$route
																						.reload();
																			}

																		});

													}, function(btn) {

													});
									break;

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create(
											'getmanagehospitalpopup',
											'managehospitalpopupctr', {}, {
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
				'managehospitalpopupctr',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'$route',
						'$upload',
						'specilityService',
						'countryService',
						'hospitalService',
						function($scope, $http, $modalInstance, $route,
								$upload, specilityService, countryService,
								hospitalService) {

							$scope.listOfProcedure = specilityService
									.getSpecility();

							$scope.listofcountry = countryService.getCountry();
							$scope.hospital = hospitalService.getHospital();

							$scope.selectedCountry = function() {
								$scope.selectedCountryData = $scope.hospital.address.country;
								for (var i = 0; i < $scope.listofcountry.length; i++) {
									if ($scope.listofcountry[i].countryId == $scope.selectedCountryData) {
										$scope.states = $scope.listofcountry[i].states;
									}
								}
							}

							$scope.selectedState = function() {
								$scope.selectedStateData = $scope.hospital.address.state;
								for (var i = 0; i < $scope.listofcountry.length; i++) {
									if ($scope.listofcountry[i].countryId == $scope.selectedCountryData) {
										for (var j = 0; j < $scope.listofcountry[i].states.length; j++) {
											if ($scope.listofcountry[i].states[j].stateId == $scope.selectedStateData) {
												$scope.cities = $scope.listofcountry[i].states[j].cities;
											}
										}

									}
								}
							}

							$scope.cancel = function() {

								$modalInstance.dismiss('canceled');
								$route.reload();
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
										.post('manageHospital', $scope.hospital)
										.success(
												function(data) {

													if (data.responseStatus == 'SUCCESS') {
														$modalInstance.close();

													} else {
														$scope.errorMessage = data.errorMessage;
													}

												});

							}; // end hitEnter

							$scope.$watch('files', function() {
								$scope.upload($scope.files);
							});

							$scope.upload = function(files) {
								if (files && files.length) {
									for (var i = 0; i < files.length; i++) {
										var file = files[i];
										$upload
												.upload({
													url : 'upload',
													file : file
												})
												.progress(
														function(evt) {
															var progressPercentage = parseInt(100.0
																	* evt.loaded
																	/ evt.total);
															console
																	.log('progress: '
																			+ progressPercentage
																			+ '% '
																			+ evt.config.file.name);
														})
												.success(
														function(data, status,
																headers, config) {
															$scope.hospital.logo = data;
														});
									}
								}
							};

						} ]);
// end editcategorypopup
