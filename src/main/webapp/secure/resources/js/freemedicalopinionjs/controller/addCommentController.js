app.controller('addCommentCtr', [
		'$scope',
		'$http',
		function($scope, $http) {

			$scope.loginTocomment = false;

			$scope.addComment = function(data) {
				if ($scope.loginData.login == false) {
					$scope.loginTocomment = true;
				} else {
					$scope.loginTocomment = false;
					$scope.commentdata.contentId = data;

					$http.post('secure/addComment', $scope.commentdata)
							.success(function(data) {
								$scope.commentdata.comment = null;
							});
				}

			};

		} ]);

app.controller('searchCtrl', [ '$scope', '$http', '$location', '$window',
		function($scope, $http, $location, $window) {

			$scope.search = function() {
				$window.location.href = '#!/searchcontent/' + $scope.searchkey;
			};

		} ]);