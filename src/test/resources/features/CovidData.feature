Feature: Google testing

  @CovidData
  Scenario: Get covid19 data
    Given when user navigates to covid page
    When user accesses the "covid19" api
    And user performs a get request for "data.json" endpoint
    And validate data from the response based on "state"
    And user performs a get request for "state_district_wise.json"
    Then fetch data from response for "recovered" for districts
    And validate the districts "recovered" on web

