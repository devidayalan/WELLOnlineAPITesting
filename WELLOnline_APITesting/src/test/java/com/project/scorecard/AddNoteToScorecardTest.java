package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class AddNoteToScorecardTest extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void addNoteToScorecard() throws IOException {
		
		Integer partid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 3));
		Integer featureid =Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "featureid",3));
		
		String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		JSONObject param = new JSONObject();
		param.put("part_id", partid);
		param.put("feature_id", featureid);
		param.put("note", GenerateRandomTestDataUtils.getTestString() );
		param.put("phase", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 2) );
		param.put("private", 0);
		String jsonString = param.toJSONString();
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.body(jsonString) 
				.pathParam("scorecard_id", scorecardid)
				.when()
						.post("scorecard/{scorecard_id}/note")
				.then()
						.statusCode(200)
						.log().body()
						.extract().response();
		String noteid = res.path("id").toString();
		ExcelParserUtils.setCellData(loginUserfile_path, scoreCardSheet, 1, 12, noteid);
		
	}

}
