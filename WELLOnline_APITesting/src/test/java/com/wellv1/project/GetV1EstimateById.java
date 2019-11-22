package com.wellv1.project;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetV1EstimateById extends TestBase {
	
	@Test
	public void getV1EstimateById() throws IOException {
		v1EstimateId = "1420"; //ExcelParserUtils.getSingleCellData(loginUserfile_path, v1_projectSheet, "estimateId", 2);
		res =
				given()
				.pathParam("estimate_id", v1EstimateId)
			       
				.when()
						.get("well-v1/estimate/{estimate_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		//projectsIdForEstimate = (res.path("projects[0].id")).toString();
		//ExcelParserUtils.setCellData(loginUserfile_path, estimateSheet, 1, 6, projectsIdForEstimate);
	}

}
