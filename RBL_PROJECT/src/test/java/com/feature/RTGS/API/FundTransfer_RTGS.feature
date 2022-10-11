@GenerateOTPRTGS @GenerateOTP
Feature: RBL RTGS Feature File

  Background: 
    Given I am requesting "BaseURI"
    
    
   #Create a valid request with valid value in "checksum" header in "RequestBody" Object and receive valid response and complete response schema validation.
  @RTGS_API_GOTP01_TC001 @Resource_Name_Vinayak
  Scenario: RTGS_API_GOTP01_TC001
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
    Then I compare Json Schema file "Json_GenrateOTP_Schema.json" with Response File "JsonGenrateOtpResponse.json".
    Then I wait for a minute 
    
 #Check that OTP messages are getting generated as per the RBL template for combinations of delivery flag  S and ServiceType flags  S
  @RTGS_API_GOTP02_TC003 @Resource_Name_Vinayak
  Scenario: RTGS_API_GOTP02_TC003
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
    Then I wait for a minute 
    
    
    
     #To check If invalid TokenID  entered in the Header 
    @Resource_Name_Vinayak @RTGS_API_GOTP11_TC046
    Scenario: RTGS_API_GOTP11_TC046
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
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request with Invalid APIKey and Token Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Verify Generate Otp 401 Unauthorized Status code.
    Then Save Final Response in "InvalidGenrateOTPResponse.json" output File.
    
    
    #To check if valid TokenID  and invalid  x-api-key entered in the Header 
    @Resource_Name_Vinayak @RTGS_API_GOTP11_TC047
     Scenario: RTGS_API_GOTP11_TC047
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
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request with Invalid APIKey Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Verify Generate Otp 403 Forbidden Status code.
    
    
    #To check that status code for invalid mobile no. 
    @RTGS_API_GOTP12_TC055 @Resource_Name_Vinayak
    Scenario: RTGS_API_GOTP12_TC055
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
    Then Verify Generate Otp 200 ok Status code.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "INVALID_MOBILE" For "Generate Otp".
    Then I am validating object "OTPGenerationResponse.Status.ErrorMessage" for string value "INVALID_MOBILE" For "Generate Otp".
    Then I am validating object "OTPGenerationResponse.Status.ErrorCode" for string value "010" For "Generate Otp".
     
    
    
   #Check that OTP messages are getting generated as per the RBL template for combinations of delivery flag  B and ServiceType flags  S 
   @Resource_Name_Vinayak @RTGS_API_GOTP02_TC005 @BS
  Scenario: RTGS_API_GOTP02_TC005
    Given Load TestData Form Specific Api sheet "GET TOKEN"
    Then Create Input File for Get OTP json from Excel Sheet
    Then I go to "/api/v1/OAUTH/get-token" and "Post" Api Request Saved in Json File "GetOtpJsonTestData.json"without charset
    Then Save Final Response in "GetOtpJsonResponse.json" output File.
    Then I am saving Bearer Token from response available on "generateTokenResponse.ResponseBody.generateTokenResponseBody.access_token"
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".
    Then I am validating object "generateTokenResponse.Status.DisplayText" for string value "Token Generated Successfully" For "GET TOKEN".
    Then I compare Json Schema file "GetTokenSchema.json" with Response File "GetOtpJsonResponse.json".
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "GenerateOtpBaseDeliveryFlagBS.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "SUCCESS" For "Generate Otp".
    Then Verify Generate Otp 200 ok Status code.
    Then Fetch OTP In Your Registered Mobile.
    Then Verify OTP mobile message is in RBI format In Your Registered Mobile.
    Then I wait for a minute 
    
    #Check that OTP messages are getting generated as per the RBL template for combinations of delivery flag  B and ServiceType flags  V
    @Resource_Name_Vinayak @RTGS_API_GOTP02_TC006 @BV
  Scenario: RTGS_API_GOTP02_TC006
    Given Load TestData Form Specific Api sheet "GET TOKEN"
    Then Create Input File for Get OTP json from Excel Sheet
    Then I go to "/api/v1/OAUTH/get-token" and "Post" Api Request Saved in Json File "GetOtpJsonTestData.json"without charset
    Then Save Final Response in "GetOtpJsonResponse.json" output File.
    Then I am saving Bearer Token from response available on "generateTokenResponse.ResponseBody.generateTokenResponseBody.access_token"
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".
    Then I am validating object "generateTokenResponse.Status.DisplayText" for string value "Token Generated Successfully" For "GET TOKEN".
    Then I compare Json Schema file "GetTokenSchema.json" with Response File "GetOtpJsonResponse.json".
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "GenerateOtpBaseDeliveryFlagBV.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "SUCCESS" For "Generate Otp".
    Then Verify Generate Otp 200 ok Status code.
    Then Fetch OTP In Your Registered Mobile.
    Then Verify OTP mobile message is in RBI format In Your Registered Mobile.
    Then I wait for a minute 
    
    
   #Check that if RequestID and UUID used in the request are unique
  @Resource_Name_Vinayak @RTGS_API_GOTP04_TC020
  Scenario: RTGS_API_GOTP04_TC020
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
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"with charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then I am validating object "OTPGenerationResponse.Status.DisplayText" for string value "DUPLICATE_REQID" For "Generate Otp".
    Then Verify Generate Otp 200 ok Status code.
    Then I wait for a minute 
    
    
   