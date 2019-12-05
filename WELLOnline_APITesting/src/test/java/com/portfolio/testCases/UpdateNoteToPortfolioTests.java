package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;


public class UpdateNoteToPortfolioTests extends TestBase {
	@Test
	@SuppressWarnings("unchecked")
	public void updateNoteToPortfolio() throws IOException {
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		String noteId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "noteId", 2);
		
		JSONObject projObj = new JSONObject();
		JSONArray projArr = new JSONArray();
		JSONObject paramData = new JSONObject();
		paramData.put("communication_type",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "communication_type", 4)) ;
		projObj.put("remark", GenerateRandomTestDataUtils.getTestString());
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
		   .pathParam("note_id", noteId)
		 
		.when()
				.put("portfolio/{portfolio_id}/note/{note_id}")
		.then()
				.statusCode(200)
				.log().all()
				.extract().response();
	}

}
