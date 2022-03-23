@tag
Feature: Drawing cards

  @tag1
  Scenario: drawing a card
    Given it is players turn
    When player draws a card
    Then determain that contant of the card

		Scenario: drawing a hand as a player
    Given its players turn to draw
    When players can draw cards
		Then add them to his hand
		
		Scenario: player picks cards to play
    Given has a hand
    When he choses 5 cards to play
    Then he has an order of cards to play
		
		
		