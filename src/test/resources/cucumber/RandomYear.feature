Feature: getting random years not equal to Input

  Scenario Outline: getting random years from db
    Given the year <notYear>
    And the seed value <seed>
    And the number of years <numberOfYears> required
    When i request the random years details
    Then webpage should show year List "<yearsListValue>"
    And years list size is equal to <numberOfYears>
    Examples:
     |notYear     | seed | numberOfYears | yearsListValue    |
     |  1999      |    1    | 3  |  2002 -1992 -2007 - |
     |  2000      |    5    | 3    | 1990 -1969 -2006 -|



