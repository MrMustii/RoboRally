
@tag
Feature: Player
  @tag1
  Scenario: player has a hand
    Given a player with a hand
    When a player draws a card
    Then he has a card

  @tag2
  Scenario: Player draws a hand
    Given a player with a hand of no cards
    When a player draws a hand
    Then he has 9 cards
  @tag3
  Scenario: Player has a set cards in play
    Given a player with a hand of cards
    When picks the first cards to play
    Then he has 1 card in play
    
  @tag4
  Scenario: Player picks cards
    Given a player with a hand of cards
    When a player picks the first 5 cards in his hand to play
    Then a player has 5 cards picked to play
    
	@tag5
  Scenario: Player looks at his hand
    Given a player with a hand of cards
    When picks the first cards to play
    Then the game prints his hand
   
 
