package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetPortfolioDocumentByIdTests extends TestBase {
	
	@Test
	public void getPortfolioDocumentById() throws IOException {
		
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		String documentId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "documentId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("document_id", documentId)
				.pathParam("portfolio_id", portfolioId)
				    
				.when()
						.get("portfolio/{portfolio_id}/document/{document_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	
	}

}
