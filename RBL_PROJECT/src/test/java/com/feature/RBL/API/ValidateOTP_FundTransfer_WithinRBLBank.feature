@ValidateOTP @ValidateOTPWITHRBL
Feature: RBL WithinRBLBank Validate otp Feature File

  Background: 
    Given I am requesting "BaseURI"



   #Create a valid request with valid value in "checksum" header in "RequestBody" Object and receive valid response and complete response schema validation.
   @Resource_Name_Vinayak @WithinRBLBank_API_VOTP01_TC001
  Scenario: WithinRBLBank_API_VOTP01_TC001
    Given Load TestData Form Specific Api sheet "GET TOKEN"
    Then Create Input File for Get OTP json from Excel Sheet
    Then I go to "/api/v1/OAUTH/get-token" and "Post" Api Request Saved in Json File "GetOtpJsonTestData.json"without charset
    Then Save Final Response in "GetOtpJsonResponse.json" output File.
    Then I am saving Bearer Token from response available on "generateTokenResponse.ResponseBody.generateTokenResponseBody.access_token"
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".
    Then I am validating object "generateTokenResponse.Status.DisplayText" for string value "Token Generated Successfully" For "GET TOKEN".
    Then I compare Json Schema file "GetTokenSchema.json" with Response File "GetOtpJsonResponse.json".
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "GenrateOtpBaseTestJson.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "SUCCESS" For "Generate Otp".
    Then Verify Generate Otp 200 ok Status code.
    Then Fetch OTP In Your Registered Mobile.
    Then Verify OTP mobile message is in RBI format In Your Registered Mobile. 
    Given Base Test "ValidateOtpBaseTestJson.json" data Replace with RunTime Test Data"ValidateOtpJsonTestData.json"
    Given Load TestData Form Specific Api sheet "Validate OTP"
    Then Create Input File for ValidateOTP json from Excel Sheet
    Then I go to "/otp/api/v1/ValidateOtp" and "Post" Api Request Saved in Json File "ValidateOtpJsonTestData.json"with charset
    Then Save Final Response in "ValidateOTPResponse.json" output File.
    Then I am validating object "OTPValidationResponse.Status.DisplayText" for string value "VALID" For "Validate OTP".
    Then I am validating object "OTPValidationResponse.Status.ErrorMessage" for string value "VALID" For "Validate OTP".
    Then I am validating object "OTPValidationResponse.Status.ErrorCode" for string value "000" For "Validate OTP".
    Then Verify Generate Otp 200 ok Status code.
    Then I compare Json Schema file "ValidateOTPJsonSchema.json" with Response File "ValidateOTPResponse.json".
    Then I wait for a minute 
    
    
    
    
    #Verify  OTP  gets success within allowed time i.e at 10 seconds
    @Resource_Name_Vinayak @WithinRBLBank_API_VOTP02_TC002 
  Scenario: WithinRBLBank_API_VOTP02_TC002
    Given Load TestData Form Specific Api sheet "GET TOKEN"
    Then Create Input File for Get OTP json from Excel Sheet
    Then I go to "/api/v1/OAUTH/get-token" and "Post" Api Request Saved in Json File "GetOtpJsonTestData.json"without charset
    Then Save Final Response in "GetOtpJsonResponse.json" output File.
    Then I am saving Bearer Token from response available on "generateTokenResponse.ResponseBody.generateTokenResponseBody.access_token"
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".
    Then I am validating object "generateTokenResponse.Status.DisplayText" for string value "Token Generated Successfully" For "GET TOKEN".
    Then I compare Json Schema file "GetTokenSchema.json" with Response File "GetOtpJsonResponse.json".
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "GenrateOtpBaseTestJson.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "SUCCESS" For "Generate Otp".
    Then Fetch OTP In Your Registered Mobile.
    Then Verify OTP mobile message is in RBI format In Your Registered Mobile. 
    Given Base Test "ValidateOtpBaseTestJson.json" data Replace with RunTime Test Data"ValidateOtpJsonTestData.json"
    Given Load TestData Form Specific Api sheet "Validate OTP"
    Then Create Input File for ValidateOTP json from Excel Sheet
    Then I want to wait for "20" Seconds.
    Then I go to "/otp/api/v1/ValidateOtp" and "Post" Api Request Saved in Json File "ValidateOtpJsonTestData.json"with charset
    Then Save Final Response in "ValidateOTPResponse.json" output File.
    Then I am validating object "OTPValidationResponse.Status.DisplayText" for string value "VALID" For "Validate OTP".
    Then I am validating object "OTPValidationResponse.Status.ErrorMessage" for string value "VALID" For "Validate OTP".
    Then I am validating object "OTPValidationResponse.Status.ErrorCode" for string value "000" For "Validate OTP".
    Then Verify Generate Otp 200 ok Status code.
    Then I wait for a minute 
    
    
    #To check after entering Invalid Token in the header
    @Resource_Name_Vinayak @WithinRBLBank_API_VOTP08_TC018 
  Scenario: WithinRBLBank_API_VOTP08_TC018
    Given Load TestData Form Specific Api sheet "GET TOKEN"
    Then Create Input File for Get OTP json from Excel Sheet
    Then I go to "/api/v1/OAUTH/get-token" and "Post" Api Request Saved in Json File "GetOtpJsonTestData.json"without charset
    Then Save Final Response in "GetOtpJsonResponse.json" output File.
    Then I am saving Bearer Token from response available on "generateTokenResponse.ResponseBody.generateTokenResponseBody.access_token"
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".
    Then I am validating object "generateTokenResponse.Status.DisplayText" for string value "Token Generated Successfully" For "GET TOKEN".
    Then I compare Json Schema file "GetTokenSchema.json" with Response File "GetOtpJsonResponse.json".
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "GenrateOtpBaseTestJson.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "SUCCESS" For "Generate Otp".
    Then Fetch OTP In Your Registered Mobile.
    Given Base Test "ValidateOtpBaseTestJson.json" data Replace with RunTime Test Data"ValidateOtpJsonTestData.json"
    Given Load TestData Form Specific Api sheet "Validate OTP"
    Then Create Input File for ValidateOTP json from Excel Sheet
    Then I go to "/otp/api/v1/ValidateOtp" and "Post" Api Request with Invalid APIKey and Token Saved in Json File "ValidateOtpJsonTestData.json"with charset
    Then Verify Generate Otp 401 Unauthorized Status code.
    
    
    #To check if valid TokenID  and invalid  x-api-key entered in the Header 
    @Resource_Name_Vinayak @WithinRBLBank_API_VOTP08_TC019 
  Scenario: WithinRBLBank_API_VOTP08_TC019
    Given Load TestData Form Specific Api sheet "GET TOKEN"
    Then Create Input File for Get OTP json from Excel Sheet
    Then I go to "/api/v1/OAUTH/get-token" and "Post" Api Request Saved in Json File "GetOtpJsonTestData.json"without charset
    Then Save Final Response in "GetOtpJsonResponse.json" output File.
    Then I am saving Bearer Token from response available on "generateTokenResponse.ResponseBody.generateTokenResponseBody.access_token"
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".
    Then I am validating object "generateTokenResponse.Status.DisplayText" for string value "Token Generated Successfully" For "GET TOKEN".
    Then I compare Json Schema file "GetTokenSchema.json" with Response File "GetOtpJsonResponse.json".
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "GenrateOtpBaseTestJson.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "SUCCESS" For "Generate Otp".
    Then Fetch OTP In Your Registered Mobile.
    Given Base Test "ValidateOtpBaseTestJson.json" data Replace with RunTime Test Data"ValidateOtpJsonTestData.json"
    Given Load TestData Form Specific Api sheet "Validate OTP"
    Then Create Input File for ValidateOTP json from Excel Sheet
    Then I go to "/otp/api/v1/ValidateOtp" and "Post" Api Request with Invalid APIKey Saved in Json File "ValidateOtpJsonTestData.json"with charset
    Then Verify Generate Otp 403 Forbidden Status code.
    
    #Verify  System gets error message if  OTP  is inserted after allowed time i.e at 101 seconds
    @Resource_Name_Vinayak @WithinRBLBank_API_VOTP02_TC004 
  Scenario: WithinRBLBank_API_VOTP02_TC004
    Given Load TestData Form Specific Api sheet "GET TOKEN"
    Then Create Input File for Get OTP json from Excel Sheet
    Then I go to "/api/v1/OAUTH/get-token" and "Post" Api Request Saved in Json File "GetOtpJsonTestData.json"without charset
    Then Save Final Response in "GetOtpJsonResponse.json" output File.
    Then I am saving Bearer Token from response available on "generateTokenResponse.ResponseBody.generateTokenResponseBody.access_token"
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".
    Then I am validating object "generateTokenResponse.Status.DisplayText" for string value "Token Generated Successfully" For "GET TOKEN".
    Then I compare Json Schema file "GetTokenSchema.json" with Response File "GetOtpJsonResponse.json".
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "GenrateOtpBaseTestJson.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "SUCCESS" For "Generate Otp".
    Then Fetch OTP In Your Registered Mobile.
    Given Base Test "ValidateOtpBaseTestJson.json" data Replace with RunTime Test Data"ValidateOtpJsonTestData.json"
    Given Load TestData Form Specific Api sheet "Validate OTP"
    Then Create Input File for ValidateOTP json from Excel Sheet        
    Then I go to "/otp/api/v1/ValidateOtp" and "Post" timeout Api Request Saved in Json File "ValidateOtpJsonTestData.json"with charset
    Then Verify Generate Otp 200 ok Status code.
    Then Save Final Response in "ValidateOTPResponse.json" output File.
    Then I am validating object "OTPValidationResponse.Status.DisplayText" for string value "TIMEOUT" For "Validate OTP".
    Then I am validating object "OTPValidationResponse.Status.ErrorMessage" for string value "TIMEOUT" For "Validate OTP".
    Then I am validating object "OTPValidationResponse.Status.ErrorCode" for string value "009" For "Validate OTP".
    
    
    
    
   