secureApp.filter('capitalize', function() {
	return function(input, all) {
		return (!!input) ? input.replace(/([^\W_]+[^\s-]*) */g, function(txt) {
			return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
		}) : '';
	}
});

secureApp.filter('isNull', function() {
	return function(input) {
		if (input == null || input == "") {
			return "--";
		} else {
			return input;
		}
	}
});

secureApp.filter('procedureName', function() {
	return function(hospital, allProcedures) {
		var filtered = [];
		if (allProcedures != null) {
			for (var i = 0; i < allProcedures.length; i++) {

				for (var j = 0; j < hospital.length; j++) {

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

secureApp.filter('countryName', function() {
	return function(countryId, countries) {
		if (countries != null && countryId != null) {
			for (var i = 0; i < countries.length; i++) {

				if (countries[i].countryId == countryId) {
					return countries[i].countryName;
				}

			}
		}

	};
});

secureApp.filter('stateName', function() {
	return function(stateId, countries) {
		if (countries != null && stateId != null) {
			for (var i = 0; i < countries.length; i++) {
				if (countries[i].states != null) {
					for (var j = 0; j < countries[i].states.length; j++) {
						if (countries[i].states[j].stateId == stateId) {
							return countries[i].states[j].stateName;
						}
					}
				}
			}
		}

	};
});

secureApp
		.filter(
				'cityName',
				function() {
					return function(cityId, countries) {
						if (countries != null && cityId != null) {
							for (var i = 0; i < countries.length; i++) {
								if (countries[i].states != null) {
									for (var j = 0; j < countries[i].states.length; j++) {
										if (countries[i].states[j].cities != null) {
											for (var k = 0; k < countries[i].states[j].cities.length; k++) {
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
