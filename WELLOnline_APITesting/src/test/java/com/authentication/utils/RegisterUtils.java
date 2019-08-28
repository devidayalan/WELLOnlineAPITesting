package com.authentication.utils;

import org.apache.commons.lang3.RandomStringUtils;
public class RegisterUtils {
	

	public static String getEmail() {
		String randomString = RandomStringUtils.randomAlphabetic(3);
		return "test"+randomString+"@gmail.com";
		
	}
	public static String getOrganization() {
		String randomString = RandomStringUtils.randomAlphabetic(1);
		return "testcompany"+randomString;
		
	}
	public static String getFirstName() {
		String randomString = RandomStringUtils.randomAlphabetic(2);
		return "test"+randomString;
		
	}
	public static String getLastName() {
		String randomString = RandomStringUtils.randomAlphabetic(2);
		return "user"+ randomString;
		
	}

}


