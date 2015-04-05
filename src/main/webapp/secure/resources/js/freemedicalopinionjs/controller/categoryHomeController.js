app.controller('CategoryHomeCtrl', [
		'$scope',
		'$rootScope',
		'$http',
		'MetaService',
		function($scope, $rootScope, $http, MetaService){
			// save the 'Registration' form
			$rootScope.metaservice = MetaService;
			$rootScope.metaservice.set("Test","desc","blah blah");
			
			$scope.articlesLimit = 4;
			$http.post('articles', null).success(function(data) {
				$scope.contents = data.contents;
			});

			$http.post('getallcategories', null).success(function(data) {
				$scope.categories = data;
			});
		} ]);