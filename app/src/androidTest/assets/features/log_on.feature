@sign_in
Feature: Perform sign in

  @screenshot
  Scenario Outline: Sign in with wrong credentials
    Given I am on login screen
    And I enter email using "<email>"
    When I press the sign in button
    Then I will see the error text "This email address is invalid"
    Examples:
      | email     |
      | abc.com   |

  @screenshot
  Scenario Outline: Sign in with correct credentials
    Given I am on login screen
    And I enter email using "<email>"
    And I enter password using "<password>"
    When I press the sign in button
    Then I will see the text "Sign in success!"
    Examples:
      | email             | password |
      | foo@example.com   | hello    |
