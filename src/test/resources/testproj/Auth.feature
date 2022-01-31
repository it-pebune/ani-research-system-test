Feature: Auth
  Ani pe bune Auth page
  Scenario: Test case 1: Page load without errors
    Given I start browser
    When I open the "http://localhost:7000" url
    Then The page loads successfully and HTTP success code is returned
    And Title displayed is the "ANI / BUNE"
    And "auth-container" element is displayed find by "class"