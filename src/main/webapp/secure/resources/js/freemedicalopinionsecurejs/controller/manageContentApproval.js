secureApp
		.controller(
				'manageContentApproval',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'contentService',
						'CategoryService',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								contentService, CategoryService) {

							$scope.launch = function(which, content) {
								contentService.addContent(content);
								CategoryService
										.addCategoryData($scope.categories);

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create(
											'getcontentapprovalpopup',
											'managecontentapprovalpopup', {}, {
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
				'managecontentapprovalpopup',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'contentService',
						'CategoryService',
						'$route',
						function($scope, $http, $modalInstance, contentService,
								CategoryService,$route) {
							$scope.content = contentService.getContent();
							$scope.categories = CategoryService.getCategory();

							$scope.cancel = function() {
								$modalInstance.dismiss('canceled');
							}; // end cancel

							$scope.approve = function() {
								$http.post('approveContent',
										$scope.content).success(
										function(data) {
											$route.reload();
										});
								$modalInstance.close();
							}; // end save

							$scope.reject = function() {
								$http.post('rejectContent',
										$scope.content).success(
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
