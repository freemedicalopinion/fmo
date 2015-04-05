app.filter('removespaces', function() {
	return function(value) {
		if (value == null)
			return '';
		return str = value.split(" ").join("-");

	};
});

app.filter('categoryImage', function() {
	return function(items, content) {
		var filtered = [];

		if (content.imageName != null) {
			filtered.push(content.imageName);
		} else {
			if (items != null) {
				for ( var i = 0; i < items.length; i++) {
					var item = items[i];
					if (item.categoryId == content.categoryId) {
						filtered.push(item.imageName);
					}
				}
			}

		}

		return filtered;
	};
});

app.filter('contentPostedIn', function() {
	return function(categories, content) {

		var filtered = [];
		if (categories != null) {
			for ( var i = 0; i < categories.length; i++) {
				var item = categories[i];
				if (item.categoryId == content.categoryId) {
					filtered.push(item.name);
				}
			}
		}
		if (content.tags != null) {
			var item = [];
			item = content.tags.split(",");
			for ( var i = 0; i < item.length; i++) {
				filtered.push(item[i]);
			}
		}
		return filtered.slice(0, 5);
	};
});

app.filter('capitalize', function() {
	return function(input, all) {
		return (!!input) ? input.replace(/([^\W_]+[^\s-]*) */g, function(txt) {
			return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
		}) : '';
	}
});

app.filter('isNull', function() {
	return function(input) {
		if (input == null || input == "") {
			return "--";
		} else {
			return input;
		}
	}
});

app.filter('procedureName', function() {
	return function(hospital, allProcedures) {
		var filtered = [];
		if (allProcedures != null) {
			for ( var i = 0; i < allProcedures.length; i++) {

				for ( var j = 0; j < hospital.length; j++) {

					var item = allProcedures[i];
					if (item.procedureId == hospital[j]) {
						filtered.push(item);
					}
				}

			}
		}
		return filtered;
	};
});

app.filter('countryName', function() {
	return function(countryId, countries) {
		if (countries != null && countryId != null) {
			for ( var i = 0; i < countries.length; i++) {

				if (countries[i].countryId == countryId) {
					return countries[i].countryName;
				}

			}
		}

	};
});

app.filter('stateName', function() {
	return function(stateId, countries) {
		if (countries != null && stateId != null) {
			for ( var i = 0; i < countries.length; i++) {
				if (countries[i].states != null) {
					for ( var j = 0; j < countries[i].states.length; j++) {
						if (countries[i].states[j].stateId == stateId) {
							return countries[i].states[j].stateName;
						}
					}
				}
			}
		}

	};
});

app
		.filter(
				'cityName',
				function() {
					return function(cityId, countries) {
						if (countries != null && cityId != null) {
							for ( var i = 0; i < countries.length; i++) {
								if (countries[i].states != null) {
									for ( var j = 0; j < countries[i].states.length; j++) {
										if (countries[i].states[j].cities != null) {
											for ( var k = 0; k < countries[i].states[j].cities.length; k++) {
												if (countries[i].states[j].cities[k].cityId == cityId) {
													return countries[i].states[j].cities[k].cityName;
												}
											}
										}
									}
								}

							}
						}

					};
				});
