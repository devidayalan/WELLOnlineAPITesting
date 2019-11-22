package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class UpdateSurveyStatusTests extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateSurveyStatusToStarted() throws IOException {
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		JSONObject paramData = new JSONObject();
		paramData.put("survey_started", true);
		paramData.put("survey_completed", false);
		String jsonString = paramData.toJSONString();
		 res = given()
					.contentType("application/json")
				   .header("Authorization", header)
				   .pathParam("portfolio_id", portfolioId)
				   .body(jsonString)
				 
				.when()
						.put("portfolio/survey/{portfolio_id}/statusUpdate")
				.then()
						.statusCode(200)
						.log().all()
						.extract().response();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateSurveyStatusToCompleted() throws IOException {
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		JSONObject paramData = new JSONObject();
		paramData.put("survey_started", false);
		paramData.put("survey_completed", true);
		String jsonString = paramData.toJSONString();
		 res = given()
					.contentType("application/json")
				   .header("Authorization", header)
				   .pathParam("portfolio_id", portfolioId)
				   .body(jsonString)
				 
				.when()
						.put("portfolio/survey/{portfolio_id}/statusUpdate")
				.then()
						.statusCode(200)
						.log().all()
						.extract().response();
	}


}
