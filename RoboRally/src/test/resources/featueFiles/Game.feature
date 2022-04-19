
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
    When They draw thier hand, thier hand is showen and pick cards
    Then all the players have picked cards

	 @tag4
   Scenario: Phase two
    Given That the players have picked cards
    And they have robots on the board
    When robot has to move accordingly
    Then The robots have new psoitions
    
#	 @tag5
#   Scenario: player has won
#    Given a player has a robot 
#    When robot moves to a end position
#    Then player wins


	 
	 #to do stories
	 ##getBoard

	 #to do methods
	 ##set board
	 ##has won
	 
	 