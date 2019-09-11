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
	public void inviteMember() throws IOException {
		
		JSONObject updateData = new JSONObject();
		updateData.put("role",ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"Role", 3)) ;
		updateData.put("position", ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"position", 8)) ;
		updateData.put("redirect-url", "https://test-account.wellcertified.com/user/register");
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
						.statusCode(204)
						.extract().response();
	}
	
	@Test (dependsOnMethods = {"inviteMember"})
	public void assertInvitedMember() throws IOException {
		
		projectId = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "ProjectId", 2);
		res =
					given()
					.header("Authorization", header)
					.pathParam("project_id", projectId)
				       
					.when()
							.get("project/{project_id}/members")
					.then()
							.statusCode(STATUS_200)
							.body("id[1]", Matchers.equalTo((Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "userid", 3)))))
							.body("pivot[1].project_id", Is.is((Integer.parseInt(projectId))))
							.extract().response();
		}
		
}


