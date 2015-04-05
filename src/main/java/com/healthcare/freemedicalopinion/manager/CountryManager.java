package com.healthcare.freemedicalopinion.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.CountryService;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.valueobject.AddressCityVO;
import com.healthcare.freemedicalopinion.valueobject.AddressCountryVO;
import com.healthcare.freemedicalopinion.valueobject.AddressStateVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;

@Component
public class CountryManager {

	@Autowired
	CountryService countryService;

	@Autowired
	ReadValueFromMessageSource message;

	public List<AddressCountryVO> getAllCountry() {
		return countryService.getAllCountry();

	}

	public CategoryManageReponseVO manageCountry(AddressCountryVO vo) {
		CategoryManageReponseVO response = new CategoryManageReponseVO();
		try {
			if (vo.getCountryName() == null
					|| vo.getCountryName().trim().equalsIgnoreCase("")) {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("country.namenotnull"));
				return response;

			}

			if (vo != null) {

				if (checkUniqueCountrynames(vo)) {
					response.setUnique(true);
					if (vo.getCountryId() == null) {
						vo.setCountryId(String.valueOf(new Date().getTime()));
						countryService.addCountry(vo);
					} else {
						countryService.editCountry(vo);
					}

				} else {
					// Country Name is not unique
					response.setResponseStatus(HTTP_RESPONSE.FAIL);
					response.setErrorMessage(message
							.readValueByKey("country.namenotunique"));
					return response;
				}
				response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
			}
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
			return response;
		}

		return response;
	}

	public CategoryManageReponseVO manageState(AddressCountryVO vo) {
		CategoryManageReponseVO response = new CategoryManageReponseVO();
		try {
			if (vo.getStates().get(0).getStateName() == null
					|| vo.getStates().get(0).getStateName().trim()
							.equalsIgnoreCase("")) {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("state.namenotnull"));
				return response;

			}

			if (vo != null) {
				if (checkUniqueStateNames(vo)) {
					response.setUnique(true);
					if (vo.getStates().get(0).getStateId() == null) {
						vo.getStates()
								.get(0)
								.setStateId(
										String.valueOf(new Date().getTime()));
						countryService.addState(vo);
					} else {
						countryService.editState(vo);
					}

				} else {
					// Country Name is not unique
					response.setResponseStatus(HTTP_RESPONSE.FAIL);
					response.setErrorMessage(message
							.readValueByKey("state.namenotunique"));
					return response;
				}
				response.setResponseStatus(HTTP_RESPONSE.SUCCESS);

			}
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
			return response;
		}

		return response;
	}

	public CategoryManageReponseVO manageCity(AddressCountryVO vo) {
		CategoryManageReponseVO response = new CategoryManageReponseVO();
		try {

			if (vo.getStates().get(0).getCities().get(0).getCityName() == null
					|| vo.getStates().get(0).getCities().get(0).getCityName().trim()
							.equalsIgnoreCase("")) {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("city.namenotnull"));
				return response;

			}

			if (vo != null) {
				if (checkUniqueCityNames(vo)) {
					response.setUnique(true);
					if (vo.getStates().get(0).getCities().get(0).getCityId() == null) {
						vo.getStates()
								.get(0)
								.getCities()
								.get(0)
								.setCityId(String.valueOf(new Date().getTime()));
						countryService.addCity(vo);
					} else {
						countryService.editCity(vo);
					}
				} else {
					// Country Name is not unique
					response.setResponseStatus(HTTP_RESPONSE.FAIL);
					response.setErrorMessage(message
							.readValueByKey("city.namenotunique"));
					return response;
				}
				response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
			}
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
			return response;
		}
		return response;
	}

	private boolean checkUniqueCountrynames(AddressCountryVO vo) {

		AddressCountryVO vos = countryService.getCountryByCountryName(vo
				.getCountryName());
		if (vos == null
				|| vos.getCountryId().equalsIgnoreCase(vo.getCountryId())) {
			return true;
		}

		return false;

	}

	private boolean checkUniqueStateNames(AddressCountryVO vo) {

		AddressCountryVO vos = countryService.getCountryByCountryId(vo
				.getCountryId());
		if (vos != null && vos.getStates() != null) {
			for (AddressStateVO voState : vos.getStates()) {
				if (voState.getStateName().equalsIgnoreCase(
						vo.getStates().get(0).getStateName())) {
					if (voState.getStateId().equalsIgnoreCase(
							vo.getStates().get(0).getStateId())) {
						return true;
					} else {
						return false;
					}

				}
			}
			return true;
		} else if (vos != null && vos.getStates() == null) {
			return true;
		}

		return false;

	}

	private boolean checkUniqueCityNames(AddressCountryVO vo) {

		AddressCountryVO vos = countryService.getCountryByCountryId(vo
				.getCountryId());
		if (vos != null && vos.getStates() != null) {
			for (AddressStateVO voState : vos.getStates()) {
				if (voState.getStateId().equalsIgnoreCase(
						vo.getStates().get(0).getStateId())) {

					if (voState.getCities() == null) {
						return true;
					} else {
						for (AddressCityVO city : voState.getCities()) {
							if (city.getCityName().equalsIgnoreCase(
									vo.getStates().get(0).getCities().get(0)
											.getCityName())) {

								if (city.getCityId().equalsIgnoreCase(
										vo.getStates().get(0).getCities()
												.get(0).getCityId())) {
									return true;
								} else {
									return false;
								}

							}

						}
						return true;
					}

				}
			}
		}

		return false;

	}
}
