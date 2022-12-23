Feature: getting random language not equal to Input

  Scenario Outline: getting random language from db
    Given the language id <languageId>
    And the seeds value  <seed>
    And the number of languages <numberOfLanguages> required
    When i request the random languages details
    Then webpage should show language List "<languageListValue>"
    And language list size is equal to <numberOfLanguages>
    Examples:
      |languageId | seed | numberOfLanguages | languageListValue    |
      |  1      |    1    | 3  |  German -French -Mandarin - |
      |  2      |    5    | 3    | French -Mandarin -German -|
      |  4      |    2    | 3    | Italian -German -Japanese -|



