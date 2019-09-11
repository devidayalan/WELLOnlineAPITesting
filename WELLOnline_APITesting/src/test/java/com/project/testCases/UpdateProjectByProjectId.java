package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class UpdateProjectByProjectId extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateProject() throws IOException {
		Integer area = Integer.parseInt(GenerateRandomTestDataUtils.getNumeric());
		 Integer countryid =Integer.parseInt( ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "countryId", 2));
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		JSONObject updateData = new JSONObject();
		updateData.put("name",GenerateRandomTestDataUtils.getProjectName()) ;
		updateData.put("area", area);
		updateData.put("project_type", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet,"type", 3));
		updateData.put("country_id", countryid);
		String jsonString = updateData.toJSONString();
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.body(jsonString)
			       
				.when()
						.put("project/update/{project_id}")
				.then()
						
						.log().body()
						.statusCode(STATUS_200)
						//.assertThat().body("id", Is.is(Integer.parseInt(projectId)))
						.body("id", Matchers.equalTo(Integer.parseInt(projectId))) 
						.extract().response();
		
		
		
		
	}

}
