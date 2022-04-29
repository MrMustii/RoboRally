
@tag
Feature: setting up the game

  @tag1
  Scenario: set a number of players
    Given there is no game
    When creation instances of 3 players 
    Then the number of players should be set to 3
	 
	 @tag2
   Scenario: get the number of players
    Given creation instances of 3 players 
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
    Given That the players have picked 2 card
		And they have robots on the board
    And they have a dead robot
    When robot has to move accordingly
    Then the living robot have new positions
    And dead robot does not move
    
	 @tag5
   Scenario: player has won
    Given robot is near end posistion 
    When robot moves to a end position
    Then player wins
	 
   @tag6
   Scenario: players unshielded
    Given players with shielded robots
    When phase 1 starts
    Then the robot are unshielded
			
		@tag7
		Scenario: pushing robots
		Given a robot with a position with coordinates <a>, <b> and orientation <c>
		And a clear board
		And another robot with a position with coordinates (<x>, <y>) and orientation <z>
		When the robot moves to <anew>, <bnew>, <cnew> 
		Then one robot is pushed  <anew>, <bnew>, <cnew> and <xnew>, <ynew>, <znew>
	
					Examples:
					| a | b | c | x | y | z | anew | bnew | cnew | xnew | ynew | znew |
					| 3 | 3 | 1 | 4 | 3 | 1 | 4    | 3    | 1    | 5    | 3    | 1    |
					| 3 | 3 | 1 | 4 | 3 | 1 | 5    | 3    | 1    | 6    | 3    | 1    |
					| 3 | 3 | 2 | 3 | 4 | 1 | 3    | 4    | 2    | 3    | 5    | 1    |
					| 3 | 3 | 2 | 3 | 4 | 1 | 3    | 5    | 2    | 3    | 6    | 1    |
					
					| 3 | 3 | 3 | 2 | 3 | 3 | 2    | 3    | 3    | 1    | 3    | 3    |
					| 3 | 3 | 0 | 3 | 4 | 1 | 3    | 4    | 0    | 3    | 5    | 1    |
		
		@tag8
		Scenario: pushing robots into boundry
		Given a robot with a position with coordinates <a>, <b> and orientation <c>
		And another robot with a position with coordinates (<x>, <y>) and orientation <z>
		When the robot moves to 3, 11, 1 
		Then one robot is pushed  <anew>, <bnew>, <cnew> and <xnew>, <ynew>, <znew>				
					
		Examples:
					| a | b | c | x | y | z | anew | bnew | cnew | xnew | ynew | znew |			
					| 3 | 10| 1 | 3 | 11| 1 | 3    | 10   | 1    | 3   | 11    | 1    |
					
		@tag9
		Scenario: pushing robot into another robot
		Given a robot with a position with coordinates 3, 3 and orientation 2
		And another robot with a position with coordinates (3, 4) and orientation 3
		And a third robot with a position with coordinates (3, 5) and orientation 0
		When the robot moves to 3, 4, 2 
		Then then the new positions for all robots are (3, 4, 2), (3, 5, 3), and (3, 6, 0) 
					
		@tag10
		Scenario: pushing robot into a wall
		Given a robot with a position with coordinates 3, 3 and orientation 2
		And another robot with a position with coordinates (3, 4) and orientation 3
		And setting a wall at 3, 4, 0
		When the robot moves to 3, 4, 2 
		Then then the new positions for all robots are (3, 3, 2), (3, 4, 3)
					
					