
@tag
Feature: setting up the game

  @tag1
  Scenario: set a number of players
    Given the user choses a 3 players
    When creation instances of 3 players 
    Then the number of players should be set to 3
	 
	 @tag2
   Scenario: get the number of players
    Given the user choses a 3 players
    When The game wants to check the number of players for the phases
    Then the number of players should be returned to 3
	 
	 @tag3
   Scenario: Phase one
    Given that the players dont have full a hand
    And they have robots on the board
    When They draw thier hand, thier hand is showen and pick cards
    Then all the players have picked cards

	 @tag4
   Scenario: Phase two
    Given That the players have picked cards
    And they have robots on the board
    When robot has to move accordingly
    Then The robots have new psoitions
    
   @tag4
   Scenario: Phase two with a dead robot
    Given That the players have picked cards
		And they have robots on the board
    And they have a dead robot
    When robot has to move accordingly
    Then the living robot have new positions
    
	 @tag5
   Scenario: player has won
    Given robot is near end posistion 
    When robot moves to a end position
    Then player wins
	 
	  @tag6
 		Scenario: Player uses an oil spill card on a robot
  	And a clear board
  	And a player with robot with position (1, 1, <start_o>)
  	When the player uses an oil spill card
  	Then then there is an oil spill at <x>, <y>
  	Examples:
  	| start_o | x | y |
  	| 0       | 1 | 0 |
  	| 1       | 0 | 1 |
  	| 2       | 1 | 2 |
  	| 3       | 2 | 1 |
	 