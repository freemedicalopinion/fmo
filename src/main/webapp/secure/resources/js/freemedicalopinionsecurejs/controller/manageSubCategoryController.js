secureApp
		.controller(
				'manageSubCategoryController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'CategoryService',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								CategoryService) {

							$scope.launch = function(which, categoryId,
									subcategory) {

								if (subcategory != null) {

									CategoryService.addSubCategoryData(
											categoryId, subcategory);
								} else {
									CategoryService.reSetCategory();
									CategoryService.addSubCategoryData(
											categoryId, {});
								}

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create(
											'getmanagesubcategorypopup',
											'managesubcategorypopup', {}, {
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
				'managesubcategorypopup',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'CategoryService',
						'$route',
						function($scope, $http, $modalInstance, CategoryService,$route) {

							$scope.category = CategoryService.getCategory();
							var categoryBck = JSON.parse(JSON.stringify($scope.category));
							$scope.errorMessage=null;
							$scope.isUnique=true;
							//localStorage.setItem("categoryBck", k);
							
							$scope.cancel = function() {
								
								$scope.category.subCategories[0].subCategoryName=categoryBck.subCategories[0].subCategoryName;
								$scope.category.subCategories[0].description=categoryBck.subCategories[0].description;
								$scope.category.subCategories[0].tags=categoryBck.subCategories[0].tags;
								
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
										.post('manageSubcategory',
												$scope.category).success(
												function(data) {
													if(data.responseStatus == 'SUCCESS'){
														if(data.unique == false){
															$scope.isUnique=false;
															$scope.errorMessage=data.message;
														}else{
															$modalInstance.close();
															$route.reload();
														
														}
														
													}else{
														$scope.errorMessage=data.errorMessage;
													}		

												});
							}; // end hitEnter

							
						} ]);
// end editcategorypopup
