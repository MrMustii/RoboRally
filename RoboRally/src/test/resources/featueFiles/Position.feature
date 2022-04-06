
@tag
Feature: Position

  @tag1
  Scenario: Change x and y coordinates in position
    Given a position with coordinates (1, 1) and orientation 0
    When the position changes to upwards adjacent tile
    Then the position has coordinates (1, 2)
    
  @tag2
  Scenario: Change x and y coordinates in position
    Given a position with coordinates (1, 1) and orientation 0
    When the position changes to right adjacent tile
    Then the position has coordinates (2, 1)
    
  @tag3
  Scenario: Change orientation in position
    Given a position with coordinates (1, 1) and orientation 0
    When the orientation of the position changes to right
    Then the position has orientation 1
    
    
    

