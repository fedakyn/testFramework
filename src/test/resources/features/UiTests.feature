@UI
Feature: UI scenarios

  @SmokeTestUI
  Scenario: Register User
    Given I open the application
    When I navigate to the Signup and Login Page
    And I sign up as testUser
    And I complete the registration form
    Then I check that account created message is displayed
    When I continue after creating account
    Then I delete the created user

  @SmokeTestUI
  Scenario: Login User
    Given I open the application
    When I navigate to the Signup and Login Page
    And I log in
