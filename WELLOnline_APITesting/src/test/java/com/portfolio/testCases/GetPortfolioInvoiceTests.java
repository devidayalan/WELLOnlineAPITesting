package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetPortfolioInvoiceTests extends TestBase {
	
	@Test
	public void getPortfolioInvoices() throws IOException {
		

		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
			       
				.when()
						.get("portfolio/{portfolio_id}/invoices")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		String invoiceId =(res.path("id[0]")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, portfolioSheet, 1, 1, invoiceId);
		
	}

}
