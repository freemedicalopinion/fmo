app.controller('StarCtrl', [ '$scope', function($scope) {

	$scope.rating = 0;

	$scope.getSelectedRating = function(rating) {
		console.log(rating);
	}

} ]);

app
		.directive(
				'starRating',
				function() {
					return {
						restrict : 'A',
						template : '<ul class="rating">'
								+ '<li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">'
								+ '\u2605' + '</li>' + '</ul>',
						scope : {
							ratingValue : '=',
							max : '=',
							onRatingSelected : '&'
						},
						link : function(scope, elem, attrs) {
							scope.stars = [];
							for (var i = 0; i < scope.max; i++) {
								scope.stars.push({
									filled : i < scope.ratingValue
								});
							}

							var updateStars = function() {
								scope.stars = [];
								for (var i = 0; i < scope.max; i++) {
									scope.stars.push({
										filled : i < scope.ratingValue
									});
								}
							};

							scope.toggle = function(index) {
								scope.ratingValue = index + 1;
								scope.onRatingSelected({
									rating : index + 1

								});
								updateStars();
							};

							scope.$watch('ratingValue',
									function(oldVal, newVal) {
										if (newVal) {
											updateStars();
										}
									});
						}
					}
				});


app.directive('starRatingstatic', function () {
    return {
        restrict: 'A',
        template: '<ul class="rating">' +
            '<li ng-repeat="star in stars" ng-class="star">' +
            '\u2605' +
            '</li>' +
            '</ul>',
        scope: {
            ratingValue: '=',
            max: '='
        },
        link: function (scope, elem, attrs) {
            scope.stars = [];
            for (var i = 0; i < scope.max; i++) {
                scope.stars.push({
                    filled: i < scope.ratingValue
                });
            }
        }
    }
});