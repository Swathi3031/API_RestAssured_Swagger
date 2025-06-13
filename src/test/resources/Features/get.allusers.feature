Feature: Get All Users API - GET Request Scenarios

Scenario: Get All Users with Valid Base URL and Endpoint
  Given User prepares the GET request with valid BaseURL and valid endpoint
  When User sends GET request to fetch users
  Then User receives 200 OK status with list of users

Scenario: Validate Response Body Content-Type is JSON
  Given User prepares the GET request with valid BaseURL and valid endpoint
  When User sends GET request to fetch users
  Then Response Content-Type is JSON with the status code 200 OK

Scenario: Validate Response Contains Required Fields
  Given User prepares the GET request with valid BaseURL and valid endpoint
  When User sends GET request to fetch users
  Then Response contains user fields: user_id, UserfirstName, UserlastName, userContactNumber, userEmailId, creationTime, lastModTime, userAddress, addressId, plotNumber, street, state, country, zipCode

Scenario: Validate user_id is integer type in response
  Given User prepares the GET request with valid BaseURL and valid endpoint
  When User sends GET request to fetch users
  Then user_id is of integer type with the status code 200 OK

Scenario: Validate address_id is integer type in response
  Given User prepares the GET request with valid BaseURL and valid endpoint
  When User sends GET request to fetch users
  Then address_id is of integer type with the status code 200 OK

Scenario: Get Users with Invalid Endpoint
  Given User prepares the GET request with valid BaseURL and invalid endpoint
  When User sends GET request to fetch users
  Then User receives 404 Not Found status

Scenario: Get Users with Empty Endpoint
  Given User prepares the GET request with valid BaseURL and empty endpoint
  When User sends GET request to fetch users
  Then User receives 404 Not Found status

Scenario: Get USERS without Authorization and receives 401
  Given User prepares the GET request without Authorization header
  When User sends GET request to fetch users without Auth
  Then User receives 401 Unauthorized status

Scenario: Get Users with Invalid Base URL
  Given User prepares the GET request with invalid BaseURL and valid endpoint
  When User sends GET request to fetch users
  Then User receives 404 Not Found status with error message