package com.healthcare.freemedicalopinion.utility;

public class ContentStatusEnum {

	public enum ContentStatus {
		NEW("New"), APPROVED("Approved"), REJECTED("Rejected"), PENDING_APPROVAL(
				"Pending Approval"), APPROVED_UPDATED("New");

		private ContentStatus(String status) {
			this.status = status;
		}

		private String status;

	}

}
