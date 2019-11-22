package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class SubmitReviewTests extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void submitReview() throws IOException {
		
		JSONObject updateData = new JSONObject();
		updateData.put("phase",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"Phase", 5)) ;
		updateData.put("comment", GenerateRandomTestDataUtils.getTestString()) ;
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.body(jsonString)
			       
				.when()
						.post("project-review/{project_id}")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
		
		reviewId =(res.path("id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, UsersSheet, 1, 7, reviewId);
		
	}

}
