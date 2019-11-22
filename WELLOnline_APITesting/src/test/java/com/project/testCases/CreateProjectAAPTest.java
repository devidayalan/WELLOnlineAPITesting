package com.project.testCases;

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

public class CreateProjectAAPTest extends TestBase {
	
	@Test
	@SuppressWarnings("unchecked")
	public void createProjectAAP() throws IOException {
		
		JSONObject projObj = new JSONObject();
		JSONArray document = new JSONArray();
		JSONArray partArray = new JSONArray();
		JSONObject linkObj = new JSONObject();
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		linkObj.put("link_s3", ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "Link", 2));
		document.add(linkObj);
		projObj.put("document", document);
		projObj.put("feature_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "featureid", 2));
		partArray.add(ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "partid", 2));
		partArray.add(ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "partid", 3));
		projObj.put("part_id", partArray);
		projObj.put("project_id", projectId);
		projObj.put("proposed", "<p>"+GenerateRandomTestDataUtils.getTestString()+"</p>");
		projObj.put("reason", "<p>"+GenerateRandomTestDataUtils.getTestString()+"</p>");
		projObj.put("sub_type", 0);
		projObj.put("type",  "aap");
		String jsonString = projObj.toJSONString();
		 System.out.println("jsonsring is"+jsonString);
		 res = given()
					.contentType("application/json")
				   .header("Authorization", header)
				   .body(jsonString)
				 
				.when()
						.post("aap/create")
				.then()
						.statusCode(STATUS_200)
						.body("any { it.key == 'id' }", is(notNullValue())) 
						.log().all()
						.extract().response();
		 
		 String aapid = (res.path("id")).toString();
		 ExcelParserUtils.setCellData(loginUserfile_path, emailListSheet, 1, 8, aapid);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void createProjectEP() throws IOException {
		
		JSONObject projObj = new JSONObject();
		JSONArray document = new JSONArray();
		JSONArray partArray = new JSONArray();
		JSONObject linkObj = new JSONObject();
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		linkObj.put("link_s3", ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "Link", 2));
		document.add(linkObj);
		projObj.put("document", document);
		projObj.put("feature_id", ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "featureid", 2));
		partArray.add(ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "partid", 2));
		partArray.add(ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "partid", 3));
		projObj.put("part_id", partArray);
		projObj.put("project_id", projectId);
		projObj.put("proposed", "<p>"+GenerateRandomTestDataUtils.getTestString()+"</p>");
		projObj.put("reason", "<p>"+GenerateRandomTestDataUtils.getTestString()+"</p>");
		projObj.put("sub_type", 0);
		projObj.put("type",  "ep");
		projObj.put("region",  "Asia");
		projObj.put("verification_method",  GenerateRandomTestDataUtils.getTestString());
		projObj.put("similar_feature",  GenerateRandomTestDataUtils.getTestString());
			
		String jsonString = projObj.toJSONString();
		 System.out.println("jsonsring is"+jsonString);
		 res = given()
					.contentType("application/json")
				   .header("Authorization", header)
				   .body(jsonString)
				 
				.when()
						.post("aap/create")
				.then()
						//.statusCode(STATUS_200)
						.body("any { it.key == 'id' }", is(notNullValue())) 
						.log().all()
						.extract().response();
		 
		 String epid = (res.path("id")).toString();
		 ExcelParserUtils.setCellData(loginUserfile_path, emailListSheet, 1, 9, epid);
		
	}
	
	
}
