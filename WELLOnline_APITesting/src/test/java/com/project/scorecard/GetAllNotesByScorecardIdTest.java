package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetAllNotesByScorecardIdTest extends TestBase {
	
	@Test
	public void getNotesByScorecardId() throws IOException {
		
		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
			
				res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("scorecard_id", scorecardId)
				
			       
				.when()
						.get("scorecard/{scorecard_id}/note")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
