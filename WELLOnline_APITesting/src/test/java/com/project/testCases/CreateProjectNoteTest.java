package com.project.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class CreateProjectNoteTest extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void createProjectNoteAsAuthenticatedUser() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		JSONObject paramData = new JSONObject();
		paramData.put("communication_type",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "communication_type", 4)) ;
		paramData.put("main_topic", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "main_topic", 11));
		paramData.put("agenda", GenerateRandomTestDataUtils.getTestString());
		paramData.put("attendees", GenerateRandomTestDataUtils.getLastName());
		paramData.put("description", GenerateRandomTestDataUtils.getTestString());
		paramData.put("project_id",projectId);
		paramData.put("is_internal", ExcelParserUtils. readRandomCellData(loginUserfile_path, emailListSheet, "is_internal", 2));
		String jsonString = paramData.toJSONString();
		 System.out.println("jsonsring is"+jsonString);
		 res = given()
			.contentType("application/json")
		   .header("Authorization", header)
		   .body(jsonString)
		 
		.when()
				.post("admin/project-note")
		.then()
				.statusCode(403)
				.extract().response();
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void createProjectNoteAsAdmin() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		JSONObject paramData = new JSONObject();
		paramData.put("communication_type",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "communication_type", 4)) ;
		paramData.put("main_topic", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "main_topic", 11));
		paramData.put("agenda", GenerateRandomTestDataUtils.getTestString());
		paramData.put("attendees", GenerateRandomTestDataUtils.getLastName());
		paramData.put("description", GenerateRandomTestDataUtils.getTestString());
		paramData.put("project_id",projectId);
		paramData.put("is_internal", ExcelParserUtils. readRandomCellData(loginUserfile_path, emailListSheet, "is_internal", 2));
		String jsonString = paramData.toJSONString();
		 System.out.println("jsonsring is"+jsonString);
		 res = given()
			.contentType("application/json")
		   .header("Authorization", admin_Header)
		   .body(jsonString)
		 
		.when()
				.post("admin/project-note")
		.then()
				.statusCode(STATUS_200)
				.log().all()
				.body("any { it.key == 'id' }", is(notNullValue())) 
				.extract().response();
	String noteId =(res.path("id")).toString();
	ExcelParserUtils.setCellData(loginUserfile_path, emailListSheet, 1,5, noteId);
		
	}

}
