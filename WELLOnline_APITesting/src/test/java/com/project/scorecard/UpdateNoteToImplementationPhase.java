package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdateNoteToImplementationPhase extends TestBase{
	
	@SuppressWarnings("unchecked")
	public void updateNoteToImplementaitonPhase() throws NumberFormatException, IOException {
	
	Integer partid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 4));
	Integer featureid =Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "featureid",4));
	String noteid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "noteid", 2);
	String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
	JSONObject param = new JSONObject();
	param.put("part_id", partid);
	param.put("feature_id", featureid);
	param.put("note", GenerateRandomTestDataUtils.getTestString() );
	param.put("phase", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 3) );
	param.put("private", 1);
	String jsonString = param.toJSONString();
	res =
			given()
			.contentType("application/json")
			.header("Authorization", header)
			.body(jsonString) 
			.pathParam("scorecard_id", scorecardid)
			.pathParam("note_id", noteid)
			.when()
					.put("scorecard/{scorecard_id}/note/{note_id}")
			.then()
					.statusCode(200)
					.log().body()
					.extract().response();
	
	
}


}
