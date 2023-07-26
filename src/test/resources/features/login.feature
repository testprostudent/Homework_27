Feature: Login feature

  Scenario: Login Success
    Given i open browser
    And i open login Page
    When i entre email "demo@classs.com"
    And i entre password "te$t$tudent"
    And i submit
    Then i am logged in
