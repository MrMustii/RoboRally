@tag
Feature: Board

  @tag1
  Scenario: Board generation
    Given a board with 12 rows and 12 columns and 3 players
    When the user runs the game
    Then a board with 12 rows and 12 columns will be generated
   
  @tag2
  Scenario: set oil tile
    Given a board with 12 rows and 12 columns and 3 players
    And the board is empty
    When changeing a floor tile to oil tile
    Then the floor becomes oil