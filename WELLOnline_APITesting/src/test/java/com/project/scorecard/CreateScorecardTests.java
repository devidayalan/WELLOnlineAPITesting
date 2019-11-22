package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class CreateScorecardTests extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void createScoreCardTest() throws IOException {
		
		JSONObject param = new JSONObject();
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		String projectType =  ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "projectType", 2);
		String projectName =  ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "projectName", 2);
		param.put("name", projectName+"-scorecard" );
		param.put("version_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "versionId", 2));
		param.put("project_id", projectId);
		param.put("project_type", projectType);
		param.put("create_recommend", true);
		String jsonString = param.toJSONString();
		
		System.out.println("final json string"+jsonString);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.body(jsonString)  
				.when()
						.post("scorecard/store")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		String id = res.path("id").toString();
		ExcelParserUtils.setCellData(loginUserfile_path, scoreCardSheet, 1, 2, id);
	}

}
