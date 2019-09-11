package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;

public class GetUserProjectsTest extends TestBase {
	
	@Test
	public void getUserProjects() throws IOException {
	res =
				given()
				.header("Authorization", header)
			       
				.when()
						.get("/user/projects")
				.then()
						.statusCode(STATUS_200)
						//.log().body()
						.extract().response();
	}
}

