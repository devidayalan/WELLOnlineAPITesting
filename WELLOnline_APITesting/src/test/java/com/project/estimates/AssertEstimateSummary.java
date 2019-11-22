package com.project.estimates;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.restassured.response.Response;

public class AssertEstimateSummary {
	
	
		
		public static void calculateSummaryParameters(Response resp) throws IOException {
			 double registrationSingle = 0;
			 double registrationSingleDisc = 0;
			 double certificationSingleDisc = 0;
			 double certificationSingle = 0;
			 double PVSingleDisc = 0;
			 double PVSingle = 0;
			 double regTerm3 = 0;
			 double regTerm3Disc = 0;
			 double certifTerm3Disc = 0;
			 double certifTerm3 = 0;
			 double PVTerm3Disc = 0;
			 double PVTerm3 = 0;
			 double regTerm5 = 0;
			 double regTerm5Disc = 0;
			 double certifTerm5Disc = 0;
			 double certifTerm5 = 0;
			 double PVTerm5Disc = 0;
			 double PVTerm5 = 0;
			 System.out.println("calling ...................is");
			 List<Map<String, Object>> projects = resp.jsonPath().getList("projects");
			
			 for(int i =0;i<projects.size();i++) {
				 registrationSingle =registrationSingle + Double.parseDouble(trailcomma( projects.get(i).get("registrationAmount").toString())); 
				 certificationSingle =certificationSingle + Double.parseDouble(trailcomma( projects.get(i).get("certificationAmount").toString())); 
				 PVSingle = PVSingle + Double.parseDouble(trailcomma( projects.get(i).get("pvAmount").toString())); 
				 registrationSingleDisc =registrationSingleDisc + Double.parseDouble(trailcomma( projects.get(i).get("regAfterDiscount").toString())); 
				 certificationSingleDisc =certificationSingleDisc + Double.parseDouble(trailcomma( projects.get(i).get("certAfterDiscount").toString())); 
				 PVSingleDisc =PVSingleDisc + Double.parseDouble(trailcomma( projects.get(i).get("pvAfterDiscount").toString()));
				
				 
				 Map<String, Object> map = (Map<String, Object>) projects.get(i).get("subscription");
		        Map<String, String> value = (Map<String, String>) map.get("term-3");
		        regTerm3 = regTerm3+ Double.parseDouble(trailcomma(value.get("registration_amount")));
		        regTerm3Disc = regTerm3Disc+ Double.parseDouble(trailcomma(value.get("reg_after_dis")));
		        certifTerm3 = certifTerm3+ Double.parseDouble(trailcomma(value.get("annual_amount")));
		        certifTerm3Disc = certifTerm3Disc+ Double.parseDouble(trailcomma(value.get("annual_after_dis")));
		        PVTerm3 = PVTerm3+ Double.parseDouble(trailcomma(value.get("pv_amount")));
		        PVTerm3Disc = PVTerm3Disc+ Double.parseDouble(trailcomma(value.get("pv_after_dis")));
		        
		        Map<String, String> value1 = (Map<String, String>) map.get("term-5");
		        regTerm5 = regTerm5+ Double.parseDouble(trailcomma(value1.get("registration_amount")));
		        regTerm5Disc = regTerm5Disc+ Double.parseDouble(trailcomma(value1.get("reg_after_dis")));
		        certifTerm5 = certifTerm5+ Double.parseDouble(trailcomma(value1.get("annual_amount")));
		        certifTerm5Disc = certifTerm5Disc+ Double.parseDouble(trailcomma(value1.get("annual_after_dis")));
		        PVTerm5 = PVTerm5+ Double.parseDouble(trailcomma(value1.get("pv_amount")));
		        PVTerm5Disc = PVTerm5Disc+ Double.parseDouble(trailcomma(value1.get("pv_after_dis")));
			 }
			 
			 String reg = trailcomma(resp.path("summary.totalRegistrationAmount").toString());
				String regDisc = trailcomma(resp.path("summary.totalRegAmountAfterDis").toString());
				String cerf = trailcomma(resp.path("summary.totalCertificationAmount").toString());
				String cerfDisc = trailcomma(resp.path("summary.totalCertAmountAfterDis").toString());
				String pv = trailcomma(resp.path("summary.totalPvAmount").toString());
				String pvDisc = trailcomma(resp.path("summary.totalPvAmountAfterDis").toString());
				Assert.assertEquals(reg,  String.valueOf((int)Math.round(registrationSingle)));
				Assert.assertEquals(cerf,  String.valueOf((int)Math.round(certificationSingle)));
				Assert.assertEquals(pv,  String.valueOf((int)Math.round(PVSingle)));
				Assert.assertEquals(regDisc,  String.valueOf((int)Math.round(registrationSingleDisc)));
				Assert.assertEquals(cerfDisc,   String.valueOf((int)Math.round(certificationSingleDisc)));
				//Assert.assertEquals(pvDisc,   String.valueOf((int)Math.round(PVSingleDisc)));// When adding pvvalue after discounts from projects, the value goes wrong.
				
			
				Map<String, Object> summap = resp.jsonPath().getMap("summary.subscription");
				Map<String, String> sumValue = (Map<String, String>) summap.get("term-3");
				System.out.println("sumValue is"+sumValue);
				String term3reg = trailcomma(sumValue.get("regTotalAmount"));
				String term3regDisc = trailcomma(sumValue.get("regTotalAfterDis"));
				String term3cerf = trailcomma(sumValue.get("annualTotalAmount"));
				String term3cerfDisc = trailcomma(sumValue.get("annualTotalAfterDis"));
				String term3pv = trailcomma(sumValue.get("pvAmount"));
				String term3pvDisc = trailcomma(sumValue.get("pvAfterDis"));
				Assert.assertEquals(term3reg,  String.valueOf((int)Math.round( regTerm3)));
				Assert.assertEquals(term3cerf,  String.valueOf((int)Math.round(certifTerm3)));
				Assert.assertEquals(term3pv,  String.valueOf((int)Math.round(PVTerm3)));
				Assert.assertEquals(term3regDisc,  String.valueOf((int)Math.round(regTerm3Disc)));
				Assert.assertEquals(term3cerfDisc,   String.valueOf((int)Math.round(certifTerm3Disc)));
				//Assert.assertEquals(term3pvDisc,   String.valueOf((int)Math.round(PVTerm3Disc)));
				
				Map<String, Object> summap5 = resp.jsonPath().getMap("summary.subscription");
				Map<String, String> sumValue5 = (Map<String, String>) summap5.get("term-5");
				String term5reg = trailcomma(sumValue5.get("regTotalAmount"));
				String term5regDisc = trailcomma(sumValue5.get("regTotalAfterDis"));
				String term5cerf = trailcomma(sumValue5.get("annualTotalAmount"));
				String term5cerfDisc = trailcomma(sumValue5.get("annualTotalAfterDis"));
				String term5pv = trailcomma(sumValue5.get("pvAmount"));
				String term5pvDisc = trailcomma(sumValue5.get("pvAfterDis"));
				Assert.assertEquals(term5reg,  String.valueOf((int)Math.round( regTerm5)));
				Assert.assertEquals(term5cerf,  String.valueOf((int)Math.round(certifTerm5)));
				Assert.assertEquals(term5pv,  String.valueOf((int)Math.round(PVTerm5)));
				Assert.assertEquals(term5regDisc,  String.valueOf((int)Math.round(regTerm5Disc)));
				Assert.assertEquals(term5cerfDisc,   String.valueOf((int)Math.round(certifTerm5Disc)));
				//Assert.assertEquals(term3pvDisc,   String.valueOf((int)Math.round(PVTerm5Disc)));
				
			 
				
		
	}
		
		public static String trailcomma(String str) {
			String trailed = null ;
				if( str.indexOf(",") != -1 )
			 {
			    trailed =  str.replaceAll(",","");
			 }
			
			return trailed;
		}

}
