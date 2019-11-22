package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetSubsetProjectsByIdTests extends TestBase {
	
	@Test
	public void getSubsetProjectsById() throws IOException {
		

		String subsetid = ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "SubsetId", 2);
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
				.pathParam("subset_id", subsetid)
			       
				.when()
						.get("portfolio/{portfolio_id}/subset/{subset_id}/projects")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
