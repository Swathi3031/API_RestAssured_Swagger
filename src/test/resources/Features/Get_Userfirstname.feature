
 Feature: Get User by FirstName API - GET Request Scenarios

Scenario: Get User by valid FirstName
  Given User prepares GET request with BaseURL, valid endpoint, and existing user FirstName
  When User sends GET request with existing user FirstName
  Then User receives 200 OK status with user details in response

Scenario: Validate FirstName in Response Matches Requested FirstName
  Given User prepares GET request with BaseURL, valid endpoint, and existing user FirstName
  When User sends GET request with existing user FirstName
  Then Response body contains same UserfirstName as requested

Scenario: Validate Response Body Content-Type is JSON
  Given User prepares GET request with BaseURL, valid endpoint, and existing user FirstName
  When User sends GET request with existing user FirstName
  Then Response Content-Type is JSON with status code 200 OK

Scenario: Validate Required Fields in Response
  Given User prepares GET request with BaseURL, valid endpoint, and existing user FirstName
  When User sends GET request with existing user FirstName
  Then Response contains user details: user_id, UserfirstName, UserlastName, userContactNumber, userEmailId, creationTime, lastModTime, userAddress, addressId, plotNumber, street, state, country, zipCode

Scenario: Validate FirstName is String Type
  Given User prepares GET request with BaseURL, valid endpoint, and existing user FirstName
  When User sends GET request with existing user FirstName
  Then UserfirstName in response is of string type

Scenario: Validate address_id is integer type in response
  Given User prepares GET request with BaseURL, valid endpoint, and existing user FirstName
  When User sends GET request with existing user FirstName
  Then address_id is of integer type

Scenario: Get User with Non-Existing FirstName
  Given User prepares GET request with BaseURL, valid endpoint, and non-existing FirstName
  When User sends GET request with non-existent FirstName
  Then User receives 404 Not Found status with the error message

Scenario: Get User with Invalid FirstName Format (e.g. integer instead of string)
  Given User prepares GET request with BaseURL, valid endpoint, and invalid FirstName as 123
  When User sends GET request with invalid FirstName 123
  Then User receives 404 Not Found status with the error message

Scenario: Get User with Invalid Endpoint
  Given User prepares GET request with BaseURL and invalid endpoint path
  When User sends GET request to wrong path
  Then User receives 404 Not Found status with the error message

Scenario: Get User without Authorization
  Given User prepares GET request with BaseURL, valid endpoint, and existing user FirstName but without Authorization header
  When User sends GET request with existing user FirstName without Auth
  Then User received 401 Unauthorized status