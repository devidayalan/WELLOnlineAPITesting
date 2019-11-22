package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdatePortfolioDocumentTests extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void updatePortfolioDocument() throws IOException {
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		String documentId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "documentId", 2);
		
		JSONObject paramData = new JSONObject();
		paramData.put("remark", GenerateRandomTestDataUtils.getTestString());
		paramData.put("link_s3", ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Link", 2));
		String jsonString = paramData.toJSONString();
		 res = given()
			.contentType("application/json")
		   .header("Authorization", header)
		   .body(jsonString)
		   .pathParam("portfolio_id", portfolioId)
		   .pathParam("document_id", documentId)
		 
		.when()
				.put("portfolio/{portfolio_id}/document/{document_id}")
		.then()
				.statusCode(200)
				.log().all()
				.extract().response();
	}

}
