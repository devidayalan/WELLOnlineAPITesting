package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

public class ResubmitAAP extends TestBase {
	
	@Test
	public void UpdateComment() throws IOException {
		
		 String aapId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "AAPId", 2);
		 res = given()
				 
				   .header("Authorization", header)
				   .pathParam("aap_id", aapId)
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
		
		 String aapId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "AAPId", 2);
		 res = given()
				 
				   .header("Authorization", header)
				   .pathParam("aap_id", aapId)
				.when()
						.post("aap/resubmit/{aap_id}")
				.then()
						.statusCode(STATUS_200)
						.log().all()
						.extract().response();
		
		
		
	}
	

}
