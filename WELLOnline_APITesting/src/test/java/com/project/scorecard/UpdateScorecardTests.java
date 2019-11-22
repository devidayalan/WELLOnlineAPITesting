package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class UpdateScorecardTests extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void updateScorecard() throws IOException {
		
		JSONObject param = new JSONObject();
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		String phase = ExcelParserUtils.readRandomCellData(loginUserfile_path, scoreCardSheet, "phase", 2);
		String projectType =  ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "projectType", 2);
		String projectName =  ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "projectName", 2);
		param.put("name", projectName+"-scorecard" );
		param.put("version_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "versionId", 2));
		param.put("project_id", projectId);
		param.put("project_type", projectType);
		param.put("phase", phase);
		String jsonString = param.toJSONString();
		
		System.out.println("final json string"+jsonString);
		String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.body(jsonString)  
				.pathParam("scorecard_id", scorecardId)
				.when()
						.put("scorecard/update/{scorecard_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
