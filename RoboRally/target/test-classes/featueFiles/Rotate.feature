@tag
Feature: Rotate robot
   
   @tag1
  Scenario Outline: Rotation of robot
    Given a robot and an orientation <s-orientation>
    When the robot rotates <direction>
    Then the robot has orientation <e-orientation>

    Examples: 
      | s-orientation | direction   | e-orientation |
      | "UP"          | "clockwise" | "RIGHT"       |
      | "RIGHT"       | "clockwise" | "DOWN"        |
      | "DOWN"        | "clockwise" | "LEFT"        |
      | "LEFT"        | "clockwise" | "UP"          |
      | "UP"          | "counter-clockwise" | "LEFT"  |
      | "RIGHT"       | "counter-clockwise" | "UP"    |
      | "DOWN"        | "counter-clockwise" | "RIGHT" |
      | "LEFT"        | "counter-clockwise" | "DOWN"  |      
    

