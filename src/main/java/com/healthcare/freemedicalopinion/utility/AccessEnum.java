package com.healthcare.freemedicalopinion.utility;

import java.util.Arrays;
import java.util.List;

public class AccessEnum {

	public enum AdminUserUrls {

		Urls(Arrays.asList(new String[] { "viewcountry", "manageCountry",
				"manageCountryPopup", "manageState", "manageStatePopup",
				"manageCity", "manageCityPopup", "viewcategory", "addcategory",
				"getmanagecategorypopup", "getmanagesubcategorypopup",
				"manageSubcategory", "managecontentapproval",
				"getcontentapprovalpopup", "getallcontentforapproval",
				"approveContent", "rejectContent", "viewprocedure",
				"manageprocedure", "manageprocedurepopup", "viewspecility",
				"managespecility", "managespecilitypopup",
				"managehospitalapproval", "getmanagehospitalapprovalpopup",
				"getallHospitalForApproval", "approvehospital",
				"rejectHospital" }));

		AdminUserUrls(List<String> adminUserUrlsList) {
			this.adminUserUrlsList = adminUserUrlsList;
		}

		List<String> adminUserUrlsList;

		public static List<String> getAdminUserUrlsList() {

			return AdminUserUrls.Urls.adminUserUrlsList;
		}

	}

	public enum DoctorUserUrls {

		Urls(Arrays.asList(new String[] {}));
		DoctorUserUrls(List<String> doctorUserUrlsList) {
			this.doctorUserUrlsList = doctorUserUrlsList;
		}

		List<String> doctorUserUrlsList;

		public static List<String> getDoctorUserUrlsList() {

			return DoctorUserUrls.Urls.doctorUserUrlsList;
		}

	}

	public enum HospitalUserUrls {
		Urls(Arrays.asList(new String[] { "viewhospital", "manageHospital",
				"getmanagehospitalpopup", "gethospitalInfoOfLoggedIn",
				"publishHospital" }));

		HospitalUserUrls(List<String> hospitalUserUrlsList) {
			this.hospitalUserUrlsList = hospitalUserUrlsList;
		}

		List<String> hospitalUserUrlsList;

		public static List<String> getHospitalUserUrlsList() {

			return HospitalUserUrls.Urls.hospitalUserUrlsList;
		}

	}

	public enum UserUrls {
		Urls(Arrays.asList(new String[] { "getupgradetohospitalpopup",
				"upgradetodoctor", "upgradetohospital" }));

		UserUrls(List<String> userUrlsList) {
			this.userUrlsList = userUrlsList;
		}

		List<String> userUrlsList;

		public static List<String> getUserUrlsList() {

			return UserUrls.Urls.userUrlsList;
		}

	}

	public enum CommonUrls {
		Urls(Arrays.asList(new String[] { "getallcountries", "",
				"getallcategories", "getmanagecontentpopup", "manageContent",
				"deleteContent", "publishContent", "managecontent",
				"getallcontent", "upload", "home", "getallHospital",
				"getallprocedures", "getallspecilities", "checkUserLogin",
				"getMenuForUser", "getAllStatuses" }));

		CommonUrls(List<String> commonUrlsList) {
			this.urlsList = commonUrlsList;
		}

		List<String> urlsList;

		public static List<String> getUserUrlsList() {

			return CommonUrls.Urls.urlsList;
		}

	}
}
