package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;
import com.common.utils.GenerateSpaceTypesID;

public class AddProjectsToPortfolioTests extends TestBase {

	@Test
	@SuppressWarnings("unchecked")
	public void addProjectsToPortfolio() throws IOException {
		
		JSONObject updateData = new JSONObject();
		JSONObject projObj = new JSONObject();
		JSONArray projArr = new JSONArray();
		projObj.put("name",  GenerateRandomTestDataUtils.getProjectName());
		projObj.put("area", GenerateRandomTestDataUtils.getNumeric());
		projObj.put("project_org", GenerateRandomTestDataUtils.getTestString());
		projObj.put("project_type", ExcelParserUtils.readRandomCellData(loginUserfile_path, estimateSheet,"type", 3));
		projObj.put("no_of_occupants", Integer.parseInt(GenerateRandomTestDataUtils.getNumeric()));
		projObj.put("project_public", true);
		projObj.put("d_and_o", 0);
		projObj.put("well_cert", "");
		projObj.put("other_certification", "");
		projObj.put("construction_status", null); 
		projObj.put("construction_type", ""); 
		projObj.put("fund_name", "");
		
		JSONArray spaceTyp = new JSONArray();
		spaceTyp.add(0,GenerateSpaceTypesID.generateSpaceTypes());
		//spaceTyp.add(1,GenerateSpaceTypesID.generateSpaceTypes());
		
		projObj.put("space_types", spaceTyp);
		projObj.put("primary_space_type_id", 12);
		projObj.put("street", GenerateRandomTestDataUtils.getTestString());
		projObj.put("city", GenerateRandomTestDataUtils.getTestString());
		projObj.put("state", GenerateRandomTestDataUtils.getTestString());
		projObj.put("country_code", ExcelParserUtils. readRandomCellData(estimatefile_path, estimateParams, "Country",75));
		projObj.put("postal_code", GenerateRandomTestDataUtils.getPostalcode());
		projObj.put("owner_name", GenerateRandomTestDataUtils.getFirstName());
		projObj.put("owner_email", GenerateRandomTestDataUtils.getEmail());
		projObj.put("owner_org", GenerateRandomTestDataUtils.getOrganization());
		projObj.put("owner_phone", GenerateRandomTestDataUtils.getPhone());
		projObj.put("owner_street", GenerateRandomTestDataUtils.getTestString());
		projObj.put("owner_city", GenerateRandomTestDataUtils.getTestString());
		projObj.put("owner_state", GenerateRandomTestDataUtils.getTestString());
		projObj.put("owner_country_code", ExcelParserUtils. readRandomCellData(estimatefile_path, estimateParams, "Country",75));
		projObj.put("owner_zip", GenerateRandomTestDataUtils.getPostalcode());
		projObj.put("industry", ExcelParserUtils. readRandomCellData(loginUserfile_path, portfolioSheet, "Industry",26));
		//projObj.put("org_website", area);
		//projObj.put("org_overview", area);
		projArr.add(projObj);
		updateData.put("projects", projArr);
		updateData.put("unit", ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "unit", 2));
		
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
				.body(jsonString)   
				.when()
						.post("portfolio/projects/{portfolio_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		String projectId =(res.path("ids[0]")).toString();
		ExcelParserUtils.setCellData(loginUserfile_path, portfolioSheet, 1, 5, projectId);
		
		
	}

}
