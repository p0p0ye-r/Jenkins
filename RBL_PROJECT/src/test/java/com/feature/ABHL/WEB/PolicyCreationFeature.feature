Feature: dummy

  Background: 
    Given Load "ABHL_INSURANCE" TestData in Specific Excel Sheet"ABHL".
    When Navigate To Specific Portal : "WebURl"

  Scenario: ABHL01_DualUser_TC001
    Then Fill UserID and Password In "INSURANCE" TCSBanks
