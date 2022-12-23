Feature: get specific actor

  Scenario Outline: getting specific actor from db
  Given actor exists with id <actorid>
    When i request the actors details
    Then webpage should show actors "<firstname>" and "<lastname>"
    Examples:
      | actorid | firstname | lastname    |
      |    1    | PENELOPE  | GUINESS     |
      |    5    | JOHNNY    | LOLLOBRIGIDA|



