package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetScorecardByPhase extends TestBase {
	
	@Test
	public void getScorecardByPhase() throws IOException {
		
		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		String phase = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 2);
		String points = "1";	
				res =
				given()
				.header("Authorization", header)
				.pathParam("scorecard_id", scorecardId)
				.pathParam("phase",phase)
			       
				.when()
						.get("scorecard/score/{scorecard_id}/{phase}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
				
				
		
	}

}
