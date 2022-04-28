
@tag
Feature: Tiles

  @tag1
  Scenario Outline: Taking damage from a tile
    Given the robot has 5 lives and moves to a tile
    When the robot interacts with the <tile> with <damage>
    Then the robot takes damage and has <HP> left

    Examples: 
      |tile							|	damage	|		HP		|
      |"0 "							|		0			|		5			|
      |"A "							|		2			|		3	    |
      |"R "							|  	1			|		4			|
      |"P "							|		5	  	|		5   	|
      |"S "							|  	0			|		5			|
      |"E "							|   0			|		5	    |
      |"+ "							|  	-1		|		6			|
      |"B "							|		3			|		2			|
      |"W0"							|		0			|		5			|

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
	Scenario: Interacting with a tile getting pushed off a conveyer belt
		Given a robot with 5 lives
		And an empty board
		And a conveyer belt on position 1 and 1 with orientation 2
		When the robot interacts with the conveyer belt on position 1 and 1
		Then the robot will be pushed to position 1 and 2
		And the tile is a damaging tile with position 1 and 2
		Then the robot will recieve corresponding damage on position 1 and 2
		
		

		
	

