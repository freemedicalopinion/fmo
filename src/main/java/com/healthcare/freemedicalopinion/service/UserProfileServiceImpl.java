package com.healthcare.freemedicalopinion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.UserProfileEO;
import com.healthcare.freemedicalopinion.repository.UserProfileRepository;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public void addUserProfile(UserProfileVO vo) {
		userProfileRepository.save(new UserProfileEO(vo));

	}

	@Override
	public UserProfileVO getUserProfileByUsername(String username) {
		List<UserProfileEO> eos = userProfileRepository
				.findByUsername(username);
		if (eos != null && eos.size() > 0) {
			return new UserProfileVO(eos.get(0));
		}
		return null;
	}

	@Override
	public UserProfileVO removeUserProfile(String username) {

		UserProfileVO vo = null;
		List<UserProfileEO> eos = userProfileRepository
				.findByUsername(username);
		if (eos != null && eos.size() > 0) {
			vo = new UserProfileVO(eos.get(0));
			userProfileRepository.delete(eos.get(0).getId());
		}

		return vo;
	}
}
