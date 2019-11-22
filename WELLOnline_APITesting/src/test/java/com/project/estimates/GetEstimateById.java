package com.project.estimates;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetEstimateById extends TestBase {
	
	@Test
	public void getEstimateById() throws IOException {
		estimateId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, estimateSheet, "Estimate ID", 2);
		res =
				given()
				.pathParam("estimate_id", estimateId)
			       
				.when()
						.get("estimate/{estimate_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		projectsIdForEstimate = (res.path("projects[0].id")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, estimateSheet, 1, 6, projectsIdForEstimate);
	}


}
