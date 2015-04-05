secureApp.service('contentService', function() {
	var content = {};

	return {
		getContent : function() {
			return content;
		},
		reSetContent : function() {
			content = {};
		},
		addContent : function(contentData) {
			content = contentData;

		}
	};

});