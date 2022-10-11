$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/feature/IMPS/FundTransfer_IMPS_Feature.feature");
formatter.feature({
  "name": "RBL IMPS Feature File",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am requesting \"BaseURI\"",
  "keyword": "Given "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.i_am_requesting(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Load TestData Form Specific Api sheet \"Generate OTP\"",
  "keyword": "Given "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.load_TestData_Form_Specific_Api_sheet(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Load TestData Form Specific Api sheet \"Validate OTP\"",
  "keyword": "Given "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.load_TestData_Form_Specific_Api_sheet(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Base Test \"GenrateOtpBaseTestJson.json\" data Replace with RunTime Test Data\"GenrateOtpJsonTestData.json\"",
  "keyword": "Given "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.base_Test_data_Replace_with_RunTime_Test_Data(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "TC_001_IMPS_CIB",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@dummy"
    },
    {
      "name": "@Resource_Name_Vikash"
    }
  ]
});
formatter.step({
  "name": "Create Input File for json from Excel Sheet",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.create_Input_File_for_json_from_Excel_Sheet()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I go to \"/otp/api/v1/generateOtp\" and \"Post\" Api Request Saved in Json File \"GenrateOtpJsonTestData.json\"without charset",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.i_go_to_and_Api_Request_Saved_in_Json_File_without_charset(String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Save Final Response in \"JsonGenrateOtpResponse.json\" output File.",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.save_Final_Response_in_output_File(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Fetch OTP In Your Registered Mobile.",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.fetch_OTP_In_Your_Regesterd_Mobile()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Create Input File for ValidateOTP json from Excel Sheet",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.create_Input_File_for_ValidateOTP_json_from_Excel_Sheet()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I am validating object \"OTPGenerationResponse.ResponseHeader.Security.Token.PasswordToken.UserId\" for string value \"101220913\" For \"GenrateOTP\".",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.i_am_validating_object_for_string_value_For(String,String,String)"
});
formatter.result({
  "error_message": "com.jayway.restassured.path.json.exception.JsonPathException: Failed to parse the JSON document\r\n\tat com.jayway.restassured.path.json.JsonPath$ExceptionCatcher.invoke(JsonPath.java:928)\r\n\tat com.jayway.restassured.path.json.JsonPath$4.doParseWith(JsonPath.java:893)\r\n\tat com.jayway.restassured.path.json.JsonPath$JsonParser.parseWith(JsonPath.java:972)\r\n\tat com.jayway.restassured.path.json.JsonPath.get(JsonPath.java:201)\r\n\tat com.stepDefination.Common.IMPS_RTGS_NEFT_COMMON_Json.i_am_validating_object_for_string_value_For(IMPS_RTGS_NEFT_COMMON_Json.java:263)\r\n\tat ✽.I am validating object \"OTPGenerationResponse.ResponseHeader.Security.Token.PasswordToken.UserId\" for string value \"101220913\" For \"GenrateOTP\".(src/test/java/com/feature/IMPS/FundTransfer_IMPS_Feature.feature:17)\r\nCaused by: groovy.json.JsonException: Lexing failed on line: 1, column: 1, while reading \u0027E\u0027, no possible valid JSON value or punctuation could be recognized.\r\n\tat groovy.json.JsonLexer.nextToken(JsonLexer.java:87)\r\n\tat groovy.json.JsonLexer$nextToken.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:116)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:120)\r\n\tat com.jayway.restassured.internal.path.json.ConfigurableJsonSlurper.parse(ConfigurableJsonSlurper.groovy:96)\r\n\tat com.jayway.restassured.internal.path.json.ConfigurableJsonSlurper$parse.callCurrent(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:51)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:157)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:169)\r\n\tat com.jayway.restassured.internal.path.json.ConfigurableJsonSlurper.parseText(ConfigurableJsonSlurper.groovy:82)\r\n\tat com.jayway.restassured.path.json.JsonPath$4$1.method(JsonPath.java:891)\r\n\tat com.jayway.restassured.path.json.JsonPath$ExceptionCatcher.invoke(JsonPath.java:926)\r\n\tat com.jayway.restassured.path.json.JsonPath$4.doParseWith(JsonPath.java:893)\r\n\tat com.jayway.restassured.path.json.JsonPath$JsonParser.parseWith(JsonPath.java:972)\r\n\tat com.jayway.restassured.path.json.JsonPath.get(JsonPath.java:201)\r\n\tat com.stepDefination.Common.IMPS_RTGS_NEFT_COMMON_Json.i_am_validating_object_for_string_value_For(IMPS_RTGS_NEFT_COMMON_Json.java:263)\r\n\tat java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)\r\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:577)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\r\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:49)\r\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\r\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:63)\r\n\tat cucumber.runner.TestStep.run(TestStep.java:49)\r\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\r\n\tat cucumber.runner.TestCase.run(TestCase.java:44)\r\n\tat cucumber.runner.Runner.runPickle(Runner.java:40)\r\n\tat cucumber.api.testng.TestNGCucumberRunner.runScenario(TestNGCucumberRunner.java:68)\r\n\tat cucumber.api.testng.AbstractTestNGCucumberTests.runScenario(AbstractTestNGCucumberTests.java:22)\r\n\tat java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)\r\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:577)\r\n\tat org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:86)\r\n\tat org.testng.internal.Invoker.invokeMethod(Invoker.java:643)\r\n\tat org.testng.internal.Invoker.invokeTestMethod(Invoker.java:820)\r\n\tat org.testng.internal.Invoker.invokeTestMethods(Invoker.java:1128)\r\n\tat org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:129)\r\n\tat org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:112)\r\n\tat org.testng.TestRunner.privateRun(TestRunner.java:782)\r\n\tat org.testng.TestRunner.run(TestRunner.java:632)\r\n\tat org.testng.SuiteRunner.runTest(SuiteRunner.java:366)\r\n\tat org.testng.SuiteRunner.runSequentially(SuiteRunner.java:361)\r\n\tat org.testng.SuiteRunner.privateRun(SuiteRunner.java:319)\r\n\tat org.testng.SuiteRunner.run(SuiteRunner.java:268)\r\n\tat org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)\r\n\tat org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:86)\r\n\tat org.testng.TestNG.runSuitesSequentially(TestNG.java:1244)\r\n\tat org.testng.TestNG.runSuitesLocally(TestNG.java:1169)\r\n\tat org.testng.TestNG.run(TestNG.java:1064)\r\n\tat org.testng.remote.AbstractRemoteTestNG.run(AbstractRemoteTestNG.java:115)\r\n\tat org.testng.remote.RemoteTestNG.initAndRun(RemoteTestNG.java:251)\r\n\tat org.testng.remote.RemoteTestNG.main(RemoteTestNG.java:77)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I compare Json Schema file \"Json_Schema.json\" with Response File \"JsonGenrateOtpResponse.json\".",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.i_compare_Json_Response_file_with_Schema_File(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Save the Request \"GenrateOtpJsonTestData.json\" and Response \"JsonGenrateOtpResponse.json\".",
  "keyword": "Then "
});
formatter.match({
  "location": "IMPS_RTGS_NEFT_COMMON_Json.save_the_Request_and_Response(String,String)"
});
formatter.result({
  "status": "skipped"
});
});