package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetProjectByProjectIdTest extends TestBase {
	@Test
	public void getprojectById() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("project_id", projectId)
			       
				.when()
						.get("project/{project_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		String countryId = res.path("country_id").toString();
		String project_type = res.path("project_type").toString();
		String name = res.path("name").toString();
		String version = res.path("applicable_version_id").toString();
	
		
		ExcelParserUtils.setCellData(loginUserfile_path, UsersSheet, 1, 6, countryId);
		ExcelParserUtils.setCellData(loginUserfile_path, scoreCardSheet, 1, 1, project_type);
		ExcelParserUtils.setCellData(loginUserfile_path, scoreCardSheet, 1, 0, name);
		ExcelParserUtils.setCellData(loginUserfile_path, scoreCardSheet, 1, 3, version);
	}
	

	//@Test(dependsOnMethods = {"getprojectById"})
	
}
