package com.project.estimates;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;
import com.common.utils.GenerateSpaceTypesID;

import io.restassured.response.Response;

public class EstimateValidator extends TestBase {
	
	String NODiscount = "NoDiscount";
	String regFeeSingle;
	String regFeeSingleDisc;
	String certFeeSingleDisc;
	String pvSingle;
	String pvSingleDisc;
	String certFeeSingle;
	String regFee3Term;
	String regFee3TermDisc;
	String certFee3Term;
	String certFee3TermDisc;
	String pv3Term;
	String pv3TermDisc;
	String regFee5Term;
	String regFee5TermDisc;
	String certFee5Term;
	String certFee5TermDisc;
	String pv5Term;
	String pv5TermDisc;
	String travelFee;
	String subTotalSingle;
	String subTotal3Term;
	String subTotal5Term;
	String subTotalSingleAfterDisc;
	String subTotal3TermAfterDisc;
	String subTotal5TermAfterDisc;
	String totalSingle;
	String total3Term;
	String total5Term;
	String totalSingleAfterDisc;
	String total3TermAfterDisc;
	String total5TermAfterDisc;
	float persqftRateSingle;
	float persqftRate3Term;
	float persqftRate5Term;
	float persqftRateSingleAfterDisc;
	float persqftRate3TermAfterDisc;
	float persqftRate5TermAfterDisc;
	int area;
	String keystone = "Keystone";
	String portfolio ="Portfolio";
	String cornerstone ="Cornerstone";
	String country ="Country";
	String countryAndcorner = "CountryAndCorner";
	String countryPortfolio = "CountryAndPortfolio";
	String countryKey = "CountryAndKey";
	String sector = "sector";
	String cornerAndsector = "CornerAndSector";
	String keyAndsector = "KeyAndSector";
	String portfolioSector = "PortfolioAndSector";
	String countryKeySector = "CountryKeyAndSector";
	String countryPortfolioSector = "CountryPortfolioAndSector";
	String countryCornerSector = "CountryCornerAndSector";
	boolean wellCore;
	
		
		public void validateEstimate(String chkVal, int areaVal,boolean wellcore) throws IOException {
			area = areaVal;
			wellCore = wellcore;
			String json = constructJSON(chkVal);
			System.out.println("json"+json);
			estimateId = getEstimateID(json);
			System.out.println("estimateId"+estimateId);
			
			Response response = callAPI(estimateId);
			validateResponse(response,chkVal);
		
			
		}
		
		
		//Delete this method when create works
		@Test
		public void testEstimate() throws IOException {
			area = 10000;
			estimateId = "643";
			wellCore = true;
			Response response = callAPI(estimateId);
			validateResponse(response,NODiscount);
		}
		
		@SuppressWarnings("unchecked")
		public String constructJSON(String chkVal) throws IOException {
			username = ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email", 2);
			String jsonString;
			JSONObject updateData = new JSONObject();
			JSONArray projArr = new JSONArray();
			updateData.put("email_address",username) ;
			updateData.put("first_name", GenerateRandomTestDataUtils.getFirstName());
			updateData.put("last_name", GenerateRandomTestDataUtils.getLastName());
			if(chkVal.equalsIgnoreCase(country)||chkVal.equalsIgnoreCase(countryKey) || chkVal.equalsIgnoreCase(countryKeySector)
					|| chkVal.equalsIgnoreCase(countryAndcorner) || chkVal.equalsIgnoreCase(countryCornerSector) || chkVal.equalsIgnoreCase(countryPortfolio)
					|| chkVal.equalsIgnoreCase(countryPortfolio) || chkVal.equalsIgnoreCase(countryPortfolioSector)) {
				updateData.put("country",ExcelParserUtils. readRandomCellData(estimatefile_path, estimateParams, "Country",78));
			}
			else {
				updateData.put("country", ExcelParserUtils. readRandomCellData(estimatefile_path, estimateParams, "CountryNODiscount",20));
			}
				updateData.put("organization",  GenerateRandomTestDataUtils.getOrganization());
			if(chkVal.equalsIgnoreCase(cornerstone) || chkVal.equalsIgnoreCase(cornerAndsector) || chkVal.equalsIgnoreCase(countryAndcorner) || chkVal.equalsIgnoreCase(countryCornerSector)) {
				updateData.put("membership", cornerstone);
			}
			else if(chkVal.equalsIgnoreCase(keystone) || chkVal.equalsIgnoreCase(keyAndsector)|| chkVal.equalsIgnoreCase(countryKey) || chkVal.equalsIgnoreCase(countryKeySector) ) {
				updateData.put("membership",keystone);
			}
			else if(chkVal.equalsIgnoreCase(portfolio) || chkVal.equalsIgnoreCase(portfolioSector)|| chkVal.equalsIgnoreCase(countryPortfolio) || chkVal.equalsIgnoreCase(countryPortfolioSector) ) {
				updateData.put("membership",portfolio);
			}
			JSONObject projObj = new JSONObject();
			projObj.put("name",  GenerateRandomTestDataUtils.getProjectName());
			projObj.put("area", area);
			if(wellCore)
				projObj.put("type",ExcelParserUtils.getSingleCellData(estimatefile_path, estimateParams, "Project type", 2));
			else
				projObj.put("type",ExcelParserUtils.getSingleCellData(estimatefile_path, estimateParams, "Project type", 3));
			JSONArray spaceTyp = new JSONArray();
			spaceTyp.add(0,GenerateSpaceTypesID.generateSpaceTypes());
			//spaceTyp.add(1,GenerateSpaceTypesID.generateSpaceTypes());
			
			projObj.put("space_types", spaceTyp);
			if(chkVal.equalsIgnoreCase(sector) || chkVal.equalsIgnoreCase(keyAndsector) || chkVal.equalsIgnoreCase(cornerAndsector) || chkVal.equalsIgnoreCase(portfolioSector) 
					|| chkVal.equalsIgnoreCase(countryKeySector)  || chkVal.equalsIgnoreCase(countryCornerSector) || chkVal.equalsIgnoreCase(countryPortfolioSector)) {
				projObj.put("estimate_sector", ExcelParserUtils. readRandomCellData(loginUserfile_path, estimateSheet, "estimate_sector",4));
			}
			else {
				projObj.put("estimate_sector", "");
			}
			projArr.add(projObj);
			
			updateData.put("projects", projArr);
			jsonString = updateData.toJSONString();
			return jsonString;

		}
		
		public String getEstimateID(String jsonString) throws IOException {
			res =
					given()
					.contentType("application/json")
					.header("Authorization", header)
					.body(jsonString)
				       
					.when()
							.post("estimate/create")
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
							.get("estimate/{estimate_id}")
					.then()
							.statusCode(STATUS_200)
							//.log().body()
							.extract().response();
			return res;
			
			
		}
		
		
		public void validateResponse(Response resp, String chkVal) throws IOException {
			boolean areaCal =  false;
			String colName = null;
			System.out.println("area value is"+area);
			if(area<=49999)
					colName = "lessthan50";
			else if(area>=50000 && area<=149999) {
				colName = "50000-149999";
				areaCal =  true;
			}
			else if(area>=150000 && area<=249999) {
				colName = "150000-249999";
				areaCal =  true;
			}
			else if(area>250000 && area<=499999) {
				colName = "250000-499999";
				areaCal =  true;
			}
			else if(area>500000 && area<=749999) {
				colName = "500000-749999";
				areaCal =  true;
			}
			else if(area>750000 && area<=999999) {
				colName = "750000-999999";
				areaCal =  true;
				
			}
			extractFeeDetails(colName);
			if(areaCal) {
				double ratePerSqft = Double.parseDouble(certFeeSingle);
				double value =area*ratePerSqft; 
				int finalVal = (int) Math.round(value);
				certFeeSingle = String.valueOf(finalVal);
			}
			calculateDiscount(chkVal);
			calculateOtherParameters(area);
			assertSingleTerm(resp);
			assertTerm( resp, "term-3") ;
			assertTerm( resp, "term-5") ;
			AssertEstimateSummary.calculateSummaryParameters(resp);
			
		}
	
		public void calculateOtherParameters(int area) {
			DecimalFormat df = new DecimalFormat("0.000");
			
			subTotalSingle = String.valueOf((Integer.parseInt(regFeeSingle) +Integer.parseInt(certFeeSingle)));
			subTotal3Term = String.valueOf((Integer.parseInt(regFee3Term) +(Integer.parseInt(certFee3Term)*3)));
			subTotal5Term = String.valueOf((Integer.parseInt(regFee5Term) +(Integer.parseInt(certFee5Term)*5)));
			subTotalSingleAfterDisc = String.valueOf((Integer.parseInt(regFeeSingleDisc) +Integer.parseInt(certFeeSingleDisc)));
			subTotal3TermAfterDisc = String.valueOf((Integer.parseInt(regFee3TermDisc) +(Integer.parseInt(certFee3TermDisc)*3)));
			subTotal5TermAfterDisc = String.valueOf((Integer.parseInt(regFee5TermDisc) +(Integer.parseInt(certFee5TermDisc)*5)));
			
			totalSingle =String.valueOf(Integer.parseInt(subTotalSingle) +(Integer.parseInt(pvSingle)-Integer.parseInt(travelFee)));
			totalSingleAfterDisc =String.valueOf(Integer.parseInt(subTotalSingleAfterDisc) +(Integer.parseInt(pvSingleDisc)-Integer.parseInt(travelFee)));
			total3Term =String.valueOf(Integer.parseInt(subTotal3Term) +(Integer.parseInt(pv3Term) -Integer.parseInt(travelFee)));
			total3TermAfterDisc =String.valueOf(Integer.parseInt(subTotal3TermAfterDisc) +(Integer.parseInt(pv3TermDisc) -Integer.parseInt(travelFee)));
			total5Term =String.valueOf(Integer.parseInt(subTotal5Term) +(Integer.parseInt(pv5Term) -Integer.parseInt(travelFee)));
			total5TermAfterDisc =String.valueOf(Integer.parseInt(subTotal5TermAfterDisc) +(Integer.parseInt(pv5TermDisc) -Integer.parseInt(travelFee)));
			
			persqftRateSingle =   Float.valueOf((df.format(Float.parseFloat(certFeeSingle)/area)));
			persqftRate3Term =  Float.valueOf((df.format(Float.parseFloat(total3Term)/area)));
			persqftRate5Term = Float.valueOf((df.format(Float.parseFloat(total5Term)/area)));
			
			persqftRateSingleAfterDisc = Float.valueOf((df.format(Float.parseFloat(certFeeSingleDisc)/area)));
			persqftRate3TermAfterDisc =Float.valueOf((df.format(Float.parseFloat(total3TermAfterDisc)/area)));
			persqftRate5TermAfterDisc = Float.valueOf((df.format(Float.parseFloat(total5TermAfterDisc)/area)));
			
		}
		
		public void calculateDiscount(String chkVal) throws IOException {
			double perDisc = Double.parseDouble(ExcelParserUtils.getSingleCellData(estimatefile_path, estimateDiscount, "discountPercent", 5));
			double pvDisc = Double.parseDouble(ExcelParserUtils.getSingleCellData(estimatefile_path, estimateDiscount, "PV", 5));
			double param2Disc = Double.parseDouble(ExcelParserUtils.getSingleCellData(estimatefile_path, estimateDiscount, "discountPercent", 7));
			double param3Disc = Double.parseDouble(ExcelParserUtils.getSingleCellData(estimatefile_path, estimateDiscount, "discountPercent", 8));
			
			
			if(chkVal.equalsIgnoreCase(NODiscount)) {
				regFeeSingleDisc = regFeeSingle;
				certFeeSingleDisc = certFeeSingle;
				pvSingleDisc = pvSingle;
				regFee3TermDisc = regFee3Term;
				certFee3TermDisc = certFee3Term;
				pv3TermDisc = pv3Term;
				regFee5TermDisc = regFee5Term;
				certFee5TermDisc = certFee5Term;
				pv5TermDisc = pv5Term;
				
			}
			
			else if(chkVal.equalsIgnoreCase(cornerstone)) {
				calculateMemberDisc(Double.parseDouble(ExcelParserUtils.getSingleCellData(estimatefile_path, estimateDiscount, "discountPercent", 2)));
				
			}
			else if(chkVal.equalsIgnoreCase(keystone) || chkVal.equalsIgnoreCase(portfolio)) {
				calculateMemberDisc(Double.parseDouble(ExcelParserUtils.getSingleCellData(estimatefile_path, estimateDiscount, "discountPercent", 3)));
				
			}
			else if(chkVal.equalsIgnoreCase(country) || chkVal.equalsIgnoreCase(sector)) {
				calculateSectorDisc(perDisc,pvDisc);
				
			}
			else if(chkVal.equalsIgnoreCase(cornerAndsector) ||chkVal.equalsIgnoreCase(countryAndcorner) 
					|| chkVal.equalsIgnoreCase(countryCornerSector)) {
				calculateSectorDisc(param2Disc,pvDisc);
			}
			else if(chkVal.equalsIgnoreCase(keyAndsector) ||  chkVal.equalsIgnoreCase(portfolioSector)
					|| chkVal.equalsIgnoreCase(countryKey) ||  chkVal.equalsIgnoreCase(countryPortfolio)
					||chkVal.equalsIgnoreCase(countryKeySector) || chkVal.equalsIgnoreCase(countryPortfolioSector) ) {
				calculateSectorDisc(param3Disc,pvDisc);
				
			}
			System.out.println("regFeeSingleDisc:"+regFeeSingleDisc+"certFeeSingleDisc:"+certFeeSingleDisc+"pvSingleDisc:"+pvSingleDisc+"regFee3TermDisc:"+regFee3TermDisc);
			System.out.println("certFee3TermDisc:"+certFee3TermDisc+"pv3TermDisc:"+pv3TermDisc+"regFee5TermDisc:"+regFee5TermDisc+"certFee5TermDisc:"+certFee5TermDisc+"pv5TermDisc:"+pv5TermDisc);
			
		}
		
		
		public void calculateMemberDisc(double disc) {
			regFeeSingleDisc = String.valueOf(calDiscPercentAmount(disc,regFeeSingle));
			certFeeSingleDisc = String.valueOf(calDiscPercentAmount(disc,certFeeSingle));
			pvSingleDisc = pvSingle;
			regFee3TermDisc = String.valueOf(calDiscPercentAmount(disc,regFee3Term));
			certFee3TermDisc = String.valueOf(calDiscPercentAmount(disc,certFee3Term));
			pv3TermDisc = pv3Term;
			regFee5TermDisc = String.valueOf(calDiscPercentAmount(disc,regFee5Term));
			certFee5TermDisc = String.valueOf(calDiscPercentAmount(disc,certFee5Term));
			pv5TermDisc = pv5Term;
		
		}
		public void calculateSectorDisc(double feeDisc, double pvDisc) {
			regFeeSingleDisc = String.valueOf(calDiscPercentAmount(feeDisc,regFeeSingle));
			certFeeSingleDisc = String.valueOf(calDiscPercentAmount(feeDisc,certFeeSingle));
			pvSingleDisc = String.valueOf(calDiscPercentAmount(pvDisc,pvSingle));
			regFee3TermDisc = String.valueOf(calDiscPercentAmount(feeDisc,regFee3Term));
			certFee3TermDisc = String.valueOf(calDiscPercentAmount(feeDisc,certFee3Term));
			pv3TermDisc = String.valueOf(calDiscPercentAmount(pvDisc,pv3Term));
			regFee5TermDisc = String.valueOf(calDiscPercentAmount(feeDisc,regFee5Term));
			certFee5TermDisc = String.valueOf(calDiscPercentAmount(feeDisc,certFee5Term));
			pv5TermDisc = String.valueOf(calDiscPercentAmount(pvDisc,pv5Term));
		
		}
		
		public int calDiscPercentAmount(double disc, String amount) {
			double total = Double.parseDouble(amount);
			double  s,discAmount;
			      s=100-disc;
		 
		        discAmount= (s*total)/100;
		        int rounded = (int) Math.round(discAmount);
		   return rounded ;
		}
		
		public void extractFeeDetails(String colName) throws IOException {
			
			
			if(wellCore) {
				certFeeSingle = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateSingleList, colName, 3);
				certFee3Term = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm3, colName, 3);
				certFee5Term = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm5, colName, 3);
				pvSingle =  ExcelParserUtils.getSingleCellData(estimatefile_path, estimateSingleList, colName, 4);
				pv3Term =  ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm3, colName, 4);
				pv5Term =  ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm5, colName, 4);
			}
			else {
				certFeeSingle = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateSingleList, colName, 5);
				certFee3Term = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm3, colName, 5);
				certFee5Term = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm5, colName, 5);
				pvSingle =  ExcelParserUtils.getSingleCellData(estimatefile_path, estimateSingleList, colName, 6);
				pv3Term =  ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm3, colName, 6);
				pv5Term =  ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm5, colName, 6);
			}
			regFeeSingle = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateSingleList, colName, 2);
			regFee3Term = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm3, colName, 2);
			regFee5Term = ExcelParserUtils.getSingleCellData(estimatefile_path, estimateterm5, colName, 2);
			travelFee =  ExcelParserUtils.getSingleCellData(estimatefile_path, estimateSingleList, colName, 7);
			
			System.out.println("regFeeSingle:"+regFeeSingle+"certFeeSingle:"+certFeeSingle+"pvSingle:"+pvSingle+"regFee3Term:"+regFee3Term);
			System.out.println("certFee3Term:"+certFee3Term+"pv3Term:"+pv3Term+"regFee5Term:"+regFee5Term+"certFee5Term:"+certFee5Term+"pv5Term:"+pv5Term);
			
			
		}
	
		 
		public void assertSingleTerm(Response resp) {
			String reg = trailcomma(resp.path("projects[0].registrationAmount").toString());
			String regDisc = trailcomma(resp.path("projects[0].regAfterDiscount").toString());
			String cerf = trailcomma(resp.path("projects[0].certificationAmount").toString());
			String cerfDisc = trailcomma(resp.path("projects[0].certAfterDiscount").toString());
			String pvDisc = trailcomma(resp.path("projects[0].pvAfterDiscount").toString());
			String pv = trailcomma(resp.path("projects[0].pvAmount").toString());
			
			String subTotal = trailcomma(resp.path("projects[0].subTotalAmount").toString());
			String subTotalAfterDisc = trailcomma(resp.path("projects[0].subTotalAmountAfterDis").toString());
			String totalAmount = trailcomma(resp.path("projects[0].totalAmount").toString());
			String totalAmountAfterDisc = trailcomma(resp.path("projects[0].totalAmountAfterDis").toString());
			Float perSqFt = ((Float)resp.path("projects[0].perSqFt"));
			Float perSqFtAfterDisc = ((Float)resp.path("projects[0].perSqFtAfterDis"));
			
			System.out.println("subTotalSingle is.."+subTotalSingle+"--::---"+subTotalSingleAfterDisc);
			System.out.println("totalSingleAfterDisc is.."+totalSingle+"--::---"+totalSingleAfterDisc);
			System.out.println("totalSingleAfterDisc is.."+persqftRateSingle+"--::---"+persqftRateSingleAfterDisc);
			Assert.assertEquals(reg,  regFeeSingle);
			Assert.assertEquals(cerf,  certFeeSingle);
			Assert.assertEquals(pv,  pvSingle);
			Assert.assertEquals(regDisc,  regFeeSingleDisc);
			Assert.assertEquals(cerfDisc,  certFeeSingleDisc);
			Assert.assertEquals(pvDisc,  pvSingleDisc);
			
			Assert.assertEquals(subTotal,  subTotalSingle);
			Assert.assertEquals(subTotalAfterDisc,  subTotalSingleAfterDisc);
			Assert.assertEquals(totalAmount, totalSingle);
			Assert.assertEquals(totalAmountAfterDisc,  totalSingleAfterDisc);
			Assert.assertEquals(perSqFt,  persqftRateSingle);
			Assert.assertEquals(perSqFtAfterDisc,  persqftRateSingleAfterDisc);
			
		}
	
		@SuppressWarnings("unchecked")
		public void assertTerm(Response resp, String term) {
			
			Map<String, Object> map = resp.jsonPath().getMap("projects[0].subscription");
			Map<String, Object> value = (Map<String, Object>) map.get(term);
			String reg = trailcomma(value.get("registration_amount").toString());
			String cerf = trailcomma(value.get("annual_amount").toString());
			String pv = trailcomma(value.get("pv_amount").toString());
			String regDisc = trailcomma(value.get("reg_after_dis").toString());
			String cerfDisc = trailcomma(value.get("annual_after_dis").toString());
			String pvDisc = trailcomma(value.get("pv_after_dis").toString());
			
			String subTotal = trailcomma(value.get("sub_total_amount").toString());
			String subTotalAfterDisc = trailcomma(value.get("sub_total_after_dis").toString());
			String totalAmount = trailcomma(value.get("total_amount").toString());
			String totalAmountAfterDisc = trailcomma(value.get("total_after_dis").toString());
			Float perSqFt = (Float) value.get("per_sq_ft"); //1.565
			Float perSqFtAfterDisc = (Float) value.get("per_sq_ft_after_dis"); //1.48
			
			
			System.out.println("subTotal.."+subTotal+"--::---"+subTotal3Term);
			System.out.println("subTotal after dsc.."+subTotalAfterDisc+"--::---"+subTotal3TermAfterDisc);
			System.out.println("total amount 3 term.."+totalAmount+"--::---"+total3Term);
			System.out.println("total3TermAfterDisc.."+totalAmountAfterDisc+"--::---"+total3TermAfterDisc);
			System.out.println("per sq ft 3 term.."+perSqFt+"--::---"+persqftRate3Term);
			System.out.println("per sq ft 3 term AfterDisc.."+perSqFtAfterDisc+"--::---"+persqftRate3TermAfterDisc);
				
			
			System.out.println("subTotalTerm5 is.."+subTotal5Term+"--::---"+subTotal5TermAfterDisc);
			System.out.println("totalTerm5AfterDisc is.."+total5Term+"--::---"+total5TermAfterDisc);
			System.out.println("totalTerm5AfterDisc is.."+persqftRate5Term+"--::---"+persqftRate5TermAfterDisc);
			
			if(term.equalsIgnoreCase("term-3")) {
				
				Assert.assertEquals(reg,  regFee3Term);
				Assert.assertEquals(cerf,  certFee3Term);
				Assert.assertEquals(pv,  pv3Term);
				Assert.assertEquals(regDisc,  regFee3TermDisc);
				Assert.assertEquals(cerfDisc,  certFee3TermDisc);
				Assert.assertEquals(pvDisc,  pv3TermDisc);
				
				Assert.assertEquals(subTotal,  subTotal3Term);
				Assert.assertEquals(subTotalAfterDisc,  subTotal3TermAfterDisc);
				Assert.assertEquals(totalAmount,  total3Term);
				Assert.assertEquals(totalAmountAfterDisc,  total3TermAfterDisc);
				Assert.assertEquals(perSqFt,  persqftRate3Term);
				Assert.assertEquals(perSqFtAfterDisc,  persqftRate3TermAfterDisc);
			}
			else if(term.equalsIgnoreCase("term-5")) {
				
				Assert.assertEquals(reg,  regFee5Term);
				Assert.assertEquals(cerf,  certFee5Term);
				Assert.assertEquals(pv,  pv5Term);
				Assert.assertEquals(regDisc,  regFee5TermDisc);
				Assert.assertEquals(cerfDisc,  certFee5TermDisc);
				Assert.assertEquals(pvDisc,  pv5TermDisc);
				
				Assert.assertEquals(subTotal,  subTotal5Term);
				Assert.assertEquals(subTotalAfterDisc,  subTotal5TermAfterDisc);
				Assert.assertEquals(totalAmount,  total5Term);
				Assert.assertEquals(totalAmountAfterDisc,  total5TermAfterDisc);
				Assert.assertEquals(perSqFt,  persqftRate5Term);
				Assert.assertEquals(perSqFtAfterDisc,  persqftRate5TermAfterDisc);
				
			}
			
		}
		
	

}
		
	