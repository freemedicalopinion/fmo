package com.healthcare.freemedicalopinion.service;

import java.util.List;

import com.healthcare.freemedicalopinion.valueobject.SpecilityVO;

public interface SpeciltyService {

	public List<SpecilityVO> getAllSpecilites();

	public SpecilityVO getAllSpecilityBySpecilityName(String specilityName);

	public SpecilityVO getAllSpecilityBySpecilityId(String specilityId);

	public void addSpecility(SpecilityVO vo);

	public void editSecility(SpecilityVO vo) throws Exception;

}
