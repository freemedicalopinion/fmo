package com.healthcare.freemedicalopinion.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.healthcare.freemedicalopinion.utility.AccessEnum.AdminUserUrls;
import com.healthcare.freemedicalopinion.utility.AccessEnum.CommonUrls;
import com.healthcare.freemedicalopinion.utility.AccessEnum.DoctorUserUrls;
import com.healthcare.freemedicalopinion.utility.AccessEnum.HospitalUserUrls;
import com.healthcare.freemedicalopinion.utility.AccessEnum.UserUrls;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;
import com.healthcare.freemedicalopinion.utility.UserUtility;

public class Interceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		StringBuffer requestUrl = request.getRequestURL();
		if (requestUrl.indexOf("/secure/resources/") > 0) {
			return true;
		}
		if (requestUrl.indexOf("/secure/") > 0) {
			String requestedResource = requestUrl.substring(
					requestUrl.lastIndexOf("/") + 1, requestUrl.length());
			List<String> roles = UserUtility.getLoggedInUserRoleList();
			/**
			 * Check for common urls
			 * */
			if (CommonUrls.getUserUrlsList().contains(requestedResource)) {
				

				return true;
			}

			if (roles.contains(Role.ROLE_ADMIN.toString())) {
				if (!AdminUserUrls.getAdminUserUrlsList().contains(
						requestedResource)) {
					throw new Exception("request for requestUrl denied "
							+ requestUrl);
				}
			} else if (roles.contains(Role.ROLE_DOCTOR.toString())) {
				if (!DoctorUserUrls.getDoctorUserUrlsList().contains(
						requestedResource)) {
					throw new Exception("request for requestUrl denied "
							+ requestUrl);
				}
			} else if (roles.contains(Role.ROLE_HOSPITAL.toString())) {
				if (!HospitalUserUrls.getHospitalUserUrlsList().contains(
						requestedResource)) {
					throw new Exception("request for requestUrl denied "
							+ requestUrl);
				}
			} else if (roles.contains(Role.ROLE_USER.toString())) {
				if (!UserUrls.getUserUrlsList().contains(requestedResource)) {
					throw new Exception("request for requestUrl denied "
							+ requestUrl);
				}
			}
		}

		request.setAttribute("startTime", startTime);

		return true;
	}

	// after the handler is executed
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		long startTime = (Long) request.getAttribute("startTime");

		long endTime = System.currentTimeMillis();

		long executeTime = endTime - startTime;

	}

}
