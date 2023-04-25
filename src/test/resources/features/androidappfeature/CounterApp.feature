Feature: Validate Covid-19 data

  @CounterApp
  Scenario: Verify the working of plus counter
    Then User verifies that counter value is "0"
    When User press the plus button
    Then User verifies that counter value is "1"