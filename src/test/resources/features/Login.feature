Feature: Login feature

  Scenario: Successful Login
    Given I open the login page
    When I enter a email address "randy.ramos@testpro.io"
    And I enter a password "te$t$tudent"
    And I click submit
    Then I am logged in