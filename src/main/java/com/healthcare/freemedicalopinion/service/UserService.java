package com.healthcare.freemedicalopinion.service;

import com.healthcare.freemedicalopinion.security.CustomUserDetails;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserVO;

public interface UserService {

	public UserVO getUserByUserName(String username);

	public CustomUserDetails getUserDetailsForLogin(String username);

	public void addUser(UserProfileVO proile, UserVO user) throws Exception;

	public void changepassword(String hashedpassword) throws Exception;

	boolean changeRole(Role newRole) throws Exception;

}
