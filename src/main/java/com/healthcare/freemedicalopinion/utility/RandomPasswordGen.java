package com.healthcare.freemedicalopinion.utility;

import java.util.UUID;

public class RandomPasswordGen {

	public static String randompassword() {
		String uuid = UUID.randomUUID().toString();
		System.out.println("password " + uuid);
		return uuid;
	}

}
