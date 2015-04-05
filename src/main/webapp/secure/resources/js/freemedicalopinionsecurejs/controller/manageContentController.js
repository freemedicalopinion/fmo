secureApp
		.controller(
				'manageContent',
				[
						'$scope',
						'$rootScope',
						'$timeout',
						'$http',
						'$dialogs',
						'contentService',
						'CategoryService',
						'$route',
						function($scope, $rootScope, $timeout, $http, $dialogs,
								contentService, CategoryService,$route) {

							$scope.errormessage = "";

							$scope.launch = function(which, content) {

								CategoryService
										.addCategoryData($scope.categories);

								$scope.contentValidate = {};

								if (content != null) {
									contentService.addContent(content);
								} else {
									contentService.reSetContent();
								}

								var dlg = null;

								switch (which) {

								// Error Dialog
								case 'error':
									dlg = $dialogs.error($scope.errormessage);
									break;

								// Confirm Dialog
								case 'delete':
									dlg = $dialogs
											.confirm('Please Confirm',
													'Are you sure , want to delete this content.');
									dlg.result
											.then(
													function(btn) {
														$http
																.post(
																		'deleteContent',
																		$scope.content.contentId)
																.success(
																		function(
																				data) {
																			if (data.responseStatus == 'SUCCESS') {
																				$route.reload();

																			} else {
																				$scope.errormessage = data.errorMessage;

																				$scope
																						.launch('error');

																			}
																		});
													}, function(btn) {

													});
									break;

								// Confirm Dialog
								case 'publish':
									dlg = $dialogs
											.confirm(
													'Publish this content',
													'Are you sure , want to publish this content. Accepting this will change the staus of content to pending approval . After approval of Admin this content will be online . ');
									dlg.result
											.then(
													function(btn) {

														$http
																.post(
																		'publishContent',
																		$scope.content)
																.success(
																		function(
																				data) {
																			if (data.responseStatus == 'SUCCESS') {
																				if (data.valid == false) {
																					$scope.errormessage = data.message;
																					$scope
																							.launch('error');
																				}else{
																					$route.reload();
																				}

																			} else {
																				$scope.errormessage = data.errorMessage;
																			}

																		});

													}, function(btn) {

													});
									break;

								// Confirm Dialog
								case 'create':

									if ($scope.content != null
											&& $scope.content.status == 'APPROVED') {
										dlg = $dialogs
												.confirm(
														'Please Confirm',
														'Are you sure you want to edit the published content .On editing this it will create a new content for you . and after approval it will replace the current published content.');
										dlg.result.then(function(btn) {
											$scope.launch('confirmPublishEdit',
													$scope.content);
										}, function(btn) {

										});

									} else {
										$scope.launch('confirmPublishEdit',$scope.content);
									}

									break;

								// Create Your Own Dialog
								case 'confirmPublishEdit':

									dlg = $dialogs.create(
											'getmanagecontentpopup',
											'managecontentpopup', {}, {
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
				'managecontentpopup',
				[
						'$scope',
						'$http',
						'$modalInstance',
						'contentService',
						'CategoryService',
						'$upload',
						'$route',
						function($scope, $http, $modalInstance, contentService,
								CategoryService, $upload,$route) {

							$scope.content = contentService.getContent();
							$scope.categories = CategoryService.getCategory();
							$scope.errorMessage = null;
							$scope.validateData = {};
							
							var contentBck = JSON.parse(JSON.stringify($scope.content));

							$scope.cancel = function() {
								
								$scope.content.categoryId=contentBck.categoryId;
								$scope.content.subCategoryId=contentBck.subCategoryId;
								$scope.content.title=contentBck.title;
								$scope.content.imageName=contentBck.imageName;
								$scope.content.contentBody=contentBck.contentBody;
								$scope.content.tags=contentBck.tags;
								
								$modalInstance.dismiss('canceled');
							}; // end cancel

							$scope.saveContent = function() {
								$http
										.post('manageContent', $scope.content)
										.success(
												function(data) {
													if (data.responseStatus == 'SUCCESS') {
														if (data.valid == false) {
															$scope.validateData = data;
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
							}; // end save

							$scope.hitEnter = function(evt) {
								if (angular.equals(evt.keyCode, 13)
										&& !(angular.equals(
												$scope.category.name, null) || angular
												.equals($scope.category.name,
														'')))
									$scope.save();
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
															$scope.content.imageName = data;
														});
									}
								}
							};

						} ]);
// end editcategorypopup

