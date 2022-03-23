@tag
Feature: Lives of robot

  @tag1
  Scenario: damaging robot
    Given A robot and 5 lives
    When the robot takes 1 damage
    Then it has 4 lives

  @tag2
  Scenario: kill robot
    Given A robot with starting positions (1, 1)
    And 1 live
    When the robot takes 1 damage
    Then it respawns at the starting point
    And it has 5 lives