package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetScorecardDocumentByPartidTest extends TestBase {
	
	@Test
	public void getScorecardDocumentByPartid() throws IOException {
		

		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		Integer partid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 3));
		
		res =
		given()
		.header("Authorization", header)
		.pathParam("scorecard_id", scorecardId)
		.pathParam("part_id", partid)
		
	       
		.when()
				.get("scorecard/{scorecard_id}/document/{part_id}")
		.then()
				.statusCode(STATUS_200)
				.log().body()
				.extract().response();
	}

}
