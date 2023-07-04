@test
@ui
Feature: Can we find cheese?

  Scenario: Finding some cheese
    Given I am on the Google search page
    When I search for "Cheese!"
    Then the page title should start with "cheese"

  Scenario: Finding some Beef
    Given I am on the Google search page
    When I search for "Beef!"
    Then the page title should start with "beef"

  Scenario: Agent claimant search
    Given A user is on the claimant submission screen
    When A nino is provided
    Then They will be on the next page