package com.healthcare.freemedicalopinion.service;

import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

public interface UserProfileService {

	void addUserProfile(UserProfileVO vo);

	UserProfileVO getUserProfileByUsername(String username);

	UserProfileVO removeUserProfile(String username);

}
