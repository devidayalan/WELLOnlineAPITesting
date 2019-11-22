package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetAAPTests extends TestBase {
	
	@Test
	public void getAAPAll() {
		res =
				given()
				.header("Authorization", header)
				    
				.when()
						.get("aap/all")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}
	
	@Test
	public void getAAPAllAsAdmin() {
		res =
				given()
				.header("Authorization", admin_Header)
				    
				.when()
						.get("admin/aap")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}
	
	@Test
	public void getAAPbyAAPId() throws IOException {
		String aapId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "AAPId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("aap_id", aapId)
				    
				.when()
						.get("aap/{aap_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}
	
	@Test
	public void getAAPbyAAPIdAsAdmin() throws IOException {
		String aapId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "AAPId", 2);
		res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("aap_id", aapId)
				    
				.when()
						.get("admin/aap/{aap_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}


}
