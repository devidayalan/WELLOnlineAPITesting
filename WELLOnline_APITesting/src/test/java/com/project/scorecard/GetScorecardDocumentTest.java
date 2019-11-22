package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetScorecardDocumentTest extends TestBase {
	
	@Test
	public void getScorecardUploadedDocument() throws IOException {
		
		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		
		res =
		given()
		.header("Authorization", header)
		.pathParam("scorecard_id", scorecardId)
		
	       
		.when()
				.get("scorecard/{scorecard_id}/document")
		.then()
				.statusCode(STATUS_200)
				.log().body()
				.extract().response();
		
	}

}
