package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class AddDocumentToPortfolioTests extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void addDocumentToPortfolio() throws IOException {
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		JSONObject paramData = new JSONObject();
		paramData.put("remark", GenerateRandomTestDataUtils.getTestString());
		paramData.put("link_s3", ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Link", 2));
		String jsonString = paramData.toJSONString();
		 res = given()
			.contentType("application/json")
		   .header("Authorization", header)
		   .body(jsonString)
		   .pathParam("portfolio_id", portfolioId)
		 
		.when()
				.post("portfolio/{portfolio_id}/document")
		.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		 
		 String documentId =(res.path("id")).toString();
			ExcelParserUtils.setCellData(loginUserfile_path, portfolioSheet, 1, 8, documentId);
	}

}
