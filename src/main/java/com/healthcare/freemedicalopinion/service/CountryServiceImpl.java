package com.healthcare.freemedicalopinion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.AddressCityEO;
import com.healthcare.freemedicalopinion.entityobject.AddressCountryEO;
import com.healthcare.freemedicalopinion.entityobject.AddressStateEO;
import com.healthcare.freemedicalopinion.repository.CountryRepository;
import com.healthcare.freemedicalopinion.valueobject.AddressCountryVO;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Override
	public AddressCountryVO getCountryByCountryId(String countryId) {

		List<AddressCountryEO> eos = countryRepository
				.findByCountryId(countryId);
		if (eos != null && eos.size() > 0) {
			return new AddressCountryVO(eos.get(0));
		}
		return null;

	}

	@Override
	public AddressCountryVO getCountryByCountryName(String countryName) {
		List<AddressCountryEO> eos = countryRepository
				.findByCountryName(countryName);
		if (eos != null && eos.size() > 0) {
			return new AddressCountryVO(eos.get(0));
		}
		return null;
	}

	@Override
	public void addCountry(AddressCountryVO vo) {
		if (vo != null) {
			countryRepository.save(new AddressCountryEO(vo));
		}

	}

	@Override
	public List<AddressCountryVO> getAllCountry() {

		List<AddressCountryEO> eos = countryRepository.findAll();
		List<AddressCountryVO> vos = new ArrayList<AddressCountryVO>();
		for (AddressCountryEO eo : eos) {
			vos.add(new AddressCountryVO(eo));
		}
		return vos;
	}

	@Override
	public void addState(AddressCountryVO vo) {
		AddressCountryEO eoTosave = new AddressCountryEO(vo);

		if (vo != null && vo.getCountryId() != null) {
			List<AddressCountryEO> eos = countryRepository.findByCountryId(vo
					.getCountryId());
			if (eos != null && eos.size() > 0) {
				AddressCountryEO eo = eos.get(0);
				List<AddressStateEO> stateeo = eo.getStates();
				if (stateeo == null) {
					stateeo = new ArrayList<AddressStateEO>();
				}
				stateeo.addAll(eoTosave.getStates());
				eo.setStates(stateeo);
				countryRepository.save(eo);
			}
		}
	}

	@Override
	public void addCity(AddressCountryVO vo) {
		AddressCountryEO eoTosave = new AddressCountryEO(vo);

		if (vo != null && vo.getCountryId() != null) {
			List<AddressCountryEO> eos = countryRepository.findByCountryId(vo
					.getCountryId());
			if (eos != null && eos.size() > 0) {
				AddressCountryEO eo = eos.get(0);
				List<AddressStateEO> stateeos = eo.getStates();
				if (stateeos != null) {
					for (AddressStateEO stateeo : stateeos) {
						if (stateeo.getStateId().equalsIgnoreCase(
								eoTosave.getStates().get(0).getStateId())) {
							List<AddressCityEO> eoCities = stateeo.getCities();
							if (eoCities != null) {
								eoCities.addAll(eoTosave.getStates().get(0)
										.getCities());
							} else {
								eoCities = new ArrayList<AddressCityEO>();
								eoCities.addAll(eoTosave.getStates().get(0)
										.getCities());
								stateeo.setCities(eoCities);
							}
							countryRepository.save(eo);
						}
					}
				}

			}
		}

	}

	@Override
	public void editCountry(AddressCountryVO vo) {
		List<AddressCountryEO> eos = countryRepository.findByCountryId(vo
				.getCountryId());
		if (eos != null && eos.size() > 0) {
			AddressCountryEO eo = eos.get(0);
			eo.setCountryName(vo.getCountryName());
			countryRepository.save(eo);
		}

	}

	@Override
	public void editState(AddressCountryVO vo) {
		AddressCountryEO eoTosave = new AddressCountryEO(vo);
		AddressStateEO eoStateToEdit = eoTosave.getStates().get(0);

		if (vo != null && vo.getCountryId() != null) {
			List<AddressCountryEO> eos = countryRepository.findByCountryId(vo
					.getCountryId());
			if (eos != null && eos.size() > 0) {
				AddressCountryEO eo = eos.get(0);
				List<AddressStateEO> stateeos = eo.getStates();
				if (stateeos != null) {
					for (AddressStateEO stateeo : stateeos) {
						if (stateeo.getStateId().equalsIgnoreCase(
								eoStateToEdit.getStateId())) {
							stateeo.setStateName(eoStateToEdit.getStateName());
							countryRepository.save(eo);
						}
					}
				}

			}
		}
	}

	@Override
	public void editCity(AddressCountryVO vo) {
		AddressCountryEO eoTosave = new AddressCountryEO(vo);
		AddressCityEO eoCityToSave = eoTosave.getStates().get(0).getCities()
				.get(0);

		if (vo != null && vo.getCountryId() != null) {
			List<AddressCountryEO> eos = countryRepository.findByCountryId(vo
					.getCountryId());
			if (eos != null && eos.size() > 0) {
				AddressCountryEO eo = eos.get(0);
				List<AddressStateEO> stateeos = eo.getStates();
				if (stateeos != null) {
					for (AddressStateEO stateeo : stateeos) {
						if (stateeo.getStateId().equalsIgnoreCase(
								eoTosave.getStates().get(0).getStateId())) {
							List<AddressCityEO> eoCities = stateeo.getCities();
							if (eoCities != null) {
								for (AddressCityEO eoCity : eoCities) {
									if (eoCity.getCityId().equalsIgnoreCase(
											eoCityToSave.getCityId())) {
										eoCity.setCityName(eoCityToSave
												.getCityName());
										countryRepository.save(eo);
									}
								}

							}
						}
					}
				}

			}
		}

	}

}
