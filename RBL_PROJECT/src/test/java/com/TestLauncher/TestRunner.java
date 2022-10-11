package com.TestLauncher;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.generics.CustomReporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@Listeners(CustomReporter.class)
@CucumberOptions(features = ("src\\test\\java\\com\\feature\\IMPS\\WEB\\FundTransfer_IMPS_CIB.feature"),
                 glue = { "com.stepDefination" },   
//            tags = {"@T1345"},              
                 plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				            "json:target/custom-reports/cucumber-reports/cucumber-jsonreports.json",
				            "junit:target/custom-reports/cucumber-reports/cucumber-junitreports.xml",
				            "rerun:rerun/failed_scenarios.txt" 
				}
//                , monochrome = true	
//            , dryRun = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
	 @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
}