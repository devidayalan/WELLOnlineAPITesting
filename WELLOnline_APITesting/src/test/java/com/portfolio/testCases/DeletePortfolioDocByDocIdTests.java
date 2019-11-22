package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class DeletePortfolioDocByDocIdTests extends TestBase {
	
	@Test
	public void deletePortfolioDocByDocId() throws IOException {
		
		String documentId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "documentId", 2);
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
				.pathParam("document_id", documentId)
				
			       
				.when()
						.delete("portfolio/{portfolio_id}/document/{document_id}")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
	}

}
