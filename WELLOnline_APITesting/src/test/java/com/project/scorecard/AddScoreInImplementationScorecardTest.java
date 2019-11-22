package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class AddScoreInImplementationScorecardTest extends TestBase {
	@Test
	@SuppressWarnings("unchecked")
	public void AddScoreInImplemenPhaseScorecardTest() throws IOException {
		
		JSONObject param = new JSONObject();
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		String scorecardid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "scorecardId", 2);
		String partid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 7);
		String subpartid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "subpartsid", 7);
		System.out.println("partid is"+partid);
		System.out.println("subpartid is"+subpartid);
		param.put("response", "yes");
		param.put("points", 1);
		
		param.put("phase", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "phase", 3) );
		param.put("phase_response", "completed");
		param.put("option_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "optionsid", 7));
		param.put("version_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "versionId", 2));
		String jsonString = param.toJSONString();
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
	//@Test(dependsOnMethods = "AddScoreInImplemenPhaseScorecardTest")
	public void testProjectScore() throws IOException {
			projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
			res =
					given()
					.header("Authorization", header)
					.pathParam("project_id", projectId)
				       
					.when()
							.get("project/{project_id}")
					.then()
							.statusCode(STATUS_200)
							.log().body()
							.extract().response();
		
		//String summaryscore =  res.path("scorecards[0].score.score").toString();
		String assessment_score =  res.path("scorecards[0].assessment_score.score").toString();
		String implementation_score =  res.path("scorecards[0].implementation_score.score").toString();
		System.out.println("summaryscore is"+res.path("scorecards[0].score.score").toString());
		//Assert.assertEquals(summaryscore, "2");
		Assert.assertEquals(assessment_score, "1");
		Assert.assertEquals(implementation_score, "1");
	}

}
