package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetProjectNoteByNoteId extends TestBase {
	
	@Test
	public void getProjectNoteByNoteId() throws IOException {
		
		String note_id  =ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "note_id", 2);
		res =
				given()
				.header("Authorization", admin_Header)
				.pathParam("id", note_id)
			       
				.when()
						.get("admin/project-note/{id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}

}
