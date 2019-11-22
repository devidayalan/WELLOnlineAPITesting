package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;


public class GetPortfolioSummaryTests extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void getPortfolioSummary() throws IOException {

		JSONObject updateData = new JSONObject();
		JSONArray projArr = new JSONArray();
		projArr.add(0,"city");
		projArr.add(1,"project_type");
		projArr.add(2,"country_code");
		projArr.add(3,"area");
		projArr.add(4,"industry");
		projArr.add(5,"construction_status");
		projArr.add(6,"construction_type");
		projArr.add(7,"owner_name");
		
		updateData.put("field_array", projArr);
		String jsonString = updateData.toJSONString();
		System.out.println("jsonString is"+jsonString);
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
			    .body(jsonString)
				.when()
						.post("portfolio/{portfolio_id}/portfolioSummary")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();

	}


}
