package com.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
public class GenerateRandomTestDataUtils {
	

	public static String getEmail() {
		String randomString = RandomStringUtils.randomAlphabetic(3);
		return "test"+randomString+"@gmail.com";
		
	}
	public static String getOrganization() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "testcompany"+randomString;
		
	}
	public static String getFirstName() {
		String randomString = RandomStringUtils.randomAlphabetic(2);
		return "test"+randomString;
		
	}
	public static String getLastName() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "user"+ randomString;
		
	}
	public static String getProjectName() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "Test Project"+ randomString;
		
	}
	public static String getNumeric() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return  randomString;
		
	}

}


