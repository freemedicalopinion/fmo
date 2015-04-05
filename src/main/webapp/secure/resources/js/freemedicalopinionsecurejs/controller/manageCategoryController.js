secureApp
		.controller(
				'manageCategoryController',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'CategoryService',
						'$route',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								CategoryService ,$route) {

							$scope.launch = function(which, data) {

								if (data != null) {

									CategoryService.addCategoryData(data);
								} else {
									CategoryService.reSetCategory();
								}

								var dlg = null;

								switch (which) {

								// Create Your Own Dialog
								case 'create':
									dlg = $dialogs.create(
											'getmanagecategorypopup',
											'managecategorypopup', {}, {
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
				'managecategorypopup',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'CategoryService',
						'$route',
						'$upload',
						function($scope, $http, $modalInstance, CategoryService,$route,$upload) {

							$scope.category = CategoryService.getCategory();
							$scope.errorMessage = null;
							$scope.isUnique = true;

							var categoryBck = JSON.parse(JSON.stringify($scope.category));

							$scope.cancel = function() {
								$scope.category.name =categoryBck.name;
								$scope.category.description =categoryBck.description;
								$scope.category.tags =categoryBck.tags;
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
								$http.post('addcategory', $scope.category)
										.success(function(data) {
											
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
															$scope.category.imageName = data;
														});
									}
								}
							};

						} ]);
// end editcategorypopup
