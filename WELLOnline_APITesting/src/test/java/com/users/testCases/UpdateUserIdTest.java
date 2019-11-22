package com.users.testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;
import com.common.utils.GenerateRandomTestDataUtils;



public class UpdateUserIdTest extends TestBase {
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateUserIdInfo() throws IOException {
		userid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "Userid", 2));
		System.out.println("userid is"+userid);

		HashMap<String,String> parametr = new HashMap<>();
		 parametr.put("name", GenerateRandomTestDataUtils.getFirstName());
		 parametr.put("email", ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email", 2));
		 parametr.put("last_name", GenerateRandomTestDataUtils.getLastName());
		 parametr.put("roles[0]", ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "role", 2));
		 parametr.put("id",ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "userid", 2));
		res = given()
					.header("Authorization", header)
				   .pathParam("user_id", userid)
				   .param("id", 38880) 
				   .formParams(parametr)
				.when()
						.post("user/update/{user_id}")
				.then()
						.statusCode(STATUS_200)
						.assertThat().body("id", Is.is(userid))
						.log().all()
						.extract().response();
				
						
	}
	@Test
	public void updateUserIdOptionalInfo() throws NumberFormatException, IOException {
		
		HashMap<String,String> optional = new HashMap<>();
		 userid = Integer.parseInt(ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "Userid", 2));
			optional.put("name","Test");
			optional.put("email",ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "email", 2));
			optional.put("last_name",GenerateRandomTestDataUtils.getLastName());
			optional.put("organization",GenerateRandomTestDataUtils.getOrganization());
			optional.put("profile_pic", "https://well-v2-dev.s3.amazonaws.com/user/profile/wellv2testuser%40gmail.com89ae846b-b309-4159-99eb-6c2c1a6c9edd~wMkXg-_~_-15054701.jpeg");
			optional.put( "industry", null);
			optional.put("job_title", "Test");
			optional.put(  "languages[0]", "English");
			optional.put( "dob", "1990-05-08");
			optional.put( "gender", "Female");
			optional.put("website", GenerateRandomTestDataUtils.getEmail());
			optional.put(  "twitter", null);
			optional.put(   "gplus", null);
			optional.put(   "facebook", null);
			optional.put(  "linkedin", null);
			optional.put(  "roles[0]", ExcelParserUtils.getSingleCellData(loginUserfile_path, UsersSheet, "role", 2));
			optional.put(  "phone_number", "8234729387");
			optional.put(	 "well_advisory", null);
			res = given()

					   .header("Authorization", header)
					   .pathParam("user_id", userid)
				       .param("id", 38880) 
				       .formParams(optional)
				      
					.when()
							.post("user/update/{user_id}")
					.then()
							.statusCode(STATUS_200)
							.assertThat().body("id", Is.is(userid))
							.log().all()
							.extract().response();
				 

		
	}

}
