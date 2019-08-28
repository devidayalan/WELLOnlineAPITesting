package com.common.base;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.common.utils.ExcelParserUtils;

public class CustomDataProvider extends TestBase {
	

	@DataProvider (name="ValidData")
	public Object [] [] readExcel() throws IOException {

	int rownum = ExcelParserUtils.getRowCount(loginUserfile_path, sheetName);
	int colCount = ExcelParserUtils.getCellCount(loginUserfile_path, sheetName, 1);
	String data[][] = new String [rownum][colCount];
	for(int i=1;i<=rownum;i++) {
		for(int j=0; j<colCount;j++) {
			data[i-1][j]= ExcelParserUtils.getCellData(loginUserfile_path, sheetName, i, j);
		}
	}
	
	return data;
	}
	//@DataProvider (name="InvalidData")
		public Object [] [] readInvalidExcel() throws IOException {

		int rownum = ExcelParserUtils.getRowCount(loginUserfile_path, LOGIN_INVALID_SHEET);
		int colCount = ExcelParserUtils.getCellCount(loginUserfile_path, LOGIN_INVALID_SHEET, 1);
		String data[][] = new String [rownum][colCount];
		for(int i=1;i<=rownum;i++) {
			for(int j=0; j<colCount;j++) {
				data[i-1][j]= ExcelParserUtils.getCellData(loginUserfile_path, LOGIN_INVALID_SHEET, i, j);
			}
		}
		
		return data;
		}
		@DataProvider (name="UserData")
		public Object [] [] readUserExcel() throws IOException {

		int rownum = ExcelParserUtils.getRowCount(loginUserfile_path, existngUserSheet);
		int colCount = ExcelParserUtils.getCellCount(loginUserfile_path, existngUserSheet, 1);
		String data[][] = new String [rownum][colCount];
		for(int i=1;i<=rownum;i++) {
			for(int j=0; j<colCount;j++) {
				data[i-1][j]= ExcelParserUtils.getCellData(loginUserfile_path, existngUserSheet, i, j);
			}
		}
		
		return data;
		}
		@DataProvider (name="verifyTokenData")
		public Object [] [] readTokenExcel() throws IOException {

		int rownum = ExcelParserUtils.getRowCount(loginUserfile_path, tokenSheet);
		int colCount = ExcelParserUtils.getCellCount(loginUserfile_path, tokenSheet, 1);
		String data[][] = new String [rownum][colCount];
		for(int i=1;i<=rownum;i++) {
			for(int j=0; j<colCount;j++) {
				data[i-1][j]= ExcelParserUtils.getCellData(loginUserfile_path, tokenSheet, i, j);
			}
		}
		
		return data;
		}
		
		
		@DataProvider (name="emailId")
		public Object [] [] readEmailExcel() throws IOException {

		int rownum = ExcelParserUtils.getRowCount(loginUserfile_path, emailSheet);
		int colCount = ExcelParserUtils.getCellCount(loginUserfile_path, emailSheet, 1);
		String data[][] = new String [rownum][colCount];
		for(int i=1;i<=rownum;i++) {
			for(int j=0; j<colCount;j++) {
				data[i-1][j]= ExcelParserUtils.getCellData(loginUserfile_path, emailSheet, i, j);
			}
		}
		
		return data;
		}


}
