package com.portfolio.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
//Error on space type, project type, owner industry
//address - front end alert to enter complete address
public class PortfolioBulkUpdateTests extends TestBase {
	@Test
	public void portfolioBulkUpdateOwnerName() throws IOException {
		addData("owner_name", "test_Owner");
		
	}
	
	@Test
	public void portfolioBulkUpdateprojectpublic() throws IOException {
		addData("project_public", "0");
		
	}
	
	@Test
	public void portfolioBulkUpdateFund() throws IOException {
		addData("fund_name", "test_fund");
		
	}
	
	@Test
	public void portfolioBulkUpdateConstructionStatus() throws IOException {
		addData("construction_type", "cs");
		
	}
	
	@Test
	public void portfolioBulkUpdateIndustry() throws IOException {
		
		String industry =  ExcelParserUtils.readRandomCellData(loginUserfile_path, portfolioSheet, "Industry", 26);
		addData("industry", industry);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void addData (String field, String value) throws IOException {
		JSONObject updateData = new JSONObject();
		JSONArray projIds = new JSONArray();
		JSONArray data = new JSONArray();
		Map dataMap = new HashMap();
		projIds = addProjId(); 
		dataMap.put("field",field);
		if(value.equalsIgnoreCase("0"))
			dataMap.put("value", 0)	;
		else
			dataMap.put("value", value);
		data.add(dataMap);
		updateData.put("project_ids",projIds);
		updateData.put("data", data);
		String jsonString = updateData.toJSONString();
		System.out.println("jsonstring is"+jsonString);
		callAPI(jsonString);
	}
	
	public JSONArray addProjId() throws IOException {
		JSONArray projIds = new JSONArray();
		for(int i=0;i<4;i++) {
			int j = i+2;
			String id =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "ProjIds", j);
			projIds.add(id);
		}
		return projIds;
		
	}
	
	public void callAPI(String jsonString) throws IOException {
		String portfolioId =  ExcelParserUtils.getSingleCellData(loginUserfile_path, portfolioSheet, "Id", 2);
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.pathParam("portfolio_id", portfolioId)
			    .body(jsonString)
			       
				.when()
						.post("portfolio/projects/{portfolio_id}/bulkUpdate")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
	}

}
