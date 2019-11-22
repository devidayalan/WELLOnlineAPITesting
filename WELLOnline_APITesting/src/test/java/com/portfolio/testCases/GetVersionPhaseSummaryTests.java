package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetVersionPhaseSummaryTests extends TestBase {
	
	@Test
	public void getVersionPhaseSummaryByScorecardId() throws IOException {
		

		String scorecardId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "scorecardId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("scorecard_id", scorecardId)
				    
				.when()
						.get("scorecard/versionPhaseSummary/{scorecard_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}

