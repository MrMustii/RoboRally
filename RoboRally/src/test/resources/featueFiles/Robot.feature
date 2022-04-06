@tag
Feature: Robot
		      
		  @tag1
			  Scenario: Damaging robot
			    Given A robot with 5 lives
			    When the robot takes 1 damage
			    Then it has 4 lives
			
		  @tag2
			  Scenario: Death of robot
			    Given A robot with 1 life and start position (1, 1, 1)
			    When the robot takes 1 damage
			    Then it respawns at the starting point (1, 1, 1)
			    And it has 5 lives

			@tag3
				Scenario Outline: Moving of robot
					Given a robot with a position with coordinates (3, 3) and orientation 0
					And a board without obstacles
					When the robot gets a new position <x>, <y>, <o> by moving or rotating
					Then the robot has the position <x>, <y>, <o>
					
					Examples:
					| x | y | o |
					| 3 | 4 | 0 |
					| 4 | 3 | 0 |
					| 3 | 2 | 0 |
					| 2 | 3 | 0 |
					| 3 | 5 | 0 |
					| 5 | 3 | 0 |
					| 3 | 1 | 0 |
					| 1 | 3 | 0 |
					| 3 | 6 | 0 |
					| 6 | 3 | 0 |
					| 3 | 0 | 0 |
					| 0 | 3 | 0 |
					| 3 | 3 | 1 |
					| 3 | 3 | 2 |
					| 3 | 3 | 3 |
					