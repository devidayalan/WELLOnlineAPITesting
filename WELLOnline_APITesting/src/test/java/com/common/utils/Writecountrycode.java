package com.common.utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.common.base.TestBase;

@Test
public class Writecountrycode extends TestBase{
	
	public void writecountry() throws IOException {
		res =
				given()
				     
				.when()
						.get("getCountryWithCode")
				.then()
						.statusCode(STATUS_200)
						.log().body()
						.extract().response();
		List<String> jsonResponse = res.jsonPath().getList("$");
		System.out.println(jsonResponse.size());
		//[AF, AX, AL, DZ, AS, AD, AO, AI, AQ, AG, AR, AM, AW
		String code = res.jsonPath().getString("code");
		
		System.out.println(code);
		String country = res.jsonPath().getString("name");
		System.out.println(country);
		String id = res.jsonPath().getString("id");
		System.out.println(id);
		
		for (int i= 0; i<12; i++) {
			
			String cde = res.jsonPath().getString("country["+i+"]");
			System.out.println("cde is"+cde);
		//String cde = res.jsonPath().getString("code[1]");
			//ExcelParserUtils.setCellData(loginUserfile_path, estimateSheet, i+1, 2, cde);
			
		}
		
		//String code =  (res.path("code")).toString();
		//String country =  (res.path("name")).toString();
		//String id =  (res.path("id")).toString();
		//ExcelParserUtils.setCellData(loginUserfile_path, CountrySheet, 1, 0, code);
		//ExcelParserUtils.setCellData(loginUserfile_path, CountrySheet, 1, 1, code);
		//ExcelParserUtils.setCellData(loginUserfile_path, CountrySheet, 1, 2, code);
	}

	}


