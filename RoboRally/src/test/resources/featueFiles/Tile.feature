
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

	@tag2
	Scenario: Teleporting
		Given a robot
		And an empty board
		And two teleporter tiles at x- and y positions 1 and 1, and 4 and 4
		When the robot interacts with the teleporter tile at position 1 and 1
		Then the robot will teleport to the other teleporter at position 4 and 4

