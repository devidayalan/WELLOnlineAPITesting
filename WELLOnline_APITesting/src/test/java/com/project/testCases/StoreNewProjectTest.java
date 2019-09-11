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
import com.common.utils.GenerateSpaceTypesID;


public class StoreNewProjectTest extends TestBase {
	@SuppressWarnings("unchecked")
	@Test
	public void storeProject() throws IOException {
		JSONObject parametr = new JSONObject();
	//	 Map<String, Object> parametr = new HashMap<>();
		 parametr.put("name",GenerateRandomTestDataUtils.getProjectName());
		 parametr.put("area",GenerateRandomTestDataUtils.getNumeric());
		 parametr.put("project_type",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"type", 3));
		parametr.put("country_id",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "ID",10));
		 parametr.put("city","chennai");
		 parametr.put("state","TamilNadu");
	     parametr.put("postal_code","600001");
		 parametr.put("first_name",GenerateRandomTestDataUtils.getFirstName());
		parametr.put("last_name",GenerateRandomTestDataUtils.getLastName());
		parametr.put("organization",GenerateRandomTestDataUtils.getOrganization());
		parametr.put("primary_space_type_id",GenerateSpaceTypesID.generateSpaceTypes());
		parametr.put("estimate_sector",ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "estimate_sector",4));
		String jsonString = parametr.toJSONString();
		res = given()
				.contentType("application/json")
			   .header("Authorization", header)
			   .body(jsonString)
			   
			.when()
					.post("project/store")
			.then()
					.statusCode(STATUS_200)
					.body("$", hasKey("id")) 
					.body("any { it.key == 'id' }", is(notNullValue())) 
					.log().all()
					.extract().response();
		projectId =(res.path("id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, UsersSheet, 1, 5, projectId);
	}
	
}
