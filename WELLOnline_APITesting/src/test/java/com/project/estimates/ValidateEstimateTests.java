package com.project.estimates;

import java.io.IOException;

import org.testng.annotations.Test;

public class ValidateEstimateTests extends EstimateValidator {
	//Total 60 testcases.
	//@Test
	public void validateNoDiscLessThan50() throws IOException {
		validateEstimate(NODiscount,10000,true);//well core
		validateEstimate(NODiscount,10000,false);//well certification
	}
	
	//@Test
	public void validateNoDiscLessThan150() throws IOException {
		validateEstimate(NODiscount,100000,true);
		validateEstimate(NODiscount,100000,false);
	}
	
	//@Test
	public void validateNoDiscLessThan250() throws IOException {
		validateEstimate(NODiscount,200000, true);
		validateEstimate(NODiscount,200000, false);
	}
	public void validateNoDiscLessThan500() throws IOException {
	validateEstimate(NODiscount,300000, true);
	validateEstimate(NODiscount,300000, false);
	}
	
	public void validateNoDiscLessThan750() throws IOException {
		validateEstimate(NODiscount,600000, true);
		validateEstimate(NODiscount,600000, false);
	}
	
	public void validateNoDiscLessThan999() throws IOException {
		validateEstimate(NODiscount,800000, true);
		validateEstimate(NODiscount,800000, false);
	}
	//Membership
		//Keystone
	
	@Test
	public void validatekeystoneLessThan50() throws IOException {
		validateEstimate(keystone,10000,true);// have issue with 3 term and 5 term (value differs in projects and summary)
		validateEstimate(keystone,10000,false);
		
	}
	
	public void validatekeystoneLessThan150() throws IOException {
		validateEstimate(keystone,100000,true);
		validateEstimate(keystone,100000,false);
	}
	
	public void validatekeystoneLessThan250() throws IOException {
		validateEstimate(keystone,200000, true);
		validateEstimate(keystone,200000, false);
	}
	
	public void validatekeystoneLessThan500() throws IOException {
		validateEstimate(keystone,300000, true);
		validateEstimate(keystone,300000, false);
	}
	
		//Cornerstone
	
	public void validatecornerstoneLessThan50() throws IOException {
		validateEstimate(cornerstone,10000, true);
		validateEstimate(cornerstone,10000, false);
	}
	
	public void validatecornerstoneLessThan150() throws IOException {
		validateEstimate(cornerstone,100000, true);
		validateEstimate(cornerstone,100000, false);
	}
	public void validatecornerstoneLessThan250() throws IOException {
		validateEstimate(cornerstone,200000, true);
		validateEstimate(cornerstone,200000, false);
	}
	
	public void validatecornerstoneLessThan500() throws IOException {
		validateEstimate(cornerstone,300000, true);
		validateEstimate(cornerstone,300000, false);
	}
	
		//portfolio
	
	public void validatePortfolioLessThan50() throws IOException {
		validateEstimate(portfolio,10000, true);
		validateEstimate(portfolio,10000, false);
	}
	
	public void validatePortfolioLessThan150() throws IOException {
		validateEstimate(portfolio,100000, true);
		validateEstimate(portfolio,100000, false);
	}
	public void validatePortfolioLessThan250() throws IOException {
		validateEstimate(portfolio,200000, true);
		validateEstimate(portfolio,200000, false);
	}
	
	public void validatePortfolioLessThan500() throws IOException {
		validateEstimate(portfolio,300000, true);
		validateEstimate(portfolio,300000, false);
	}
	
	//Country//11148
	
	public void validateCountryLessThan50() throws IOException {
		validateEstimate(country,10000, true);
		validateEstimate(country,10000, false);
	}
	//11129 well cert
	public void validateCountryLessThan150() throws IOException {
		validateEstimate(country,100000, true);
		validateEstimate(country,100000, false);
	}
	public void validateCountryLessThan250() throws IOException {
		validateEstimate(country,200000, true);
		validateEstimate(country,200000, false);
	}
	
	public void validateCountryLessThan500() throws IOException {
		validateEstimate(country,300000, true);
		validateEstimate(country,300000, false);
	}
	
	//sector ///11149
	public void validateSectorLessThan50() throws IOException {
		validateEstimate(sector,10000, true);
		validateEstimate(sector,10000, false);
	}
	
	public void validateSectorLessThan150() throws IOException {
		validateEstimate(sector,100000, true);
		validateEstimate(sector,100000, false);
	}
	public void validateSectorLessThan250() throws IOException {
		validateEstimate(sector,200000, true);
		validateEstimate(sector,200000, false);
	}
	
	public void validateSectorLessThan500() throws IOException {
		validateEstimate(sector,300000, true);
		validateEstimate(sector,300000, false);
	}

	//Membership and sector discount
		//cornerstone
	public void validateCornerAndSectorLessThan50() throws IOException {
		validateEstimate(cornerAndsector,10000, true);
		validateEstimate(cornerAndsector,10000, false);
	}
	
	public void validateCornerAndSectorLessThan150() throws IOException {
		validateEstimate(cornerAndsector,100000, true);
		validateEstimate(cornerAndsector,100000, false);
	}
	public void validateCornerAndSectorLessThan250() throws IOException {
		validateEstimate(cornerAndsector,200000, true);
		validateEstimate(cornerAndsector,200000, false);
	}
	
	public void validateCornerAndSectorLessThan500() throws IOException {
		validateEstimate(cornerAndsector,300000, true);
		validateEstimate(cornerAndsector,300000, false);
	}
	
	//Membership and sector discount
	//Keystone
	
	public void validateKeyAndSectorLessThan50() throws IOException {
		validateEstimate(keyAndsector,10000, true);
		validateEstimate(keyAndsector,10000, false);
	}
	
	public void validateKeyAndSectorLessThan150() throws IOException {
		validateEstimate(keyAndsector,100000, true);
		validateEstimate(keyAndsector,100000, false);
	}
	public void validateKeyAndSectorLessThan250() throws IOException {
		validateEstimate(keyAndsector,200000, true);
		validateEstimate(keyAndsector,200000, false);
	}
	
	public void validateKeyAndSectorLessThan500() throws IOException {
		validateEstimate(keyAndsector,300000, true);
		validateEstimate(keyAndsector,300000, false);
	}
	
	//Membership and sector discount
		//Portfolio
	
	public void validatePortfolioAndSectorLessThan50() throws IOException {
		validateEstimate(portfolioSector,10000, true);
		validateEstimate(portfolioSector,10000, false);
	}
	
	public void validatePortfolioAndSectorLessThan150() throws IOException {
		validateEstimate(portfolioSector,100000, true);
		validateEstimate(portfolioSector,100000, false);
	}
	public void validatePortfolioAndSectorLessThan250() throws IOException {
		validateEstimate(portfolioSector,200000, true);
		validateEstimate(portfolioSector,200000, false);
	}
	
	public void validatePortfolioAndSectorLessThan500() throws IOException {
		validateEstimate(portfolioSector,300000, true);
		validateEstimate(portfolioSector,300000, false);
	}
	
	//Country and Membership
	//cornerstone
	
	public void validateCornerAndCountryLessThan50() throws IOException {
		validateEstimate(countryAndcorner,10000, true);
		validateEstimate(countryAndcorner,10000, false);
	}
	
	public void validateCornerAndCountryLessThan150() throws IOException {
		validateEstimate(countryAndcorner,100000, true);
		validateEstimate(countryAndcorner,100000, false);
	}
	public void validateCornerAndCountryLessThan250() throws IOException {
		validateEstimate(countryAndcorner,200000, true);
		validateEstimate(countryAndcorner,200000, false);
	}
	
	public void validateCornerAndCountryLessThan500() throws IOException {
		validateEstimate(countryAndcorner,300000, true);
		validateEstimate(countryAndcorner,300000, false);
	}
	
	//Country and Membership
	//keystone 11136 core
	
	public void validateKeyAndCountryrLessThan50() throws IOException {
		validateEstimate(countryKey,10000, true);
		validateEstimate(countryKey,10000, false);
	}
	
	public void validateKeyAndCountryLessThan150() throws IOException {
		validateEstimate(countryKey,100000, true);
		validateEstimate(countryKey,100000, false);
	}
	public void validateKeyAndCountryLessThan250() throws IOException {
		validateEstimate(countryKey,200000, true);
		validateEstimate(countryKey,200000, false);
	}
	
	public void validateKeyAndCountryLessThan500() throws IOException {
		validateEstimate(countryKey,300000,true);
		validateEstimate(countryKey,300000,false);
	}
	
	//Country and Membership
	//portfoliostone
	
	public void validatePortfolioAndCountryLessThan50() throws IOException {
		validateEstimate(countryPortfolio,10000,true);
		validateEstimate(countryPortfolio,10000,false);
	}
	
	public void validatePortfolioAndCountryLessThan150() throws IOException {
		validateEstimate(countryPortfolio,100000,true);
		validateEstimate(countryPortfolio,100000,false);
	}
	public void validatePortfolioAndCountryLessThan250() throws IOException {
		validateEstimate(countryPortfolio,200000,true);
		validateEstimate(countryPortfolio,200000,false);
	}
	
	public void validatePortfolioAndCountryrLessThan500() throws IOException {
		validateEstimate(countryPortfolio,300000,true);
		validateEstimate(countryPortfolio,300000,false);
	}
	
	// Country membership and sector 
	//keystone
	
	public void validateSectorKeyAndCountryLessThan50() throws IOException {
		validateEstimate(countryKeySector,10000,true);
		validateEstimate(countryKeySector,10000,false);
	}
	
	public void validateSectorKeyAndCountryLessThan150() throws IOException {
		validateEstimate(countryKeySector,100000,true);
		validateEstimate(countryKeySector,100000,false);
	}
	public void validateSectorKeyAndCountryLessThan250() throws IOException {
		validateEstimate(countryKeySector,200000,true);
		validateEstimate(countryKeySector,200000,false);
	}
	
	public void validateSectorKeyAndCountryrLessThan500() throws IOException {
		validateEstimate(countryKeySector,300000,true);
		validateEstimate(countryKeySector,300000,false);
	}
	
	// Country membership and sector 
	//Cornerstone
	
	public void validateSectorCornerAndCountryLessThan50() throws IOException {
		validateEstimate(countryCornerSector,10000,true);
		validateEstimate(countryCornerSector,10000,false);
	}
	
	public void validateSectorCornerAndCountryLessThan150() throws IOException {
		validateEstimate(countryCornerSector,100000,true);
		validateEstimate(countryCornerSector,100000,false);
	}
	public void validateSectorCornerAndCountryLessThan250() throws IOException {
		validateEstimate(countryCornerSector,200000,true);
		validateEstimate(countryCornerSector,200000,false);
	}
	
	public void validateSectorCornerAndCountryrLessThan500() throws IOException {
		validateEstimate(countryCornerSector,300000,true);
		validateEstimate(countryCornerSector,300000,false);
	}
	
	// Country membership and sector 
	//Portfolio
	
	public void validateSectorPortfolioAndCountryLessThan50() throws IOException {
		validateEstimate(countryPortfolioSector,10000,true);
		validateEstimate(countryPortfolioSector,10000,false);
	}
	
	public void validateSectorPortfolioAndCountryLessThan150() throws IOException {
		validateEstimate(countryPortfolioSector,100000,true);
		validateEstimate(countryPortfolioSector,100000,false);
	}
	public void validateSectorPortfolioAndCountryLessThan250() throws IOException {
		validateEstimate(countryPortfolioSector,200000,true);
		validateEstimate(countryPortfolioSector,200000,false);
	}
	
	public void validateSectorPortfolioAndCountryrLessThan500() throws IOException {
		validateEstimate(countryPortfolioSector,300000,true);
		validateEstimate(countryPortfolioSector,300000,false);
	}
	

}
