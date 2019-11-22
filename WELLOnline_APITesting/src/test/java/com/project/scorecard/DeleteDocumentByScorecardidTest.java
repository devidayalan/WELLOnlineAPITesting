package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class DeleteDocumentByScorecardidTest extends TestBase{
	
	@Test
	public void deleteDocumentByScorecardId() throws IOException {
		
		String documentid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "documentid", 2);
		String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		
		res =
				given()
				.header("Authorization", header)
				.pathParam("scorecard_id", scorecardid)
				.pathParam("document_id", documentid)
			       
				.when()
						.delete("scorecard/{scorecard_id}/document/{document_id}")

				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
