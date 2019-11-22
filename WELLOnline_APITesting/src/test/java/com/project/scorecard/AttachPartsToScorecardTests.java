package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class AttachPartsToScorecardTests extends TestBase {
	String scorecardid;
	String partid;
	String subpartid;
	//@Test
	@SuppressWarnings("unchecked")
	
	public void attachPartsToScorecardInAssessPhase() throws IOException {
		
		JSONObject param = new JSONObject();
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		partid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 2);
		subpartid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "subpartsid", 2);
		
		param.put("response", "yes");
		param.put("phase", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 2) );
		param.put("phase_response", ExcelParserUtils.readRandomCellData(loginUserfile_path, scoreCardSheet, "phase_response", 3) );
		param.put("option_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "optionsid", 2));
		param.put("version_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "versionId", 2));
		String jsonString = param.toJSONString();
		System.out.println("final json string"+jsonString);
		callAPI(jsonString);
		
	}
	@Test
	@SuppressWarnings("unchecked")
	public void attachOptiPartsToScorecardInAssessPhase() throws IOException {
		
		JSONObject param = new JSONObject();
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		partid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 4);
		subpartid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "subpartsid", 4);
		param.put("response", "yes");
		param.put("points", 1);
		param.put("phase", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 2) );
		param.put("phase_response", ExcelParserUtils.readRandomCellData(loginUserfile_path, scoreCardSheet, "phase_response", 3) );
		param.put("option_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "optionsid", 4));
		param.put("version_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "versionId", 2));
		String jsonString = param.toJSONString();
		System.out.println("jsonString"+jsonString);
		callAPI(jsonString);
		
	}
	
		public void callAPI (String jsonString) {
			res =
					given()
					.contentType("application/json")
					.header("Authorization", header)
					.body(jsonString) 
					.pathParam("scorecard_id", scorecardid)
					.pathParam("part_id", partid)
					.pathParam("sub_part_id", subpartid)
					.when()
							.post("scorecard/attach/{scorecard_id}/{part_id}/{sub_part_id}")
					.then()
							.statusCode(201)
							.log().body()
							.extract().response();
			
		}
		
		//@Test(dependsOnMethods = "attachOptiPartsToScorecardInAssessPhase")
		public void getScorecardByPhase() throws IOException {
			
			String scorecardId = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
			String phase = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 2);
			String points = "1";	
					res =
					given()
					.header("Authorization", header)
					.pathParam("scorecard_id", scorecardId)
					.pathParam("phase",phase)
				       
					.when()
							.get("scorecard/score/{scorecard_id}/{phase}")
					.then()
							.statusCode(STATUS_200)
							.log().body()
							.extract().response();
					
					String score = res.path("assessment_score.score").toString();
					Assert.assertEquals(score, points);
					
					
			
		}
}
