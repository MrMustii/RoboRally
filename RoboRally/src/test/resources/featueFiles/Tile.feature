
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
      |"P "							|		5		  |		0	    |
      |"S "							|  	0			|		5			|
      |"E "							|   0			|		5	    |
      |"+ "							|  	-3		|		8			|
      |"W0"							|		0			|		5			|
      

