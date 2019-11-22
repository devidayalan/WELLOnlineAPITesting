package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class AddNoteToPortfolioTests extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void addNoteToPortfolio() throws IOException {
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		JSONObject projObj = new JSONObject();
		JSONArray projArr = new JSONArray();
		JSONObject paramData = new JSONObject();
		paramData.put("communication_type",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "communication_type", 4)) ;
		paramData.put("main_topic", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "main_topic", 11));
		paramData.put("agenda", GenerateRandomTestDataUtils.getTestString());
		paramData.put("attendees", GenerateRandomTestDataUtils.getLastName());
		paramData.put("description", GenerateRandomTestDataUtils.getTestString());
		paramData.put("is_internal", ExcelParserUtils. readRandomCellData(loginUserfile_path, emailListSheet, "is_internal", 2));
		projObj.put("link_s3", ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Link", 2));
		projArr.add(projObj);
		paramData.put("documents", projArr);
		
		String jsonString = paramData.toJSONString();
		 System.out.println("jsonsring is"+jsonString);
		 res = given()
			.contentType("application/json")
		   .header("Authorization", header)
		   .body(jsonString)
		   .pathParam("portfolio_id", portfolioId)
		 
		.when()
				.post("portfolio/{portfolio_id}/note")
		.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		 
		 String noteId =(res.path("id")).toString();
			ExcelParserUtils.setCellData(loginUserfile_path, portfolioSheet, 1, 7, noteId);
		
	}

}
