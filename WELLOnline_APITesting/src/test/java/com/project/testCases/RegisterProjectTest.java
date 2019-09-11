package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class RegisterProjectTest extends TestBase {

	@Test
	public void registerProject() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		 String countryid = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "countryId", 2);
		 String pricingOption = ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"pricing_option", 2);
		 	res = given()

				.header("Authorization", header).pathParam("project_id", projectId)
				.formParam("owner_email", ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Email", 3))
				.formParam("pricing_option",pricingOption)
				.formParam("country_id",Integer.parseInt(countryid))
				.formParam("doc_sub_date_est","2019-08-06")
				

				.when().post("project/register/{project_id}").then()
				.statusCode(STATUS_200)
				.log().all()
				.extract().response();
	}

}
