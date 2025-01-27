@UI
Feature: UI scenarios


  Scenario: Register User
    Given I open the application
    And I click on signup login
    And I input the signup name testUser
    And I input the signupEmail
    And I press signup
    And I complete all the fields for registering
    Then I check that account created message is displayed
    And I close the browser