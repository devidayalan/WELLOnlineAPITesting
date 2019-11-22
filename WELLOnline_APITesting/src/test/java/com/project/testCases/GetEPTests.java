package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetEPTests extends TestBase {
	

	@Test
	public void getEPbyEPId() throws IOException {
		String epId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "EPId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("aap_id", epId)
				    
				.when()
						.get("aap/{aap_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}
	
	@Test
	public void getEPbyEPIdAsAdmin() throws IOException {
		String epId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "EPId", 2);
		res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("aap_id", epId)
				    
				.when()
						.get("admin/aap/{aap_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
