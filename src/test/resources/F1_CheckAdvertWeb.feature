Feature: Check advertisement web

  Scenario: Add ads to favorites section
    Given Web page "https://www.ss.com/en"
    And I see that Memo section is empty
    Then I open first ad in section "Cars"
    And I see a link "Add to favorites"
    Then I add an ad to favorites
    Then I see success alert
    Then I see "Memo" section contain added ad URL
    When I try to add the same ad
    Then I see no changes
    Given Search section "https://www.ss.com/en/search/"
    When I search for "Audi"
    And I add 2 more ads
    Then I see "Memo" section contain added ad URL
    When I delete all ads from Memo
    Then I see that Memo section is empty
