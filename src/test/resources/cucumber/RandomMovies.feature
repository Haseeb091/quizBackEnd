Feature: getting random films

  Scenario Outline: getting random films from db
    Given the seed <seed>
    And the number of films <numberOfFilms> required
    When i request the random film details
    Then webpage should show film List "<filmList>"
    And film list size is equal to <numberOfFilms>
    Examples:
      | seed | numberOfFilms | filmList    |
      |    1    | 3  |  265 DYING MAKER 1973 A Intrepid Tale of a Boat And a Monkey who must Kill a Cat in California  -256 DROP WATERFRONT 2012 A Fanciful Documentary of a Husband And a Explorer who must Reach a Madman in Ancient China  -28 ANTHEM LUKE 1954 A Touching Panorama of a Waitress And a Woman who must Outrace a Dog in An Abandoned Amusement Park  - |
      |    5    | 3    | 71 BILKO ANONYMOUS 2000 A Emotional Reflection of a Teacher And a Man who must Meet a Cat in The First Manned Space Station  -55 BARBARELLA STREETCAR 1958 A Awe-Inspiring Story of a Feminist And a Cat who must Conquer a Dog in A Monastery  -736 ROBBERY BRIGHT 1968 A Taut Reflection of a Robot And a Squirrel who must Fight a Boat in Ancient Japan  -|



