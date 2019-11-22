package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetNoteByFeatureIdTest extends TestBase {
	
	//@Test
	//Not working
	public void getScorecardNoteByFeatureId() throws IOException {
		

		String featureid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "featureid", 3);
		String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		
		res =
		given()
		.header("Authorization", header)
		.pathParam("scorecard_id", scorecardid)
		.pathParam("feature_id", featureid)
		
	       
		.when()
				.get("scorecard/{scorecard_id}/{feature_id}/note")
		.then()
				//.statusCode(STATUS_200)
				.log().body()
				.extract().response();
	}

}
