package com.project.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

public class InviteProjectMemberTest extends TestBase{
	
	@SuppressWarnings("unchecked")
	@Test
	public void inviteMemberAsProjectMember() throws IOException {
		
		JSONObject updateData = new JSONObject();
		updateData.put("role",ExcelParserUtils.getSingleCellData(loginUserfile_path, estimateSheet,"Role", 4)) ;
		updateData.put("position", ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"position", 8)) ;
		updateData.put("redirect-url", "https://stg-account.wellcertified.com/user/register");
		updateData.put("email", ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Email", 3));
		
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("project_id", projectId)
				.body(jsonString)
			       
				.when()
						.post("project/invite/{project_id}")
				.then()
						
						.log().body()
						.statusCode(403)
						.extract().response();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void inviteMemberAsProjectManager() throws IOException {
		
		JSONObject updateData = new JSONObject();
		updateData.put("role",ExcelParserUtils.getSingleCellData(loginUserfile_path, estimateSheet,"Role", 3)) ;
		updateData.put("position", ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"position", 8)) ;
		updateData.put("redirect-url", "https://stg-account.wellcertified.com/user/register");
		updateData.put("email", ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Email", 4));
		
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", admin_Header)
				.pathParam("project_id", projectId)
				.body(jsonString)
			       
				.when()
						.post("project/invite/{project_id}")
				.then()
						
						.log().body()
						.statusCode(204)
						.extract().response();
	}
	
	@Test (dependsOnMethods = {"inviteMemberAsProjectManager"})
	
	public void assertInvitedMember() throws IOException {
		
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
					given()
					.header("Authorization", admin_Header)
					.pathParam("project_id", projectId)
				       
					.when()
							.get("project/{project_id}/members")
					.then()
							.statusCode(STATUS_200)
							.log().body()
							.body("id[0]", Matchers.equalTo((Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "userid", 4)))))
							.body("pivot[0].project_id", Is.is((Integer.parseInt(projectId))))
							.extract().response();
	}
}


