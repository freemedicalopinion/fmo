secureApp.service('countryService', function() {
	var country = {};

	return {
		getCountry : function() {
			return country;
		},
		reSetCountry : function() {
			country = {};
		},
		addCountryData : function(countryData) {
			country = {};
			country = countryData;

		},

		addStateData : function(countryId, state) {

			country = {};
			country.countryId = countryId;

			country.states = [ state ];

		},
		addCityData : function(countryId, stateId, city) {
			country = {};
			country.countryId = countryId;
			country.states = [ {} ];
			country.states[0].stateId = stateId;
			country.states[0].cities = [ city ];
		}
	};

});
