secureApp.service('hospitalService', function() {
	var hospital = {};

	return {
		getHospital : function() {
			return hospital;
		},
		reSetHospital : function() {
			hospital = {};
		},
		addHospital : function(contentData) {
			hospital = contentData;

		}
	};

});