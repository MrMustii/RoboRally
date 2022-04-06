@tag
Feature: Board

  @tag1
  Scenario: Board generation
    Given a board with 12 rows and 12 columns and 3 players
    When the user runs the game
    Then a board with 12 rows and 12 columns will be generated