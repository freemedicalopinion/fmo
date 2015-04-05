package com.healthcare.freemedicalopinion.valueobject;

public class ChangePasswordVO {

	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword
	 *            the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confirmNewPassword
	 */
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	/**
	 * @param confirmNewPassword
	 *            the confirmNewPassword to set
	 */
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

}
