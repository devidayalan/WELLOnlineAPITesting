package com.wellv1.project;

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

public class CreateV1ProjectTest extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void createV1Project() throws IOException {
		
		JSONObject parametr = new JSONObject();
		String project_std = ExcelParserUtils.readRandomCellData(loginUserfile_path, v1_projectSheet,"project_standard", 4);
		parametr.put("name",GenerateRandomTestDataUtils.getProjectName());
		parametr.put("v1_project_standard",project_std);
		if(project_std.equalsIgnoreCase("office")) {
			parametr.put("v1_project_type",ExcelParserUtils.readRandomCellData(loginUserfile_path, v1_projectSheet,"project_type", 3));
		}
		 parametr.put("project_type",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"type", 3));
		 parametr.put("country_id",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "ID",10));
			
		 parametr.put("area",GenerateRandomTestDataUtils.getNumeric());
		 parametr.put("street",GenerateRandomTestDataUtils.getTestString()+"street");
		 parametr.put("city",GenerateRandomTestDataUtils.getTestString()+"city");
		 
		parametr.put("country_code",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "code",10));
		parametr.put("owner_org",GenerateRandomTestDataUtils.getOrganization());
	     parametr.put("owner_name",GenerateRandomTestDataUtils.getFirstName());
		parametr.put("owner_email",ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Email", 3));
		
		parametr.put("owner_phone",GenerateRandomTestDataUtils.getPhone());
		parametr.put("applicant_role",ExcelParserUtils. readRandomCellData(loginUserfile_path, v1_projectSheet, "applicant_role",8));
		parametr.put("current_status",ExcelParserUtils. readRandomCellData(loginUserfile_path, v1_projectSheet, "current_status",6));
		parametr.put("project_public",1);
		String jsonString = parametr.toJSONString();
		System.out.println("jsonString is"+jsonString);
		res = given()
				.contentType("application/json")
			   .header("Authorization", header)
			   .body(jsonString)
			   
			.when()
					.post("well-v1/project/store")
			.then()
					.statusCode(STATUS_200)
					.body("$", hasKey("id")) 
					.body("any { it.key == 'id' }", is(notNullValue())) 
					.log().all()
					.extract().response();
		v1projectId =(res.path("id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, v1_projectSheet, 1, 4, v1projectId);
		
	}

}
