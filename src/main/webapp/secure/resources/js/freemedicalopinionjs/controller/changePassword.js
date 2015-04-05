appFmo.controller('changepasswordctrl', [
		'$scope',
		'$http',
		function($scope, $http,$route,$window) {

			// save the 'Registration' form
			
			$scope.registrationFailMessage=null;
			$scope.registrationSuccessMessage=null;

			$scope.changePasswordSubmit = function() {
				$scope.registrationFailMessage=null;
				$scope.registrationSuccessMessage=null;
				$http.post('home', $scope.changepassword).success(
						function(data) {
							
							if (data.responseStatus == 'FAIL') {
								$scope.registrationFailMessage=data.errorMessage;
								
							} else {
								$scope.registrationSuccessMessage=data.message;
							}

						});
			};

		} ]);