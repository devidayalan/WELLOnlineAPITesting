package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class ReturnReviewAsAdminTest extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void returnReviewAsAdmin() throws IOException {
		
		JSONObject updateData = new JSONObject();
		updateData.put("review_comment", GenerateRandomTestDataUtils.getTestString()) ;
		updateData.put("payment_status",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"payment", 2)) ;
		updateData.put("phase",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"Phase", 5)) ;
		
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		reviewId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "reviewId", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", admin_Header)
				.pathParam("project_id", projectId)
				.pathParam("review_id", reviewId)
				.body(jsonString)
			       
				.when()
						.post("admin/project-review/{project_id}/{review_id}/return")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
		
	}

}
