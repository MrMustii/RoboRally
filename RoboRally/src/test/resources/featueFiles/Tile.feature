
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
      |"P "							|		100	  |		-95   |
      |"S "							|  	0			|		5			|
      |"E "							|   0			|		5	    |
      |"+ "							|  	-3		|		8			|
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
	Scenario: Moving onto a conveyer belt
		Given a robot
		And an empty board
		And a series of conveyer belts on positions 1 and 1, 2 and 1, 3 and 1 and all have orientation 1
		When the robot interacts with the conveyor belt on position 1 and 1
		Then the robot will move down the series of conveyor belts and end on position 4 and 1
		
		
	@tag5
	Scenario: Interacting with a tile getting pushed off a conveyer belt
		Given a robot with 5 lives
		And an empty board
		And a conveyer belt on position 1 and 1 with orientation 1
		When the robot interacts with the conveyer belt on position 1 and 1
		Then the robot will be pushed to position 2 and 1
		And the tile is a damaging tile with position 2 and 1
		Then the robot will recieve corresponding damage on position 2 and 1
		
		
	@tag6
	Scenario: Getting pushed be a conveyor belt in other directions
		Given a robot
		And an empty board
		And a conveyer belts on position 1 and 1 with orientation 0 and position 1 and 0 with orientation 3, and position 0 and 0 with orientation 2
		When the robot interacts with the conveyor belt on position 1 and 1
		Then the robot will move to position 1 and 0
		
		
	

