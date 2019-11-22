package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.common.base.TestBase;

public class ListUserPortfolioTests extends TestBase {
	@Test
	public void listUserPortfolios() {
		
		res =
				given()
				.header("Authorization", header)
				    
				.when()
						.get("user/portfolios")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

	}
