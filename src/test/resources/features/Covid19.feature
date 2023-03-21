Feature: Validate Covid-19 data

  @Covid19
  Scenario: Fetch the response from covid19 api
    Given when user navigates to covid page
    And user accesses the "covid19" api
    When user performs a get request for "data.json" endpoint
    Then user should be able to get response
    And validate data from the response based on "state"
    And user accesses the "covid19" api
    And user performs a get request for "state_district_wise.json"
    Then fetch data from response for "recovered" for districts
    And validate the districts "recovered" on web
