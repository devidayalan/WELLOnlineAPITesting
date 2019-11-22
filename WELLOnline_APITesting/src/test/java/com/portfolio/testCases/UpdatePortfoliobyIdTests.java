package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdatePortfoliobyIdTests extends TestBase {
	@SuppressWarnings("unchecked")
	@Test
	public void updatePortfolioById() throws IOException {
		JSONObject updateData = new JSONObject();
		updateData.put("name",GenerateRandomTestDataUtils.getPortfolioName()) ;
		updateData.put("area", GenerateRandomTestDataUtils.getNumeric());
		updateData.put("no_of_assets",  Integer.parseInt(GenerateRandomTestDataUtils.getNumeric()));
		updateData.put("owner_street", GenerateRandomTestDataUtils.getTestString());
		updateData.put("owner_city", GenerateRandomTestDataUtils.getTestString());
		updateData.put("owner_country_code",ExcelParserUtils. readRandomCellData(estimatefile_path, estimateParams, "Country",75));
		updateData.put("owner_postal_code",GenerateRandomTestDataUtils.getPostalcode());
		updateData.put("portfolio_public", true);
		String jsonString = updateData.toJSONString();
		System.out.println("jsonString is"+jsonString);
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		
		res = given()
				.contentType("application/json")
			   .header("Authorization", header)
			   .pathParam("portfolio_id", portfolioId)
			   .body(jsonString)
			   
			.when()
					.put("portfolio/update/{portfolio_id}")
			.then()
					.statusCode(STATUS_200)
					.log().all()
					.extract().response();
		
	}

}
