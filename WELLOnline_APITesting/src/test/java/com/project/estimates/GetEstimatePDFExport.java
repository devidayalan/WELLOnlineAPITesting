package com.project.estimates;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.testng.annotations.Test;

import com.common.base.TestBase;
import com.common.utils.ExcelParserUtils;

@Test
public class GetEstimatePDFExport extends TestBase {
	
	public void getEstimatePDF() throws IOException {
		
		// create the folder structure if it does not exist
		File outputPath = new File(System.getProperty("user.dir")
				+ "/output");

		outputPath.mkdirs();
        String downloadFileName = "Estimate.pdf";
        File outputFile = new File(outputPath.getPath(), downloadFileName);
        if(outputFile.exists()) {
        	outputFile.delete();
        }
        
        estimateId = ExcelParserUtils.getSingleCellData(loginUserfile_path, estimateSheet, "Estimate ID", 2);
		res =
				given()
				.contentType("application/pdf")
				.accept("*/*")
				.pathParam("estimate_id", estimateId)
			       
				.when()
						.get("estimate/{estimate_id}/pdfExport")
				.then()
						.statusCode(STATUS_200)
						.extract().response();
		 byte[] bArray = res.body().asByteArray();

		OutputStream out = new FileOutputStream(outputFile);
		out.write(bArray);
		out.close();
		
	
	}

}
