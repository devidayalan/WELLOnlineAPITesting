package com.project.estimates;

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
import com.common.utils.GenerateSpaceTypesID;

public class CreateEstimateTest extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void createEstimate() throws IOException {
		
		JSONObject updateData = new JSONObject();
		JSONArray projArr = new JSONArray();
		username = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email", 2);
		
		//create json payload object
		updateData.put("email_address",username) ;
		updateData.put("first_name", GenerateRandomTestDataUtils.getFirstName());
		updateData.put("last_name", GenerateRandomTestDataUtils.getLastName());
		updateData.put("country", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "code",10));
		updateData.put("organization",  GenerateRandomTestDataUtils.getOrganization());
		
		JSONObject projObj = new JSONObject();
		projObj.put("name",  GenerateRandomTestDataUtils.getProjectName());
		projObj.put("area", GenerateRandomTestDataUtils.getNumeric());
		projObj.put("type", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet,"type", 3));
		
		JSONArray spaceTyp = new JSONArray();
		spaceTyp.add(0,GenerateSpaceTypesID.generateSpaceTypes());
		spaceTyp.add(1,GenerateSpaceTypesID.generateSpaceTypes());
		
		projObj.put("space_types", spaceTyp);
		projObj.put("estimate_sector", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "estimate_sector",4));
		
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
						.post("estimate/create")
				.then()
						
						.log().body()
						.statusCode(STATUS_200)
						.body("any { it.key == 'id' }", is(notNullValue())) 
						.extract().response();
		estimateId =(res.path("id")).toString();
		
		ExcelParserUtils.setCellData(loginUserfile_path, estimateSheet, 1, 5, estimateId);
		
	}

}
