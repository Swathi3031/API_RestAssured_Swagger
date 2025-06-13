Feature: Get User by ID API - GET Request Scenarios

Scenario: Get User by Valid ID
  Given User prepares GET request with BaseURL, valid endpoint, and valid user ID
  When User sends GET request with existing user ID
  Then User receives 200 OK status with the user details in response

Scenario: Validate user_id in Response Matches Requested ID
  Given User prepares GET request with BaseURL, valid endpoint, and valid user ID
  When User sends GET request with existing user ID
  Then Response body contains same user_id as requested

Scenario: Validate Response Body Content-Type is JSON
  Given User prepares GET request with BaseURL, valid endpoint, and valid user ID
  When User sends GET request with existing user ID
  Then the Response Content-Type is JSON with status code 200 OK

Scenario: Validate Required Fields in Response
  Given User prepares GET request with BaseURL, valid endpoint, and valid user ID
  When User sends GET request with existing user ID
  Then Response contains mandatory fields: user_id, UserfirstName, UserlastName, userContactNumber, userEmailId, creationTime, lastModTime, userAddress, addressId, plotNumber, street, state, country, zipCode

Scenario: Validate user_id is Integer Type
  Given User prepares GET request with BaseURL, valid endpoint, and valid user ID
  When User sends GET request with existing user ID
  Then the user_id in response is of integer type

Scenario: Validate address_id is integer type in response
  Given User prepares GET request with BaseURL, valid endpoint, and valid user ID
  When User sends GET request with existing user ID
  Then the address_id is of integer type

Scenario: Get User with Non-Existing ID
  Given User prepares GET request with BaseURL, valid endpoint, and non-existing user ID
  When User sends GET request with non-existent ID
  Then User receives 404 Not Found status with an error message

Scenario: Get User with Invalid ID Format (e.g. string instead of integer)
  Given User prepares GET request with BaseURL, valid endpoint, and invalid user ID 'abc'
  When User sends GET request with invalid ID 'abc'
  Then User receives 400 Bad Request status with an error message

Scenario: Get User with Invalid Endpoint
  Given User prepares GET request with the BaseURL and invalid endpoint path
  When User sends GET request to the wrong path
  Then User receives 404 Not Found status with an error message

Scenario: Get User without Authorization
  Given User prepares GET request with valid BaseURL, valid endpoint, and existing user ID but without Authorization header
  When User sends GET request with existing user ID without Authorization
  Then User receives the 401 Unauthorized status