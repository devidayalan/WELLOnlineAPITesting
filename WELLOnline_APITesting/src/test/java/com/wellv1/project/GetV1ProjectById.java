package com.wellv1.project;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetV1ProjectById extends TestBase {
	
	@Test
	public void getV1ProjectById() throws IOException {
		v1projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, v1_projectSheet, "projectId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("project_id", v1projectId)
			       
				.when()
						.get("project/{project_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}


}
