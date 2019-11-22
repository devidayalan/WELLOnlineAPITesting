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

public class CreateProjectDocumentTest extends TestBase {
	@SuppressWarnings("unchecked")
	@Test
	public void addProjectDocument() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		JSONObject parametr = new JSONObject();
		
			 parametr.put("link_s3",  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "Link", 2));
			 parametr.put("name", GenerateRandomTestDataUtils.getFirstName());
			 parametr.put("type", ExcelParserUtils.readRandomCellData(loginUserfile_path, emailListSheet, "documentType", 2));
			// JSONArray features = new JSONArray();
			// features.add(0, GenerateRandomTestDataUtils.getFirstName());
			// parametr.put("v1features", features);
			
			 String jsonString = parametr.toJSONString();
			 System.out.println("jsonsring is"+jsonString);
		res = given()
				.contentType("application/json")
			   .header("Authorization", header)
			   .pathParam("project_id", projectId)
			   .body(jsonString)
			 
			.when()
					.post("project-document/{project_id}")
			.then()
					.statusCode(STATUS_200)
					.body("project_id", Matchers.equalTo(projectId))
					.body("any { it.key == 'id' }", is(notNullValue())) 
					.log().all()
					.extract().response();
		String documentId =(res.path("id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, emailListSheet, 1, 3, documentId);
	}

}
