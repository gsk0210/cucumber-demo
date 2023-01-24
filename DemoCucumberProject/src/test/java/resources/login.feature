Feature: Login

  @Login
  Scenario: Login using valid credentials
    Given I am on Login Page
    When I enter Email and password and hit submit button
    Then the user should be logged in

  @Login
    Scenario: Login using invalid credentials implementing Data Table
      Given I am on Login Page
      When I enter Email and password and hit submit button using data table
      |locked_out_user|secret_sauce|
      Then the user should not be logged in


      Scenario Outline: Login using valid credentials implementing multiple data set
        Given I am on Login Page
        When I enter email <User>
        And password and hit submit button multiple times <Password>
        Then the user should be logged in
        Examples:
          |User|Password|
        |standard_user|secret_sauce|
        |problem_user|secret_sauce|




