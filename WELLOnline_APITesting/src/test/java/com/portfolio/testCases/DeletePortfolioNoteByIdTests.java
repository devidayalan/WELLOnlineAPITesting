package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class DeletePortfolioNoteByIdTests extends TestBase {
	
	@Test
	public void deletePortfolioNoteById() throws IOException {
		String noteId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "noteId", 2);
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
				.pathParam("note_id", noteId)
				
			       
				.when()
						.delete("portfolio/{portfolio_id}/note/{note_id}")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
		
		
	}

}
