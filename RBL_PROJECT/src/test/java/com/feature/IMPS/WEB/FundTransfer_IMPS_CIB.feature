Feature: Dual user Imps Corporate internet banking TestCase

  Background: 
    Given Load "IMPS CIB" TestData in Specific Excel Sheet"IMPS_CIB".
    When Navigate To Specific Portal : "WebURl"

  @T1345 @ResourceName_Vikash @High 
  Scenario: NEFT01_DualUser_TC001
    Then Enter User name in Corporate Internet Banking Page
    Then Click on "Next" button in Corporate Internet Banking LoginPage.
    #Then Click On "I confirm the image and phrase displayed matched my initial selection." CheckBox.
    #Then Enter Password in Corporate Internet Banking Page.
    #Then Click on "Login"  button in Corporate Internet Banking PasswordPage.
    #Then Click on "IMPS" Feild On Quick Links
    #Then Fill All Iniated "IMPS" Payement In Corporate Internet Banking fund Transfer.
    #Then Click on "Continue" button in Corporate Internet Banking LoginPage.

  @T1346 @ResourceName_Vikash @High
  Scenario: NEFT01_DualUser_TC002
    Then Enter User name in Corporate Internet Banking Page
    Then Click on "Next" button in Corporate Internet Banking LoginPage.
    Then Click On "I confirm the image and phrase displayed matched my initial selection." CheckBox.
    Then Enter Password in Corporate Internet Banking Page.
    Then Click on "Login"  button in Corporate Internet Banking PasswordPage.
    Then Click on "IMPS" Feild On Quick Links
    Then Fill All Iniated "IMPS" Payement In Corporate Internet Banking fund Transfer.
    Then Click on "Continue" button in Corporate Internet Banking LoginPage.
    Then Verify Error Message"Select beneficiary for the selected beneficiary type." in Trasfer Forn In Payment Initiate Page.

  @T1347 @ResourceName_Vikash @High
  Scenario: RTGS01_DualUser_TC003
    Then Enter User name in Corporate Internet Banking Page
    Then Click on "Next" button in Corporate Internet Banking LoginPage.
    Then Click On "I confirm the image and phrase displayed matched my initial selection." CheckBox.
    Then Enter Password in Corporate Internet Banking Page.
    Then Click on "Login"  button in Corporate Internet Banking PasswordPage.
    Then Verify HomePage Name "vicky rajput" in Rbl Home Page

  @T1348 @ResourceName_Vikash @High
  Scenario: RTGS01_DualUser_TC004
    Then Enter User name in Corporate Internet Banking Page
    Then Click on "Next" button in Corporate Internet Banking LoginPage.
    Then Click On "I confirm the image and phrase displayed matched my initial selection." CheckBox.
    Then Enter Password in Corporate Internet Banking Page.
    Then Click on "Login"  button in Corporate Internet Banking PasswordPage.
    Then Click on "IMPS" Feild On Quick Links
    Then Fill All Iniated "IMPS" Payement In Corporate Internet Banking fund Transfer.
    Then Click on "Continue" button in Corporate Internet Banking LoginPage.
    Then Verify Error Message"Select beneficiary for the selected beneficiary ty." in Trasfer Forn In Payment Initiate Page.
