Feature: As a user, I would like my phone to greet on command
  Scenario: A user wants to be greeted by name
    Given a user with name "Andrew"
    When the user clicks the greet button
    Then the app should greet the user by name
      |Hello, Andrew!|
      |Go away, Andrew!|

  Scenario: A user wants to greet no one in particular
    Given a user who has not provided a name
    When the user clicks the greet button
    Then the app should greet the entire world
      |Hello, World!|
      |Go away, World!|
