package com.healthcare.freemedicalopinion.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.UserProfileService;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

@Component
public class UserProfileManager {

	@Autowired
	UserProfileService userProfileService;

	public void addUserProfile(UserProfileVO vo) {
		userProfileService.addUserProfile(vo);
	}

	public UserProfileVO getUserProfileByUsername(String username) {
		return userProfileService.getUserProfileByUsername(username);
	}

	public UserProfileVO removeUserProfileOfLoggedInUser() {

		return userProfileService.removeUserProfile(UserUtility
				.getLoggedInUserUserName());

	}

}
