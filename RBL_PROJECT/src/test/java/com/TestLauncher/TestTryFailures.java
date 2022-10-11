package com.TestLauncher;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
	        features = {"@rerun/failed_scenarios.txt"}, 
	        monochrome = true, 
	        plugin = {
	        		"html:target/custom-reports/cucumber-htmlreports",
					},
	        glue = {"com.stepDefination.Common"}
	        )
	public class TestTryFailures extends AbstractTestNGCucumberTests {
	
	}