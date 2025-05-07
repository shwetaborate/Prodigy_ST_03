Feature: Sauce Demo Login Functionality
  As a user of the Sauce Demo website
  I want to be able to log in with valid credentials
  And be prevented from logging in with invalid credentials

  Background:
    Given the user is on the login page

  Scenario: Successful login with valid credentials
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should be logged in successfully

  Scenario: Failed login with locked out user
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then the user should see an error message containing "locked out"

  Scenario: Failed login with invalid username
    When the user logs in with username "invalid_user" and password "secret_sauce"
    Then the user should see an error message containing "Username and password do not match"

  Scenario: Failed login with invalid password
    When the user logs in with username "standard_user" and password "wrong_password"
    Then the user should see an error message containing "Username and password do not match"

  Scenario: Failed login with empty credentials
    When the user logs in with username "" and password ""
    Then the user should see an error message containing "Username is required"

  Scenario Outline: Login with different types of users
    When the user enters username "<username>"
    And the user enters password "<password>"
    And the user clicks the login button
    Then the user should <result>

    Examples:
      | username                | password      | result                                                       |
      | standard_user           | secret_sauce  | be logged in successfully                                    |
      | problem_user            | secret_sauce  | be logged in successfully                                    |
      | performance_glitch_user | secret_sauce  | be logged in successfully                                    |
      | locked_out_user         | secret_sauce  | see an error message containing "locked out"                 |
      | invalid_user            | secret_sauce  | see an error message containing "Username and password do not match" |
      | standard_user           | wrong_pass    | see an error message containing "Username and password do not match" |