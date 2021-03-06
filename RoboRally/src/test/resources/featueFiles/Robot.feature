@tag
Feature: Robot
		      
		  @tag1
			  Scenario: Damaging robot
			    Given A robot with 5 lives
			    When the robot takes 1 damage
			    Then it has 4 lives
			    
			@tag2
			  Scenario: Damaging robot with shield
			    Given A robot with 5 lives
			    And it is shielded
			    When the robot takes 1 damage
			    Then it has 5 lives
			
		  @tag3
			  Scenario: Death of robot
			    Given A robot with 1 life and start position (1, 1, 1)
			    When the robot takes 1 damage
			    Then it is dead


			@tag4
			  Scenario: Rising of robot
			    Given A dead robot and start position (1, 1, 1)
			    When the robot respawns
			    Then it respawns at the starting point (1, 1, 1)
			    And it has 5 lives

			@tag5
				Scenario Outline: Moving of robot
					Given a robot with a position with coordinates (3, 3) and orientation 2
					And a board without obstacles
					When the robot gets a new position <x>, <y>, <o> by moving or rotating
					Then the robot has the position <x>, <y>, <o>
					
					Examples:
					| x | y | o |
					| 3 | 4 | 2 |
					| 4 | 3 | 2 |
					| 3 | 2 | 2 |
					| 2 | 3 | 2 |
					| 3 | 5 | 2 |
					| 5 | 3 | 2 |
					| 3 | 1 | 2 |
					| 1 | 3 | 2 |
					| 3 | 6 | 2 |
					| 6 | 3 | 2 |
					| 3 | 0 | 2 |
					| 0 | 3 | 2 |
					| 3 | 3 | 1 |
					| 3 | 3 | 2 |
					| 3 | 3 | 3 |
					
			@tag6
				Scenario Outline: Moving of robot with wall
					Given a robot with a position with coordinates (3, 3) and orientation 2
					And a board with a wall at position 3, 4, <wall_o>
					When the robot gets a new position 3, <robot_y>, 2 by moving or rotating
					Then the robot has the position 3, <final_y>, 2
				
					
					Examples:
					| wall_o | robot_y | final_y |
					| 0      | 4       | 3       |
					| 1      | 4       | 4       |
					| 2      | 4       | 4       |
					| 3      | 4       | 4       |
					| 0      | 5       | 3       |
					| 1      | 5       | 5       |
					| 2      | 5       | 4       |
					| 3      | 5       | 5       |
					
			@tag7
			  Scenario: the board knows that the robot moved
			    Given a robot with a position with coordinates (3, 3) and orientation 0
			    When the robot moves
			    Then the old tile is unoccupied 
			    And new tile is occupied

			
			@tag8
			Scenario: Not exceeding 5 health
			Given a robot with 5 lives that steps on a repair tile
			When the robot gets repaired
			Then the robot will still have 5 lives
			
			@tag9
			Scenario: Start position overriden interact method does not remove shielded status
				Given a robot that is shielded
				And a start position tile on position 1 and 1
				When the robot interacts with the start posoition tile on position 1 and 1
				Then shielded status is still true
				

			@tag10
				Scenario Outline: Moving of robot on a oil tile and changing direction of the move
					Given a robot with a position with coordinates (<robot_x>, <robot_y>) and orientation <robot_o>
					And a board with a oil tile at position 3, 3
					When the robot moves over oil tile to <final_x>, <final_y>, <robot_o> 
					Then the robot does not have the position <final_x>, <final_y>, <robot_o> 
		
					Examples:
					| robot_o | robot_x | robot_y | final_x | final_y |
					| 0       | 3       | 4       | 3       | 2       |
					| 1       | 2       | 3       | 4       | 3       |
					| 2       | 3       | 2       | 3       | 4       |
					| 3       | 4       | 3       | 2       | 3       |

					
					