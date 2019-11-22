package com.wellv1.project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class CreateV1EstimateTest extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void createV1Estimate() throws IOException {
		JSONObject updateData = new JSONObject();
		JSONArray projArr = new JSONArray();
		username = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email", 2);
		
		//create json payload object
		updateData.put("email",username) ;
		updateData.put("name", GenerateRandomTestDataUtils.getFirstName());
		updateData.put("organization",  GenerateRandomTestDataUtils.getOrganization());
		updateData.put("is_community",  false);
		updateData.put("country", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "code",10));
		
		
		JSONObject projObj = new JSONObject();
		projObj.put("name",  GenerateRandomTestDataUtils.getProjectName());
		projObj.put("area", GenerateRandomTestDataUtils.getNumeric());
		projObj.put("type", ExcelParserUtils. readRandomCellData(loginUserfile_path, v1_projectSheet,"project_type", 4));
	
		//Add created domain objects to json array
		projArr.add(projObj);
		
		//Add array to json payload object
		updateData.put("projects", projArr);
		String jsonString = updateData.toJSONString();
		
		System.out.println("final json string"+jsonString);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.body(jsonString)
			       
				.when()
						.post("well-v1/estimate")
				.then()
						
						.log().body()
						.statusCode(STATUS_200)
						.body("any { it.key == 'id' }", is(notNullValue())) 
						.extract().response();
		v1EstimateId =(res.path("id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, v1_projectSheet, 1, 5, v1EstimateId);
		
		
	}

}
