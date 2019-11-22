package com.wellv1.project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EstimateV1Validator extends TestBase {
	
	int area;
	String project_Type;
	String estimateId;
	String country ="Country";
	String countryAndcorner = "CountryAndCorner";
	String countryPortfolio = "CountryAndPortfolio";
	String countryKey = "CountryAndKey";
	String regFee;
	String certifFee;
	String certifDiscountFee;
	String NODiscount = "NoDiscount";
	String keystone = "Keystone";
	String portfolio ="Portfolio";
	String cornerstone ="Cornerstone";
	String cs = "cs";
	String ci = "ci";
	String nc = "nc";
	String cics = "cics";
	String pilot = "residential";
	
	
	public void validateEstimate(String chkVal, int areaVal, String projectType) throws IOException {
		area = areaVal;
		project_Type = projectType;
		String json = constructJSON(chkVal);
		estimateId = getEstimateID(json);
	System.out.println("estimateId"+estimateId);
	//estimateId = "1472";
		Response response = callAPI(estimateId);
		validateResponse(response,chkVal);
	
		
	}
	
	@SuppressWarnings("unchecked")
	public String constructJSON(String chkVal) throws IOException {
		JSONObject updateData = new JSONObject();
		JSONArray projArr = new JSONArray();
		username = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email", 2);
		
		//create json payload object
		updateData.put("email",username) ;
		updateData.put("name", GenerateRandomTestDataUtils.getFirstName());
		updateData.put("organization",  GenerateRandomTestDataUtils.getOrganization());
		if(chkVal.equalsIgnoreCase(cornerstone)  || chkVal.equalsIgnoreCase(countryAndcorner) ) {
			updateData.put("membership", cornerstone);
		}
		else if(chkVal.equalsIgnoreCase(keystone) || chkVal.equalsIgnoreCase(countryKey)  ) {
			updateData.put("membership",keystone);
		}
		else if(chkVal.equalsIgnoreCase(portfolio) || chkVal.equalsIgnoreCase(countryPortfolio)  ) {
			updateData.put("membership",portfolio);
		}
		updateData.put("is_community",  false);
		if(chkVal.equalsIgnoreCase(country)||chkVal.equalsIgnoreCase(countryKey)
				|| chkVal.equalsIgnoreCase(countryAndcorner) || chkVal.equalsIgnoreCase(countryPortfolio))	 {
			updateData.put("country",ExcelParserUtils.getSingleCellData(estimatefile_path, estimateParams, "V1Country", 3));
		}
		else {
			updateData.put("country",ExcelParserUtils.getSingleCellData(estimatefile_path, estimateParams, "V1Country", 2));
		}
		
		JSONObject projObj = new JSONObject();
		projObj.put("name",  GenerateRandomTestDataUtils.getProjectName());
		projObj.put("area", area);
		projObj.put("type", project_Type);
	
		//Add created domain objects to json array
		projArr.add(projObj);
		
		//Add array to json payload object
		updateData.put("projects", projArr);
		String jsonString = updateData.toJSONString();
		
		System.out.println("final json string"+jsonString);
		return jsonString;
		
	}
	
	public String getEstimateID(String jsonString) throws IOException {
		res =
				given()
				.contentType("application/json")
				.header("Authorization", header)
				.body(jsonString)
			       
				.when()
						.post("well-v1/estimate")
				.then()
						
						.log().body()
						.statusCode(STATUS_200)
						.body("any { it.key == 'id' }", is(notNullValue())) 
						.extract().response();
		estimateId =(res.path("id")).toString();
		return estimateId;
	}
	
	public Response callAPI(String estimateid) throws IOException {
		res =
				given()
				.pathParam("estimate_id", estimateid)
			       
				.when()
						.get("well-v1/estimate/{estimate_id}")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		
		return res;
		
		
	}
	
	public void validateResponse(Response resp, String chkVal) throws IOException {
		String colName = project_Type;
		if(chkVal.equalsIgnoreCase(NODiscount) ) {
			extractFeeDetails(estimateV1Sheet, colName);
			assertFees(resp);
		}
		else if(chkVal.equalsIgnoreCase(portfolio)) {
			extractFeeDetails( estimateV1DiscountSheet, colName);
			assertDiscountedFees(resp);
		}
		else if(chkVal.equalsIgnoreCase(cornerstone)) {
			extractFeeDetails( estimateV1CornerStoneSheet, colName);
			assertDiscountedFees(resp);
		}
		else if(chkVal.equalsIgnoreCase(countryPortfolio)) {
			 colName = "country_"+project_Type; 
			extractFeeDetails( estimateV1DiscountSheet, colName);
			assertDiscountedFees(resp);
		}
		else if(chkVal.equalsIgnoreCase(countryAndcorner)) {
			colName = "country_"+project_Type;
			extractFeeDetails( estimateV1CornerStoneSheet, colName);
			assertDiscountedFees(resp);
		}
		 else if(chkVal.equalsIgnoreCase(country)) { 
			 colName = "country_"+project_Type; 
		 extractFeeDetails(estimateV1Sheet,colName);
		  assertFees(resp); 
		 }
		 
		
	}

	public void extractFeeDetails(String sheet, String colName) throws IOException {
		if(area<=50000) {
			regFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName, 2);
			certifFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName, 3);
			certifDiscountFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName, 4);
		}
		else if(area>50000 && area<=500000) {
			regFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName, 5);
			certifFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName,6);
			certifDiscountFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName,7);
		}
		else if(area> 500000) {
			regFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName, 8);
			certifFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName, 9);
			certifDiscountFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, sheet, colName, 10);
		}
	
		}
	
	public void assertDiscountedFees(Response resp) {
		String reg =  trailcomma(resp.path("formattedDiscountedRegPrice").toString());
		String bundle=  trailcomma(resp.path("formattedBundlePrice").toString());
		String bundleDisc =  trailcomma(resp.path("formattedDiscountedBundlePrice").toString());
		System.out.println("The values are regfee::"+regFee+"certificationfee::"+certifFee+"certificationDiscount::::"+certifDiscountFee);
		System.out.println("The values from excel::"+reg+"bundlefee::"+bundle+"bundlediscount::::"+bundleDisc);
		
		Assert.assertEquals(reg,  regFee);
		Assert.assertEquals(bundle,  certifFee);
		//Assert.assertEquals(bundleDisc,  certifDiscountFee);
	}
	
	@SuppressWarnings("unchecked")
	public void assertFees(Response resp) {
		String bundleDisc;
		String bundle;
		Map<String, Object> map = resp.jsonPath().getMap("estimate");
		List<Map<String, Object>> projects = (List<Map<String, Object>>) map.get("projects");
		String reg =  projects.get(0).get("regPrice").toString();
		if(area>500000) {
			bundle =  trailcomma(resp.path("formattedBundlePrice").toString());
			bundleDisc =  resp.path("discountedBundlePrice").toString();
		}
		else {
			bundle =  projects.get(0).get("bundlePrice").toString();
			 bundleDisc =  projects.get(0).get( "discountedBundlePrice").toString();
		}
			
		
		System.out.println("The values are regfee::"+regFee+"certificationfee::"+certifFee+"certificationDiscount::::"+certifDiscountFee);
		//String regDisc =  projects.get(0).get( "regPriceOffered").toString();
		Assert.assertEquals(reg,  regFee);
		Assert.assertEquals(bundle,  certifFee);
		//Assert.assertEquals(bundleDisc,  certifDiscountFee);
	
		
		
	}

}
