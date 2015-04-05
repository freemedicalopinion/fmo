app.controller('RegistrationCtrl', [
		'$scope',
		'$http',
		function($scope, $http) {

			// save the 'Registration' form
			$scope.registrationFailMessage = null;
			$scope.registrationSucccess = null;

			$scope.register = function() {
				$scope.registrationFailMessage = null;
				$scope.registrationSucccess = null;
				$http.post('registration', $scope.registration).success(
						function(data) {
							
							if (data.responseStatus == 'FAIL') {
								$scope.registrationFailMessage=data.errorMessage;
							} else {
								$scope.registrationSucccess = data.errorMessage;
							}

						});
			};

		} ]);