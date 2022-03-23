@tag
Feature: Death of robot

  @tag1
  Scenario: Death of robot
    Given A robot and a start position (1, 1)
    When the robot dies
    Then it respawns at the starting point
    Then 