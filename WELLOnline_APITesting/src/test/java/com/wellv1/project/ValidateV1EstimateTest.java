package com.wellv1.project;

import java.io.IOException;

import org.testng.annotations.Test;

public class ValidateV1EstimateTest extends EstimateV1Validator {
	@Test
	public void validateNoDiscLessThan50k() throws IOException {
		validateEstimate(NODiscount, 10000, "nc");
		validateEstimate(NODiscount, 10000, "ci");
		validateEstimate(NODiscount, 10000, "cs");
		validateEstimate(NODiscount, 10000, "cics");
		validateEstimate(NODiscount, 10000, "residential");
	}
	@Test
	public void validateNoDiscLessThan5L() throws IOException {
	
		validateEstimate(NODiscount, 300000, "nc");
		validateEstimate(NODiscount, 300000, "ci");
		validateEstimate(NODiscount, 300000, "cs");
		validateEstimate(NODiscount, 300000, "cics");
		validateEstimate(NODiscount, 300000, "residential");
		
	}
	@Test
	public void validateNoDiscMoreThan5L() throws IOException {
		validateEstimate(NODiscount, 600000, "nc");
		validateEstimate(NODiscount, 600000, "ci");
		validateEstimate(NODiscount, 600000, "cs");
		validateEstimate(NODiscount, 600000, "cics");
		validateEstimate(NODiscount, 600000, "residential");
		
	}
	@Test
	public void validatePortfolioLessThan50K() throws IOException {
		
		validateEstimate(portfolio, 50000, "nc");
		validateEstimate(portfolio, 50000, "ci");
		validateEstimate(portfolio, 50000, "cs");
		validateEstimate(portfolio, 50000, "cics");
		validateEstimate(portfolio, 50000, "residential");
		
	}
	@Test
	public void validatePortfolioLessThan5L() throws IOException {
		
		validateEstimate(portfolio, 100000, "nc");
		validateEstimate(portfolio, 100000, "ci");
		validateEstimate(portfolio, 100000, "cs");
		validateEstimate(portfolio, 100000, "cics");
		validateEstimate(portfolio, 100000, "residential");
		
	}
	@Test
	public void validatePortfolioMoreThan5L() throws IOException {
		
		validateEstimate(portfolio, 600000, "nc");
		validateEstimate(portfolio, 600000, "ci");
		validateEstimate(portfolio, 600000, "cs");
		validateEstimate(portfolio, 600000, "cics");
		validateEstimate(portfolio, 600000, "residential");
		
	}
	@Test
	public void validateCornerStoneLessThan50K() throws IOException {
		
		validateEstimate(cornerstone, 50000, "nc");
		validateEstimate(cornerstone, 50000, "ci");
		validateEstimate(cornerstone, 50000, "cs");
		validateEstimate(cornerstone, 50000, "cics");
		validateEstimate(cornerstone, 50000, "residential");
		
	}
	
	@Test
	public void validateCornerStoneLessThan5L() throws IOException {
		
		validateEstimate(cornerstone, 100000, "nc");
		validateEstimate(cornerstone, 100000, "ci");
		validateEstimate(cornerstone, 100000, "cs");
		validateEstimate(cornerstone, 100000, "cics");
	validateEstimate(cornerstone, 100000, "residential");
	
		
	}
	@Test
	public void validateCornerStoneMoreThan5L() throws IOException {
	
	validateEstimate(cornerstone, 600000, "nc");
	validateEstimate(cornerstone, 600000, "ci");
	validateEstimate(cornerstone,600000, "cs");
	validateEstimate(cornerstone, 600000, "cics");
	validateEstimate(cornerstone, 600000, "residential");
	
	
	}
	@Test
	public void validateCountryLessThan50k() throws IOException {
		validateEstimate(country, 50000, "nc");
		validateEstimate(country, 50000, "ci");
		validateEstimate(country, 50000, "cs");
		validateEstimate(country, 50000, "cics");
		validateEstimate(country, 50000, "residential");
	}
	
	@Test
	public void validateCountryLessThan5L() throws IOException {
		validateEstimate(country, 100000, "nc");
		validateEstimate(country, 100000, "ci");
		validateEstimate(country, 100000, "cs");
		validateEstimate(country, 100000, "cics");
		validateEstimate(country, 100000, "residential");
	}
	
	@Test
	public void validateCountryMoreThan5L() throws IOException {
		validateEstimate(country, 600000, "nc");
		validateEstimate(country, 600000, "ci");
		validateEstimate(country, 600000, "cs");
		validateEstimate(country, 600000, "cics");
		validateEstimate(country, 600000, "residential");
	}
	
	@Test
	public void validateCountryPortfolioLessThan50k() throws IOException {
		validateEstimate(countryPortfolio, 50000, "nc");
		validateEstimate(countryPortfolio, 50000, "ci");
		validateEstimate(countryPortfolio, 50000, "cs");
		validateEstimate(countryPortfolio, 50000, "cics");
		validateEstimate(countryPortfolio, 50000, "residential");
		
	}
	
	@Test
	public void validateCountryPortfolioLessThan5L() throws IOException {
		validateEstimate(countryPortfolio, 100000, "nc");
		validateEstimate(countryPortfolio, 100000, "ci");
		validateEstimate(countryPortfolio, 100000, "cs");
		validateEstimate(countryPortfolio, 100000, "cics");
		validateEstimate(countryPortfolio, 100000, "residential");
		
	}
	@Test
	public void validateCountryPortfolioMoreThan5L() throws IOException {
		validateEstimate(countryPortfolio, 600000, "nc");
		validateEstimate(countryPortfolio, 600000, "ci");
		validateEstimate(countryPortfolio, 600000, "cs");
		validateEstimate(countryPortfolio, 600000, "cics");
		validateEstimate(countryPortfolio, 600000, "residential");
	
	}
	@Test
	public void validateCountryCornerLessThan50k() throws IOException {
		validateEstimate(countryAndcorner, 50000, "nc");
		validateEstimate(countryAndcorner, 50000, "ci");
		validateEstimate(countryAndcorner, 50000, "cs");
		validateEstimate(countryAndcorner, 50000, "cics");
		validateEstimate(countryAndcorner, 50000, "residential");
	}
	
	@Test
	public void validateCountryCornerLessThan5L() throws IOException {
		validateEstimate(countryAndcorner, 100000, "nc");
		validateEstimate(countryAndcorner, 100000, "ci");
		validateEstimate(countryAndcorner, 100000, "cs");
		validateEstimate(countryAndcorner, 100000, "cics");
		validateEstimate(countryAndcorner, 100000, "residential");
	}
	@Test
	public void validateCountryCornerMoreThan5L() throws IOException {
		validateEstimate(countryAndcorner, 600000, "nc");
		validateEstimate(countryAndcorner, 600000, "ci");
		validateEstimate(countryAndcorner, 600000, "cs");
		validateEstimate(countryAndcorner, 600000, "cics");
		validateEstimate(countryAndcorner, 600000, "residential");
	}
	
	



}
