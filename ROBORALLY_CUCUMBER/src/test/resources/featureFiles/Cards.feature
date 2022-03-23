@tag
Feature: Drawing cards

  @tag1
  Scenario: drawing a card
    Given player draws a card
    Then determain that contant of the card
		And adds it to the hand

		
		Scenario: drawing a hand as a player
    Given player wants to draw random cards
    Then the player will discard his hand 
		And draws cards
		
		
