package com.project.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdateProjectNoteTest extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateProjectNoteAsAdmin() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		String note_id = ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "note_id", 2);
		JSONObject paramData = new JSONObject();
		paramData.put("communication_type",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "communication_type", 4)) ;
		paramData.put("project_id",projectId);
		paramData.put("main_topic", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "main_topic", 11));
		paramData.put("agenda", GenerateRandomTestDataUtils.getTestString());
		paramData.put("is_internal", ExcelParserUtils. readRandomCellData(loginUserfile_path, emailListSheet, "is_internal", 2));
		String jsonString = paramData.toJSONString();
		 System.out.println("jsonsring is"+jsonString);
		 res = given()
			.contentType("application/json")
		   .header("Authorization", admin_Header)
		   .pathParam("id", note_id)
		   .body(jsonString)
		 
		.when()
				.put("admin/project-note/{id}")
		.then()
				.statusCode(STATUS_200)
				.log().all()
				.body("any { it.key == 'id' }", is(notNullValue())) 
				.body("id", Matchers.equalTo(Integer.parseInt(note_id))) 
				.extract().response();
	
		
	}


}
