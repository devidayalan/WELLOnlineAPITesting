package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class InvitePortfolioMemberTests extends TestBase {
	//"message": "not valid role provided.",
   // "status_code": 422
	@SuppressWarnings("unchecked")
	@Test
	public void invitePortfolioMember() throws IOException {
		JSONObject updateData = new JSONObject();
		//ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Email", 3)
		//updateData.put("role",ExcelParserUtils.readRandomCellData(loginUserfile_path, portfolioSheet, "Role", 3)) ;
	//	updateData.put("position", ExcelParserUtils.readRandomCellData(loginUserfile_path, portfolioSheet, "Position", 10)) ;
		updateData.put("redirect-url", "https://stg-account.wellcertified.com/user/register");
		updateData.put("email","ashutosh@promantus.com" );
		updateData.put("role", "portfolio-admin");
		updateData.put("position", "architect");
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("portfolio_id", portfolioId)
				.body(jsonString) 
				.when()
						.post("portfolio/invite/{portfolio_id}")
				.then()
						//.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		
	}

}
