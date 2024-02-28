Feature: userLogin


	@smoke
  Scenario: Invalid login
    Given the user on application
    When the user clicked on log in button
    And the user clicked on google button for login
    And the user switch the window
    When the user entered invalid user email
    And the user clicked on next button
    Then the user should see a error message
