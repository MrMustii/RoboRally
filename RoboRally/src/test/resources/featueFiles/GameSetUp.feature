
@tag
Feature: setting up the game

  @tag1
  Scenario: set a number of players
    Given the user choses a number of players
    When creation instances of the number of players 
    Then the number of players should be set


