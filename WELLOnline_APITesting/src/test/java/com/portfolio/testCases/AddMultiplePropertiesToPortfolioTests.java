package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class AddMultiplePropertiesToPortfolioTests extends TestBase {
	
	@Test
	public void addMultipleSQFTPropertiesToPortfolio() throws IOException {
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
				.param("unit", "sqft") 
				.multiPart("file", new File(Portfolio_properties_upload_sqft))
				.when()
						.post("/portfolio/{portfolio_id}/project/excel-parse")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		String count =(res.path("count")).toString();
		Assert.assertEquals(count, "10");
	}
	
	@Test
	public void addMultipleSQMPropertiesToPortfolio() throws IOException {
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
				.param("unit", "sqm") 
				.multiPart("file", new File(Portfolio_properties_upload_sqm))
				.when()
						.post("/portfolio/{portfolio_id}/project/excel-parse")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		String count =(res.path("count")).toString();
		int value = Integer.parseInt(count);
		for(int i = 0; i<value;i++) {
			
			String id = res.path("ids["+i+"]").toString();
			System.out.println("id is"+id);
			int j = i+1;
			ExcelParserUtils.setCellData(loginUserfile_path, portfolioSheet, +j, 13, id);
		}
		Assert.assertEquals(count, "7");
	}

}
