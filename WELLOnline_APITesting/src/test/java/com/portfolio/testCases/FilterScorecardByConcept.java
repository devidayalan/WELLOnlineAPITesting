package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class FilterScorecardByConcept extends TestBase {
	
	@Test
	public void filterScorecardByConcept() throws IOException {
		
		String scorecardId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "scorecardId", 2);
		String conceptId =  ExcelParserUtils.readRandomCellData(loginUserfile_path, portfolioSheet, "conceptId", 3);
		res =
				given()
				.header("Authorization", header)
				.pathParam("scorecard_id", scorecardId)
				.pathParam("concept_id", conceptId)
			       
				.when()
						.post("scorecard/filter/{scorecard_id}/{concept_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
