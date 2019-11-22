package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetPortfolioAgreementTests extends TestBase {
	@Test
	public void getPortfolioAgreement() throws IOException {
		
/*<html>
  <body>error calling webservice, status is:400
error text is --&gt; {
  "errorCode": "ENVELOPE_IS_INCOMPLETE",
  "message": "The Envelope is not Complete. A Complete Envelope Requires Documents, Recipients, Tabs, and a Subject Line."
}
</body>
</html>*/
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
			       
				.when()
						.get("portfolio/agreement/{portfolio_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
