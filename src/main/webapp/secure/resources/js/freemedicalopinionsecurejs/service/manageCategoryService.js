secureApp.service('CategoryService', function() {
	var category = {};

	return {
		getCategory : function() {
			return category;
		},
		reSetCategory : function() {
			category = {};
		},
		addCategoryData : function(categoryData) {
			category = {};
			category = categoryData;

		},

		addSubCategoryData : function(categoryId, subcategory) {
			category = {};
			category.categoryId = categoryId;
			category.subCategories = [subcategory];

		}
	};

});


