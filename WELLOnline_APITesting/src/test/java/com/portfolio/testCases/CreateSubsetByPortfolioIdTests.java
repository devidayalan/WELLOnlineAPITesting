package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class CreateSubsetByPortfolioIdTests extends TestBase {
	@Test
	@SuppressWarnings("unchecked")
	public void createSubsetByPortfolioId() throws IOException {
		JSONObject updateData = new JSONObject();
		updateData.put("name",GenerateRandomTestDataUtils.getSubsetName()) ;
		
			JSONArray projIds = new JSONArray();
			for(int i=0;i<4;i++) {
				int j = i+2;
				String id =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "ProjIds", j);
				projIds.add(id);
			}
			
		updateData.put("project_ids", projIds);
		String jsonString = updateData.toJSONString();
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
			    .body(jsonString)
			       
				.when()
						.post("portfolio/{portfolio_id}/subset")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		String subsetId =(res.path("id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, portfolioSheet, 1, 14, subsetId);
		
		
	}

}
