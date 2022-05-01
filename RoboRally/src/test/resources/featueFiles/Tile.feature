
@tag
Feature: Tiles

  @tag1
  Scenario Outline: Taking damage from a tile
    Given the robot has 5 lives and moves to a tile
    When the robot interacts with the tile with <damage>
    Then the robot takes damage and has <HP> left

    Examples: 
      |	damage	|		HP		|
      |		0			|		5			|
      |		2			|		3	    |
      |  	1			|		4			|
      |		5	  	|		5   	|
      |  	0			|		5			|
      |   0			|		5	    |
      |  	-1		|		6			|
      |		3			|		2			|
      |		0			|		5			|

  @tag2
  Scenario Outline: oil spill rotation
    Given the robot has 5 lives and moves to a tile
    When the robot interacts with the oil tile
    Then the oriantation changes

	@tag3
	Scenario: Teleporting
		Given a robot
		And an empty board
		And two teleporter tiles at x- and y positions 1 and 1, and 4 and 4
		When the robot interacts with the teleporter tile at position 1 and 1
		Then the robot will teleport to the other teleporter at position 4 and 4
	
	@tag4
		Scenario: Teleporting to T1
		Given a robot
		And an empty board
		And two new teleporter tiles at x- and y positions 2 and 2, and 5 and 5
		When the robot interacts with the new teleporter tile at position 2 and 2
		Then the robot will teleport to the other new teleporter at position 5 and 5
	
		
	@tag5
	Scenario: Moving onto a conveyer belt
		Given a robot
		And an empty board
		And a series of conveyer belts on positions 1 and 1, 1 and 2, 1 and 3 and all have orientation 2
		When the robot interacts with the conveyor belt on position 1 and 1
		Then the robot will move down the series of conveyor belts and end on position 1 and 4
		
		
	@tag6
	Scenario: Interacting with a tile getting pushed off a conveyer belt, and other orientation conveyor belt
		Given two robots with 5 lives
		And an empty board
		And two conveyer belts on position 1 and 1  and 3 and 3 with orientation 2 and 0
		When the robots interacts with the conveyer belts on position 1 and 1 and 3 and 3
		Then the robot will be pushed to position 1 and 2 and 3 and 2
		And the tile is a damaging tile with position 1 and 2
		Then the robot will recieve corresponding damage on position 1 and 2
		
	@tag7
	Scenario: Interacting with a repair kit
	Given a robot on position with 3 lives
	And an empty board 
	And a repair kit on position 1 and 2
	When the robot interacts with the repair kit on position 1 and 2
	Then the robot should have 4 lives
	
	@tag8
	Scenario: Moving onto a laser shooter tile
	Given a robot on position 1 and 1 with orienation 1
	And an empty board
	And a laser shooter tile on position 2 and 1 with orientation 2
	When the robot tries to move onto the laser shooter tile
	Then the robot will stay at the same position 1 and 1
	
	@tag9
	Scenario: Getting pushed be a conveyor belt in other directions
		Given a robot
		And an empty board
		And conveyer belts on position 1 and 1 with orientation 1 and position 2 and 1 with orientation 0, and position 2 and 0 with orientation 3
		When the robot interacts with the conveyor belt on position 1 and 1
		Then the robot will move to position 1 and 0
		

		

		
	

