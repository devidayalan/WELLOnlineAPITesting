package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetPortfolioNoteByNoteIdTests extends TestBase {
	
	@Test
	public void getPortfolioNoteByNoteId() throws IOException {
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		String noteId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "noteId", 2);
		res =
				given()
				.header("Authorization",header)
				.pathParam("note_id", noteId)
				.pathParam("portfolio_id", portfolioId)
				    
				.when()
						.get("portfolio/{portfolio_id}/note/{note_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}


}
