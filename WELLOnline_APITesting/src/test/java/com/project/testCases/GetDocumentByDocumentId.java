package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class GetDocumentByDocumentId extends TestBase {
	
	@Test
	public void getDocumentByDocumentId() throws IOException {
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		docId = ExcelParserUtils.getSingleCellData(loginUserfile_path, emailListSheet, "documentId", 2);
		res =
				given()
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.pathParam("doc_id", docId)
			       
				.when()
						.get("project-document/{project_id}/{doc_id}")

				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
	}


}
