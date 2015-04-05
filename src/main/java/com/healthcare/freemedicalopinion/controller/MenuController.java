package com.healthcare.freemedicalopinion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.MenuEO;

@Controller
public class MenuController {

	/**
	 * Common
	 * */
	@RequestMapping(value = { "/secure/getMenuForUser" }, method = RequestMethod.POST)
	public @ResponseBody List<MenuEO> getMenuForLoggedInUser() {

		List<String> roles = UserUtility.getLoggedInUserRoleList();
		List<MenuEO> menu= getMenuForCommon();

		if (roles.contains(Role.ROLE_ADMIN.toString())) {
			menu.addAll(getMenuForAdmin());
		} else if (roles.contains(Role.ROLE_DOCTOR.toString())) {
			menu.addAll(getMenuForDoctor());
		} else if (roles.contains(Role.ROLE_HOSPITAL.toString())) {
			menu.addAll(getMenuForHospital());
		} else if (roles.contains(Role.ROLE_USER.toString())) {
			menu.addAll(getMenuForUser());
		}

		return menu;
	}

	private List<MenuEO> getMenuForCommon() {
		List<MenuEO> menus = new ArrayList<MenuEO>();
		menus.add(new MenuEO("Manage Content", "#!/managecontent"));

		return menus;
	}

	private List<MenuEO> getMenuForAdmin() {
		List<MenuEO> menus = new ArrayList<MenuEO>();
		menus.add(new MenuEO("Category", "#!/viewcategory"));
		menus.add(new MenuEO("Content Approval", "#!/managecontentapproval"));
		menus.add(new MenuEO("Manage Specility", "#!/managespecility"));
		menus.add(new MenuEO("manage Procedure", "#!/manageprocedure"));
		menus.add(new MenuEO("Manage Address City", "#!/manageaddresscountry"));
		menus.add(new MenuEO("hospital Approval", "#!/managehospitalapproval"));
		return menus;
	}

	private List<MenuEO> getMenuForDoctor() {
		List<MenuEO> menus = new ArrayList<MenuEO>();
		return menus;
	}

	private List<MenuEO> getMenuForHospital() {
		List<MenuEO> menus = new ArrayList<MenuEO>();
		menus.add(new MenuEO("Manage Hospital", "#!/managehospitalprofile"));
		return menus;
	}

	private List<MenuEO> getMenuForUser() {
		List<MenuEO> menus = new ArrayList<MenuEO>();
		return menus;
	}
}
