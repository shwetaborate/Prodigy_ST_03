Feature: SauceDemo Login Functionality
  This feature validates the login functionality of the SauceDemo website.

  # Positive Test Cases
  @positive
  Scenario: Login with Standard User Credentials
    Given I navigate to the SauceDemo login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the inventory page

  @positive
  Scenario: Login with Performance Glitch User
    Given I navigate to the SauceDemo login page
    When I enter username "performance_glitch_user" and password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the inventory page

  @positive
  Scenario: Login with Error User
    Given I navigate to the SauceDemo login page
    When I enter username "error_user" and password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the inventory page

  @positive
  Scenario: Login with Visual User
    Given I navigate to the SauceDemo login page
    When I enter username "visual_user" and password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the inventory page

  @positive
  Scenario: Login with Remember Me Functionality
    Given I navigate to the SauceDemo login page
    When I enter username "standard_user" and password "secret_sauce"
    And I check the "Remember me" checkbox
    And I click on the login button
    And I log out from the application
    When I revisit the login page
    Then the username field should be pre-populated with "standard_user"

  # Negative Test Cases
  @negative
  Scenario: Login with Locked Out User
    Given I navigate to the SauceDemo login page
    When I enter username "locked_out_user" and password "secret_sauce"
    And I click on the login button
    Then I should see an error message "Sorry, this user has been locked out."

  @negative
  Scenario: Login with Invalid Username
    Given I navigate to the SauceDemo login page
    When I enter username "invalid_user" and password "secret_sauce"
    And I click on the login button
    Then I should see an error message "Username and password do not match any user in this service."

  @negative
  Scenario: Login with Invalid Password
    Given I navigate to the SauceDemo login page
    When I enter username "standard_user" and password "wrong_password"
    And I click on the login button
    Then I should see an error message "Username and password do not match any user in this service."

  @negative
  Scenario: Login with Empty Username
    Given I navigate to the SauceDemo login page
    When I leave the username field empty
    And I enter password "secret_sauce"
    And I click on the login button
    Then I should see an error message "Username is required."

  @negative
  Scenario: Login with Empty Password
    Given I navigate to the SauceDemo login page
    When I enter username "standard_user"
    And I leave the password field empty
    And I click on the login button
    Then I should see an error message "Password is required."

  @negative
  Scenario: Login with Both Fields Empty
    Given I navigate to the SauceDemo login page
    When I leave the username field empty
    And I leave the password field empty
    And I click on the login button
    Then I should see an error message "Username is required."

  # Edge Cases and Security Tests
  @edge
  Scenario: Login with Extremely Long Username
    Given I navigate to the SauceDemo login page
    When I enter a username with 100 characters and password "secret_sauce"
    And I click on the login button
    Then I should see an error message about input length or invalid credentials

  @edge
  Scenario: Login with Extremely Long Password
    Given I navigate to the SauceDemo login page
    When I enter username "standard_user" and a password with 100 characters
    And I click on the login button
    Then I should see an error message about input length or invalid credentials

  @edge
  Scenario: Login with Special Characters in Username
    Given I navigate to the SauceDemo login page
    When I enter username "user!@#$%^&*()" and password "secret_sauce"
    And I click on the login button
    Then I should see an error message about invalid characters

  @edge
  Scenario: Login with Whitespace Only in Fields
    Given I navigate to the SauceDemo login page
    When I enter whitespace only in the username field
    And I enter whitespace only in the password field
    And I click on the login button
    Then I should see an error message "Username is required."

  @edge
  Scenario: Login Field Case Sensitivity
    Given I navigate to the SauceDemo login page
    When I enter username "STANDARD_USER" and password "SECRET_SAUCE"
    And I click on the login button
    Then I should see an error message "Username and password do not match any user in this service."

  @edge
  Scenario: Browser Back Button After Logout
    Given I successfully login with username "standard_user" and password "secret_sauce"
    And I navigate to several pages within the application
    And I log out from the application
    When I click the browser back button multiple times
    Then I should not be able to access protected content after logout
