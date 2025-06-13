Feature: Add New User API - Negative Scenarios

  Scenario: Add a new user without Authorization
    Given Users prepare the POST request with BaseURL and valid endpoint
    When Users sends the POST request with all mandatory fields but without Authorization
    Then Users receives 401 Unauthorized status

  Scenario: Add a new user with invalid BaseURL
    Given User prepares the POST request with invalid BaseURL and valid endpoint
    When User sends the POST request with all required user details
    Then User receives 404 Not Found status with appropriate message

  Scenario: Add a new user with invalid endpoint
    Given User prepares the POST request with BaseURL and invalid endpoint
    When User sends the POST request with all required user details
    Then User receives 404 Not Found status with appropriate message

  Scenario: Add a new user with empty endpoint
    Given User prepares the POST request with BaseURL and empty endpoint
    When User sends the POST request with all required user details
    Then User receives 404 Not Found status with appropriate message

  Scenario: Add a new user with empty request body
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with an empty request body
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with only optional fields
    Given Users prepare the POST request with BaseURL and valid endpoint
    When Users sends the POST request without only mandatory fields
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with existing email and contact
    Given Users prepare the POST request with BaseURL and valid endpoint
    When Users sends the POST request with existing email and contact number
    Then User receives 409 Conflict status for existing entry

    Scenario: Add a new user with invalid first name
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with invalid first name
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with invalid last name
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with invalid last name
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with special characters in contact number
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with special characters in contact number
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with contact number less than 10 digits
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with less than 10 digits in contact number
    Then User receives 400 Bad Request status with appropriate message
    
  Scenario: Add a new user with contact number more than 10 digits
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with more than 10 digits in contact number
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with integer plot number
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with numeric plot number
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with special characters in street name
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with special characters in street name
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with numeric state value
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with numeric value in state
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with numeric country value
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with numeric value in country
    Then User receives 400 Bad Request status with appropriate message

  Scenario: Add a new user with special characters in zip code
    Given Users prepare the POST request with BaseURL and valid endpoint
    When User sends the POST request with special characters in zip code
    Then User receives 400 Bad Request status with appropriate message