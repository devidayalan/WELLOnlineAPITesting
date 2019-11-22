package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class DownloadAllDocumentScorecardTest extends TestBase {
	//not required
	@Test
	public void downloadAllDocumentForScorecard() throws IOException {
		
		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("scorecard_id", scorecardId)
				.when()
						.post("documentAllDownload/{scorecard_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
