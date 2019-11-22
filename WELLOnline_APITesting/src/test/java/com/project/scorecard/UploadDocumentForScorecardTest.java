package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class UploadDocumentForScorecardTest extends TestBase {
	
	@Test
	public void uploadDocumentForScorecard() throws  IOException {
		
		Integer partid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 3));
		Integer featureid =Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "featureid",3));
		
		String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		res =
				given()
				.header("Authorization", header)
				.param("part_ids[0]", partid) 
				.param("feature_id", featureid)
				.param("phase", "assessment") 
				.multiPart("file", new File(loginUserfile_path))
				.pathParam("scorecard_id", scorecardid)
				.when()
						.post("scorecard/{scorecard_id}/document-upload")
				.then()
					//	.statusCode(200)
						.log().body()
						.extract().response();
		String documentid = res.path("id").toString();
		ExcelParserUtils.setCellData(loginUserfile_path, scoreCardSheet, 1, 13, documentid);
		
	}

}
