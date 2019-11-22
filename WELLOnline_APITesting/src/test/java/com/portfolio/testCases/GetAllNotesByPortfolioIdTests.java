package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetAllNotesByPortfolioIdTests extends TestBase {
	
	@Test
	public void getAllNotesByPortfolioId() throws IOException {
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("portfolio_id", portfolioId)
				    
				.when()
						.get("portfolio/{portfolio_id}/notes")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
