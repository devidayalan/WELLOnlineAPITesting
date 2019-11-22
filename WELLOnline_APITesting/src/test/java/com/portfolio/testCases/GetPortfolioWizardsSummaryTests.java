package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetPortfolioWizardsSummaryTests extends TestBase {
	
	@Test
	public void getPortfolioWizardsSummary() throws IOException {
		
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
			       
				.when()
						.post("portfolio/{portfolio_id}/getWizardSummary")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
