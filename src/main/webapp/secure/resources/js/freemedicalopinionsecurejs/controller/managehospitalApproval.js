secureApp
		.controller(
				'manageHospitalApproval',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'specilityService',
						'countryService',
						'hospitalService',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								specilityService, countryService,
								hospitalService) {

							$scope.launch = function(which, content) {

								specilityService
										.addSpecility($scope.listOfProcedure);
								countryService
										.addCountryData($scope.listofcountry);

								hospitalService.addHospital(content);

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create(
											'getmanagehospitalapprovalpopup',
											'manageHospitalApprovalpopup', {},
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
				'manageHospitalApprovalpopup',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'specilityService',
						'countryService',
						'hospitalService',
						'$route',
						function($scope, $http, $modalInstance,
								specilityService, countryService,
								hospitalService, $route) {

							$scope.listOfProcedure = specilityService
									.getSpecility();

							$scope.listofcountry = countryService.getCountry();
							$scope.hospital = hospitalService.getHospital();

							$scope.cancel = function() {
								$modalInstance.dismiss('canceled');
							}; // end cancel

							$scope.approve = function() {
								$http.post('approvehospital',
										$scope.hospital.hospitalId).success(
										function(data) {
											$route.reload();
										});
								$modalInstance.close();
							}; // end save

							$scope.reject = function() {
								$http.post('rejectHospital',
										$scope.hospital.hospitalId).success(
										function(data) {
											$route.reload();
										});
								$modalInstance.close();
							}; // end save

							$scope.hitEnter = function(evt) {
								if (angular.equals(evt.keyCode, 13)
										&& !(angular.equals(
												$scope.category.name, null) || angular
												.equals($scope.category.name,
														'')))
									$scope.save();
							}; // end hitEnter
						} ])
// end editcategorypopup
