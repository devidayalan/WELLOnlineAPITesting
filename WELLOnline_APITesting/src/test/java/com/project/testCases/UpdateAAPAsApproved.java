package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdateAAPAsApproved extends TestBase {
	
	@Test
		@SuppressWarnings("unchecked")
		public void UpdateAAPAsAdmin() throws IOException {
			
			JSONObject projObj = new JSONObject();
			projObj = constructJSON(projObj);
			projObj.put("status",  "approved");
			String jsonString = projObj.toJSONString();
			 System.out.println("jsonsring is"+jsonString);
			 String aapId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "AAPId", 2);
			 res = given()
						.contentType("application/json")
					   .header("Authorization", admin_Header)
					   .pathParam("aap_id", aapId)
					   .body(jsonString)
					 
					.when()
							.put("admin/aap/{aap_id}")
					.then()
							.statusCode(STATUS_200)
							.log().all()
							.extract().response();
			
			
			
		}
	
	@Test(dependsOnMethods = "UpdateAAPAsAdmin")
	public void testApprovedStatusAAP() throws IOException {
		String aapId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "AAPId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("aap_id", aapId)
				    
				.when()
						.get("aap/{aap_id}")
				.then()
						.statusCode(STATUS_200)
						.body("status", Matchers.equalTo("approved") )
						.log().body()
						.extract().response();
		
	}

		@SuppressWarnings("unchecked")
		public JSONObject constructJSON(JSONObject projObj) throws IOException {
			
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
			return projObj;
			
		}
		
	



}
