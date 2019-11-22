package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdateNoteForReviewTest extends TestBase {
	
	@Test	
	@SuppressWarnings("unchecked")
	public void updateNoteForReview() throws IOException {
		JSONObject updateData = new JSONObject();
		updateData.put("note",GenerateRandomTestDataUtils.getTestString()) ;
		updateData.put("link_s3", "www.google.com") ;
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		reviewId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "reviewId", 2);
		String noteId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "noteId", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.pathParam("review_id", reviewId)
				.pathParam("note_id", noteId)
				.body(jsonString)
			       
				.when()
						.put("project-review/{project_id}/{review_id}/note/{note_id}")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
		
		
	}

}
