
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
   
  @tag6
  Scenario: Player set robot 
  	Given a player
  	When the player sets a robot on the board 
  	Then the robot has a position 
   
  @tag7
  Scenario: Player uses a card on a robot
  	Given a player
  	And robot with position (3, 3, <start_o>)
  	When the player uses a <card>
  	Then the robot has position <x>, <y>, <o>
  	
  	Examples:
  	| start_o | card        | x | y | o |
  	| 0       | "forward 1" | 3 | 4 | 0 |
  	| 0       | "forward 2" | 3 | 5 | 0 |
  	| 0       | "forward 3" | 3 | 6 | 0 |
  	| 0       | "backward"  | 3 | 2 | 0 |
  	| 0       | "rotate_c"  | 3 | 3 | 1 |
  	| 0       | "rotate_cc" | 3 | 3 | 3 |
  	| 0       | "uturn"     | 3 | 3 | 2 |
  	| 1       | "forward 1" | 4 | 3 | 1 |
  	| 1       | "forward 2" | 5 | 3 | 1 |
  	| 1       | "forward 3" | 6 | 3 | 1 |
  	| 1       | "backward"  | 2 | 3 | 1 |
 		| 2       | "forward 1" | 3 | 2 | 2 |
  	| 2       | "forward 2" | 3 | 1 | 2 |
  	| 2       | "forward 3" | 3 | 0 | 2 |
  	| 2       | "backward"  | 3 | 4 | 2 |
 		| 3       | "forward 1" | 2 | 3 | 3 |
  	| 3       | "forward 2" | 1 | 3 | 3 |
  	| 3       | "forward 3" | 0 | 3 | 3 |
  	| 3       | "backward"  | 4 | 3 | 3 |


  @tag8
  Scenario: Player initialize robot 
  	Given a player
  	And there us a board
  	When a player initialize on the starting tile
  	Then the robot has a position of the starting tile
  	
