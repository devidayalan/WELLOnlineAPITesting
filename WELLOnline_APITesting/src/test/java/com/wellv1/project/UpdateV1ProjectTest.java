package com.wellv1.project;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdateV1ProjectTest extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateV1Project() throws IOException {

		JSONObject parametr = new JSONObject();
		String project_std = ExcelParserUtils.readRandomCellData(loginUserfile_path, v1_projectSheet,"project_standard", 4);
		parametr.put("name",GenerateRandomTestDataUtils.getProjectName());
	
		 parametr.put("area",GenerateRandomTestDataUtils.getNumeric());
		 parametr.put("street",GenerateRandomTestDataUtils.getTestString()+"street");
		 parametr.put("city",GenerateRandomTestDataUtils.getTestString()+"city");
		 parametr.put("project_type",ExcelParserUtils.readRandomCellData(loginUserfile_path, v1_projectSheet,"project_type", 3));
		 parametr.put("country_id",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "ID",10));parametr.put("owner_org",GenerateRandomTestDataUtils.getOrganization());
	     parametr.put("owner_name",GenerateRandomTestDataUtils.getFirstName());
	     parametr.put("v1_project_standard",project_std);
	     if(project_std.equalsIgnoreCase("office")) {
				parametr.put("v1_project_type",ExcelParserUtils.readRandomCellData(loginUserfile_path, v1_projectSheet,"project_type", 3));
			}
	     parametr.put("country_code",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "code",10));
			 parametr.put("owner_email",ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Email", 3));
			    
		parametr.put("owner_phone",GenerateRandomTestDataUtils.getPhone());
		parametr.put("applicant_role",ExcelParserUtils. readRandomCellData(loginUserfile_path, v1_projectSheet, "applicant_role",8));
		parametr.put("current_status",ExcelParserUtils. readRandomCellData(loginUserfile_path, v1_projectSheet, "current_status",6));
		String jsonString = parametr.toJSONString();
		System.out.println("jsonString is"+jsonString);
		v1projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, v1_projectSheet, "projectId", 2);
		
		res = given()
				.contentType("application/json")
			   .header("Authorization", header)
			   .pathParam("project_id", v1projectId)
			   .body(jsonString)
			   
			.when()
					.put("well-v1/project/{project_id}")
			.then()
					.statusCode(STATUS_200)
					.log().all()
					.body("id", Matchers.equalTo(Integer.parseInt(v1projectId))) 
					.extract().response();
		
		
	}

}
