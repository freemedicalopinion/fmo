secureApp.directive('banner', function($window) {

	return {
		restrict : 'A',

		link : function(scope, elem, attrs) {

			var winHeight = $window.innerHeight;

			elem.css('height', winHeight - 225 + 'px');
		}
	};
});


