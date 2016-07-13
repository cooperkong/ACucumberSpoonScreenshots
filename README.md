# ACucumberSpoonScreenshots
Android automation tests using Cucumber and **Spoon reporting with SCREENSHOTS**.
* Sample application to demonstrate the ability to take screenshot during Cucumber autoamtion tests on Android using Spoon
* Also has ability to use tag for better management
* Image tag with scenario statements(eg, Then I will see text "<text>")

##Usage in feature
```python
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
```

##Screenshot of Spoon report
![screenshot](https://github.com/cooperkong/ACucumberSpoonScreenshots/blob/master/screenshot.png)
##Screenshot of Spoon report device Log page
![screenshot](https://github.com/cooperkong/ACucumberSpoonScreenshots/blob/master/screenshot2.png)
##To run
```gradle
./gradlew spoon
```
