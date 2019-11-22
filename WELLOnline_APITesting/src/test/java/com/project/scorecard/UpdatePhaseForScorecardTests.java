package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class UpdatePhaseForScorecardTests extends TestBase {
	
	@Test
	public void updatePhaseForScorecard() throws IOException {
		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		String phase = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 3);
		
		
		res =
				given()
				.header("Authorization", header)
				.pathParam("scorecard_id", scorecardId)
				.param("project_phase",phase)
			       
				.when()
						.post("scorecard/phaseUpdate/{scorecard_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		
	}

}
