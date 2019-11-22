package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetNoteByPartIdTest extends TestBase {
	
	@Test
	public void getScorecardNoteByPartId() throws IOException {
		
		String partid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 3);
		String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		
		res =
		given()
		.header("Authorization", header)
		.pathParam("scorecard_id", scorecardid)
		.pathParam("part_id", partid)
		
	       
		.when()
				.get("scorecard/{scorecard_id}/note/{part_id}")
		.then()
				.statusCode(STATUS_200)
				.log().body()
				.extract().response();
	}

}
