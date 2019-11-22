package com.project.testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class AssertInvitedProjectMemberPrivileges  extends TestBase {
	

	@Test 
	public void writeTokenForInvitedMember() throws IOException{
		username = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email",4);
		password = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "password", 4);
		res = given()
			       .param("email", username)
			       .param("password", password) 
				.when()
						.post("authenticate")
				.then()
						.body("$", hasKey(TOKEN)) 
						.body("any { it.key == 'token' }", is(notNullValue())).        //authorization_token value is not null - has a value
						extract().response();
		
		String headerValue =(res.path("token")).toString();
		headerValue = "Bearer "+headerValue;
		ExcelParserUtils.setCellData(loginUserfile_path, UsersSheet, 4, 3, headerValue);
				
	  
	}
	
	@SuppressWarnings("unchecked")
	@Test// (dependsOnMethods = {"writeTokenForInvitedMember"})
	public void createNoteAsProjectMember() throws IOException {
		
			projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
			String headerValue = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "Token", 5);
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
			   .header("Authorization", headerValue)
			   .body(jsonString)
			 
			.when()
					.post("admin/project-note")
			.then()
					//.statusCode(200)
					.extract().response();
			
		}


}
