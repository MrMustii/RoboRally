
@tag
Feature: setting up the game

  @tag1
  Scenario: set a number of players
    Given the user choses a number of players
    When creation instances of the number of players 
    Then the number of players should be set
		
		
		Scenario: next players turn
    Given that a player ends the turn
    When the game sets the next players turn
    Then the next player can draw cards
    
    Scenario: picking cards
    Given Players have drawn cards
    When the players pick thier cards
    Then the next player can pick cards
    
		Scenario: playing cards
    Given Players have picked cards 
    When cards are activated in order
    Then card is removed from the hand
    