package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetPortfolioByIdTests extends TestBase {
	
	@Test
	public void getPortfolioById() throws IOException {
		
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
			       
				.when()
						.get("portfolio/{portfolio_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		String scoreCardId =(res.path("scorecard_id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, portfolioSheet, 1, 11, scoreCardId);
		
	}

}
