package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdateReviewAsAdminTests extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void updateReviewAsAdmin() throws IOException {

		JSONObject updateData = new JSONObject();
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		System.out.println(date);  
		updateData.put("comment", GenerateRandomTestDataUtils.getTestString()) ;
		updateData.put("status", "mid-review") ;
	
		updateData.put("review_comment", GenerateRandomTestDataUtils.getTestString()) ;
		updateData.put("payment_status",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"payment", 2)) ;
		updateData.put("phase",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"Phase", 5)) ;
		updateData.put("s3_link", "www.google.com") ;
		updateData.put("est_date", "2019-10-14") ;
		
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
						.put("admin/project-review/{project_id}/{review_id}/update")
				.then()
						
						.log().body()
						.statusCode(200)
						.extract().response();
		
		
	}

}
