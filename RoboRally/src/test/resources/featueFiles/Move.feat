@tag
Feature: Move robot

    @tag1
  Scenario Outline: Rotation of robot
    Given a robot and an orientation <orientation>
    And a start position (1, 1)
    When the robot moves <direction>
    Then the robot has reached <x>, <y>

    Examples: 
      | orientation   | direction  | x | y |
      | "UP"          | "forward"  | 1 | 2 |
      | "RIGHT"       | "forward"  | 2 | 1 |
      | "DOWN"        | "forward"  | 1 | 0 |
      | "LEFT"        | "forward"  | 0 | 1 |
      | "UP"          | "backward" | 1 | 0 |
      | "RIGHT"       | "backward" | 0 | 1 |
      | "DOWN"        | "backward" | 1 | 2 |
      | "LEFT"        | "backward" | 2 | 1 |