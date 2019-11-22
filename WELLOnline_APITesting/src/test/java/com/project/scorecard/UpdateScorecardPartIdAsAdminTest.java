package com.project.scorecard;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
//Not working
public class UpdateScorecardPartIdAsAdminTest extends TestBase {
	
	@Test
	public void updateScorecardPartIdAsAdmin() throws IOException {
		String partid = ExcelParserUtils.getSingleCellData(loginUserfile_path, scoreCardSheet, "partsid", 3);
		
		
		res =
				given()
				.contentType("application/json")
				.header("Authorization", admin_Header)
				.pathParam("{scorecard_part_id", partid)
				.formParam("summary", "Test")
				.when()
						.put("admin/scorecard/summary/{scorecard_part_id}")
				.then()
						//.statusCode(200)
						.log().body()
						.extract().response();
	}

}
