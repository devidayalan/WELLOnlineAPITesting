package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class ResubmitEP extends TestBase {
	

	@Test
	public void UpdateCommentForEP() throws IOException {
		
		 String epId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "EPId", 2);
		 res = given()
				 
				   .header("Authorization", header)
				   .pathParam("aap_id", epId)
				   .param("comment","<p>"+GenerateRandomTestDataUtils.getTestString()+"</p>")
				 
				.when()
						.post("aap/{aap_id}/comment")
				.then()
						.statusCode(STATUS_200)
						.log().all()
						.extract().response();
		
		
		
	}
	
	@Test
	public void reSubmitAAP() throws IOException {
		
		 String epId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "EPId", 2);
		 res = given()
				 
				   .header("Authorization", header)
				   .pathParam("aap_id", epId)
				.when()
						.post("aap/resubmit/{aap_id}")
				.then()
						.statusCode(STATUS_200)
						.log().all()
						.extract().response();
		
		
		
	}
	

}
