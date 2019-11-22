package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class DeleteNoteByNoteidTest extends TestBase {
	
	@Test
	public void DeleteScorecardNoteById() throws IOException {
		
		String noteid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "noteid", 2);
		String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		
		res =
				given()
				.header("Authorization", header)
				.pathParam("scorecard_id", scorecardid)
				.pathParam("note_id", noteid)
			       
				.when()
						.delete("scorecard/{scorecard_id}/note/{note_id}")

				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
