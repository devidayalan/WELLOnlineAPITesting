package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class AddNoteToReviewTest extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void addNoteToReview() throws IOException {
		
		JSONObject updateData = new JSONObject();
		updateData.put("note",GenerateRandomTestDataUtils.getTestString()) ;
		updateData.put("link_s3", "www.google.com") ;
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		reviewId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "reviewId", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.pathParam("review_id", reviewId)
				.body(jsonString)
			       
				.when()
						.post("project-review/{project_id}/{review_id}/note")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
		
		String noteId =(res.path("id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, UsersSheet, 1, 8, noteId);
	}

}
