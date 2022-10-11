Feature: RBL IMPS Feature File

  Background: 
    Given I am requesting "BaseURI"
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Load TestData Form Specific Api sheet "Validate OTP"
    Given Base Test "GenrateOtpBaseTestJson.json" data Replace with RunTime Test Data"GenrateOtpJsonTestData.json"

  #we want login
  @dummy @Resource_Name_Vikash
  Scenario: TC_001_IMPS_CIB
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "GenrateOtpJsonTestData.json"without charset
    Then Save Final Response in "JsonGenrateOtpResponse.json" output File.
    Then Fetch OTP In Your Registered Mobile.    
    Then Create Input File for ValidateOTP json from Excel Sheet
    #Then I am validating object "OTPGenerationResponse.ResponseHeader.Security.Token.PasswordToken.UserId" for string value "101220913" For "GenrateOTP".
    #Then I compare Json Schema file "Json_Schema.json" with Response File "JsonGenrateOtpResponse.json".
    #Then Save the Request "GenrateOtpJsonTestData.json" and Response "JsonGenrateOtpResponse.json".

