secureApp.service('specilityService', function() {
	var specility = {};

	return {
		getSpecility : function() {
			return specility;
		},
		reSetSpecility : function() {
			specility = {};
		},
		addSpecility : function(contentData) {
			specility = contentData;

		}
	};

});