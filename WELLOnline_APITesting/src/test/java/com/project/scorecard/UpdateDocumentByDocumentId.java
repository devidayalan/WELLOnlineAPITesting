package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class UpdateDocumentByDocumentId extends TestBase {
//Dont need this anymore
	//@Test
	public void updateScorecardDocumentById() throws IOException {
		
		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		Integer partid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 3));
		String documentid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "documentid", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.param("part_ids[0]", partid)
				.param("d_and_o", 1)
				.pathParam("scorecard_id", scorecardId)
				.pathParam("document_id", documentid)
				.when()
						.put("scorecard/{scorecard_id}/document/{document_id}")
				.then()
						//.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}
}
